package backend.compiler;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.Predefined;
import intermediate.symtab.SymtabEntry;
import intermediate.type.Typespec;
import intermediate.type.Typespec.Form;

import static backend.compiler.Instruction.*;
import static intermediate.symtab.Predefined.realType;
import static intermediate.symtab.SymtabEntry.Kind.*;
import static intermediate.type.Typespec.Form.*;

public class Variables extends Compiler
{
    /**
     * Emit code to load a scalar variable's value 
     * or a structured variable's address.
     * @param ctx the VariableContext.
     */
    Object factorVariable(FactorVariableContext ctx)
    {
        // Load the scalar value or structure address.
        VariableContext variableCtx = ctx.variable();
        Typespec variableTypespec = (Typespec) visit(variableCtx);
        
        // Load an array element's or record field's value.
        int modifierCount = variableCtx.modifier().size();
        if (modifierCount > 0)
        {
            ModifierContext lastModCtx =
                    variableCtx.modifier().get(modifierCount - 1);
            
            if (lastModCtx.indexList() != null)
            {
                emitLoadArrayElementValue(variableTypespec);
            }
            else
            {
                emitLoadRecordField(lastModCtx.field(), 
                                    variableTypespec);
            }
        }
        
        return null;
    }

    /**
     * Emit code to load a scalar variable's value 
     * or a structured variable's address.
     * @param ctx the VariableContext.
     */
    Typespec variable(VariableContext ctx)
    {
        SymtabEntry variableEntry = ctx.entry;
        Typespec variableTypespec = variableEntry.getTypespec();
        int modifierCount = ctx.modifier().size();
        
        if (variableEntry.getKind() == FUNCTION)
        {
            statementsImpl.emitCall(ctx.entry, null, ctx.typespec);
            return null;
        }
        
        // Scalar value or structure address.
        code.emitLoadValue(variableEntry);
        
        // Loop over subscript and field modifiers.
        for (int i = 0; i < modifierCount; ++i)
        {
            ModifierContext modCtx = ctx.modifier().get(i);
            boolean isLastModifier = i == modifierCount - 1;

            // Subscript
            if (modCtx.indexList() != null) 
            {
                variableTypespec = emitLoadArrayElementAccess(
                    modCtx.indexList(), variableTypespec, isLastModifier);
            }
            
            // Field
            else
            {
                FieldContext fieldCtx = modCtx.field();
                
                if (!isLastModifier)
                {
                    variableTypespec = 
                        emitLoadRecordField(fieldCtx, 
                                            variableEntry.getTypespec());
                }
                else variableTypespec = fieldCtx.typespec;
            }
        }
        
        return variableTypespec;
    }

    /**
     * Emit code to access an array element by loading the array address
     * and the subscript value. This can subsequently be followed by code
     * to load the array element's value or to store into the array element. 
     * @param indexListCtx the index list context.
     * @param elmtType the array element type.
     * @param lastModifier true if this is the variable's last modifier.
     * @return the type of the element.
     */
    private Typespec emitLoadArrayElementAccess(
                                    IndexListContext indexListCtx,
                                    Typespec elmtType, 
                                    boolean lastModifier)
    {
        int indexCount = indexListCtx.index().size();
        
        // Loop over the subscripts.
        for (int i = 0; i < indexCount; i++)
        {
            IndexContext indexCtx = indexListCtx.index().get(i);
            visit(indexCtx.expression());

            Typespec indexType = elmtType.getArrayIndexType();

            if (indexType.getForm() == SUBRANGE) 
            {
                int min = (Integer) indexType.getSubrangeMinValue();
                if (min != 0) 
                {
                    code.emitLoadConstant(min);
                    code.emit(ISUB);
                }
            }

            if (!lastModifier || (i < indexCount - 1)) 
                code.emit(AALOAD);
            elmtType = elmtType.getArrayElementType();
        }

        return elmtType;
    }

    /**
     * Emit a load of an array element's value.
     * @param elmtTypespec the element type if character, else null.
     */
    private void emitLoadArrayElementValue(Typespec elmtTypespec)
    {
        Form form = SCALAR;

        if (elmtTypespec != null) 
        {
            elmtTypespec = elmtTypespec.baseType();
            form = elmtTypespec.getForm();
        }

        // Load a character from a string.
        if (elmtTypespec == Predefined.charType) 
        {
            code.emit(INVOKEVIRTUAL, "java/lang/StringBuilder.charAt(I)C");
        }

        // Load an array element.
        else 
        {
            code.emit(  elmtTypespec == Predefined.integerType ? IALOAD
                      : elmtTypespec == Predefined.realType    ? DALOAD
                      : elmtTypespec == Predefined.booleanType ? BALOAD
                      : elmtTypespec == Predefined.charType    ? CALOAD
                      : form == ENUMERATED                     ? IALOAD
                      :                                          AALOAD);
        }
    }
    
    /**
     * Emit code to load the address or value of a record field.
     * @param fieldCtx the FieldContext.
     * @param last true if this is the variable's last field, else false.
     * @return the type of the field.
     */
    private Typespec emitLoadRecordField(FieldContext fieldCtx, 
                                         Typespec recordTypespec)
    {
        SymtabEntry fieldEntry = fieldCtx.entry;
        String fieldName = fieldEntry.getName();
        Typespec fieldTypespec = fieldCtx.typespec;  
        String recordPath = fieldCtx.entry.getSymtab().getOwner()
                                    .getTypespec().getRecordPath();
        String fieldPath = recordPath + "/" + fieldName;     
  
        code.emit(GETFIELD, fieldPath, code.typeDescriptor(fieldTypespec));
        code.getLocalStack().increase(fieldTypespec == realType ? 2 : 1);

        return fieldTypespec;
    }
}
