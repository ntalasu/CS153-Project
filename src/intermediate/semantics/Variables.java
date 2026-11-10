package intermediate.semantics;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.Predefined;
import intermediate.symtab.Symtab;
import intermediate.symtab.SymtabEntry;
import intermediate.symtab.SymtabEntry.Kind;
import intermediate.type.Typespec;

import static intermediate.semantics.SemanticErrorHandler.Code.*;
import static intermediate.symtab.SymtabEntry.Kind.*;
import static intermediate.type.Typespec.Form.ARRAY;
import static intermediate.type.Typespec.Form.RECORD;

import java.util.List;

public class Variables extends Semantics
{
    Typespec variableDeclarations(VariableDeclarationsContext ctx) 
    { 
        TypeSpecificationContext typespecCtx = ctx.typeSpecification();
        Typespec typespec = (Typespec) visit(typespecCtx);      
        VariableIdentifierListContext varListCtx =
                                    ctx.variableIdentifierList();
        
        typespecCtx.typespec = typespec;
        
        for (IdentifierContext idCtx : 
                                varListCtx.identifier())
        {
            int lineNumber = idCtx.getStart().getLine();        
            String variableName = idCtx.getText();
            SymtabEntry variableEntry = symtabStack.lookupLocal(variableName);
            
            if (variableEntry == null)
            {
                variableEntry = symtabStack.enterLocal(variableName, VARIABLE);
                variableEntry.setTypespec(typespec);  
                
                // Assign a slot number to a local variable.
                Symtab symtab = variableEntry.getSymtab();
                if (symtab.getNestingLevel() > 1)
                {
                    variableEntry.setSlotNumber(symtab.getNextSlotNumber());
                    
                    if (variableEntry.getTypespec() == Predefined.realType)
                    {
                        symtab.getNextSlotNumber();
                    }
                }
                
                idCtx.entry = variableEntry;
                idCtx.typespec = typespec;
            }
            else
            {
                error.flag(REDECLARED_IDENTIFIER, idCtx);
            }
            
            variableEntry.appendLineNumber(lineNumber);        
        }
        
        return typespec;
    }
    
Typespec variable(VariableContext ctx)
{
    IdentifierContext idCtx = ctx.identifier();
    List<ModifierContext> modifierList = ctx.modifier();
    String variableName = idCtx.getText().toLowerCase();
    SymtabEntry variableEntry = symtabStack.lookup(variableName);
    
    if (variableEntry != null)
    {
        int lineNumber = ctx.getStart().getLine();
        variableEntry.appendLineNumber(lineNumber);
        
        Kind kind = variableEntry.getKind();
        switch (kind)
        {
            case TYPE:
            case PROGRAM:
            case PROGRAM_PARAMETER:
            case PROCEDURE:
            case UNDEFINED:
                error.flag(INVALID_VARIABLE, ctx);
                break;
                
            case CONSTANT:
            case ENUMERATED_CONSTANT:
            case FUNCTION:
                if (modifierList.size() > 0)
                {
                    error.flag(INVALID_MODIFIER, ctx.modifier(0));
                }
                
            default: break;
        }
        
        ctx.entry = idCtx.entry = variableEntry;
        idCtx.typespec = variableEntry.getTypespec();
        
        ctx.typespec = modifiedVariableTypespec(ctx, idCtx.typespec);            
    }
    else
    {
        error.flag(UNDECLARED_IDENTIFIER, ctx);
        ctx.typespec = Predefined.integerType;
    }
    
    return ctx.typespec;
}

private Typespec modifiedVariableTypespec(VariableContext varCtx, 
                                             Typespec typespec)
{
    // Loop over the modifiers.
    for (ModifierContext modCtx : varCtx.modifier())
    {
        // Subscripts.
        if (modCtx.indexList() != null)
        {
            IndexListContext indexListCtx = modCtx.indexList();

            // Loop over the subscripts.
            for (IndexContext indexCtx : indexListCtx.index())
            {
                if (typespec.getForm() == ARRAY)
                {
                    Typespec indexTypespec = typespec.getArrayIndexType();
                    ExpressionContext exprCtx = indexCtx.expression();
                    Typespec exprTypespec = (Typespec) visit(exprCtx);

                    if (indexTypespec.baseType() != exprTypespec.baseType())
                    {
                        error.flag(TYPE_MISMATCH, exprCtx);
                    }

                    // Datatype of the next dimension.
                    typespec = typespec.getArrayElementType();
                } 
                else
                {
                    error.flag(TOO_MANY_SUBSCRIPTS, indexCtx);
                }
            }
        }
        else  // Record field.
        {
            if (typespec.getForm() == RECORD)
            {
                Symtab symtab = typespec.getRecordSymtab();
                FieldContext fieldCtx = modCtx.field();
                String fieldName = 
                    fieldCtx.identifier().getText().toLowerCase();
                SymtabEntry fieldEntry = symtab.lookup(fieldName);

                // Field of the record type?
                if (fieldEntry != null) 
                {
                    typespec = fieldEntry.getTypespec();
                    fieldCtx.entry = fieldEntry;
                    fieldCtx.typespec = typespec;
                    fieldEntry.appendLineNumber(modCtx.getStart().getLine());
                }
                else 
                {
                    error.flag(INVALID_FIELD, modCtx);
                }
            }
            
            // Not a record variable.
            else 
            {
                error.flag(INVALID_FIELD, modCtx);
            }
        }
    }

    return typespec;
}
}
