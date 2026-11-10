package backend.converter;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.Predefined;
import intermediate.symtab.SymtabEntry;
import intermediate.symtab.SymtabEntry.Kind;
import intermediate.type.Typespec;
import intermediate.type.Typespec.Form;

import static intermediate.symtab.SymtabEntry.Kind.*;
import static intermediate.type.Typespec.Form.*;

public class Variables extends Converter
{
    Object variableDeclarations(VariableDeclarationsContext ctx)
    {
        VariableIdentifierListContext varListCtx = 
                                        ctx.variableIdentifierList();   
        TypeSpecificationContext typespecCtx = ctx.typeSpecification();
        Typespec typespec = typespecCtx.typespec.baseType();
        Form typeForm = typespec.getForm();
        String typeName = Types.javaTypeName(typespec);
        
        if (programDeclarations)
        {
            for (IdentifierContext idCtx : varListCtx.identifier())
            {
                code.emitStart("private static " + typeName + " ");
                String variableName = idCtx.entry.getName().toLowerCase();
                code.emit(variableName);
                
                if (typeForm == ARRAY)
                {
                    Types.emitEmptyArrayBrackets(typespec);
                }
                
                code.emitEnd(";");
            }
        }
        else
        {
            DataInitializer data = new DataInitializer(code);

            for (IdentifierContext idCtx : varListCtx.identifier())
            {
                String variableName = idCtx.entry.getName().toLowerCase();

                if (typeForm == RECORD)
                {
                    data.emitNewRecord(idCtx.entry, true);
                }
                else if (typeForm == ARRAY)
                {
                    data.emitNewArray(idCtx.entry, true);
                }
                else
                {
                    code.emitLine(typeName + " " + variableName + ";");
                }
            }
        }
        
        return null;
    }

    Object variable(VariableContext ctx) 
    {
        IdentifierContext idCtx = ctx.identifier();
        SymtabEntry variableEntry = idCtx.entry;
        String variableName = variableEntry.getName().toLowerCase();
        Typespec typespec = idCtx.typespec;
        Kind kind = variableEntry.getKind();
               
        if (    (typespec != Predefined.booleanType)
             && (kind == ENUMERATED_CONSTANT))
        {
            String typeIdName = typespec.getIdentifier().getName();
            code.emit(typeIdName + "." 
                      + variableName.toUpperCase());
        }
        else if (kind == CONSTANT)
        {
            code.emit(variableName.toUpperCase());
        }
        else if (kind == FUNCTION) 
        {
            code.emit(variableName + "()");
        }
        else
        {
            code.emit(variableName);
        }
        
        // Loop over any subscript and field modifiers.
        for (ModifierContext modCtx : ctx.modifier())
        {
            // Subscripts.  
            if (modCtx.indexList() != null)
            {
                for (IndexContext indexCtx : modCtx.indexList().index())
                {
                    Typespec indexType = typespec.getArrayIndexType();
                    int minIndex = 0;
                    
                    if (indexType.getForm() == SUBRANGE)
                    {
                        minIndex = 
                            (Integer) indexType.getSubrangeMinValue();
                    }
                    
                    ExpressionContext exprCtx = indexCtx.expression();
                    code.emit("[");
                    if (minIndex == 0)
                    {
                        visit(exprCtx);
                    }
                    else
                    {
                        code.emit("(");
                        visit(exprCtx);
                        code.emit(")");
                        
                        if (minIndex < 0) code.emit("+" + (-minIndex));
                        else              code.emit("-" + minIndex);
                    }
                    code.emit("]");
                    
                    typespec = typespec.getArrayElementType();
                }
            }
            
            // Record field.
            else  
            {
                FieldContext fieldCtx = modCtx.field();
                String fieldName = fieldCtx.entry.getName();
                code.emit("." + fieldName);
                typespec = fieldCtx.typespec;
            }
        }
        
        return null;
    }
}
