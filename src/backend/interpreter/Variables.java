package backend.interpreter;

import java.util.HashMap;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.SymtabEntry;
import intermediate.type.Typespec;

import static intermediate.symtab.SymtabEntry.Kind.*;
import static intermediate.type.Typespec.Form.*;

public class Variables extends Executor
{
    @SuppressWarnings("unchecked")
    Object variable(VariableContext ctx) 
    {
        SymtabEntry variableEntry = ctx.entry;
        String variableName = variableEntry.getName();
        Typespec variableTypespec = variableEntry.getTypespec();
        int nestingLevel = variableEntry.getSymtab().getNestingLevel();
        
        // Is it a parameterless function call?
        if (variableEntry.getKind() == FUNCTION)
        {
            return expressionsImpl.executeFunctionCall(variableEntry, null);
        }

        // Get the variable reference from the appropriate activation record.
        StackFrame frame = runtimeStack.getTopmost(nestingLevel);
        Cell variableCell = frame.getCell(variableName);

        // Execute any array subscripts or record fields.
        for (ModifierContext modCtx : ctx.modifier()) 
        {
            // Subscripts.
            if (modCtx.indexList() != null)
            {
                // Compute a new reference for each subscript.
                for (IndexContext indexCtx : modCtx.indexList().index())
                {
                    Typespec indexTypespec = variableTypespec.getArrayIndexType();
                    int minIndex = 0;
                    
                    if (indexTypespec.getForm() == SUBRANGE)
                    {
                        minIndex = (Integer) indexTypespec.getSubrangeMinValue();
                    }
                    
                    int value = (Integer) visit(indexCtx.expression());
                    int index = value - minIndex;
                    
                    variableCell = ((Cell[]) variableCell.getValue())[index];
                    variableTypespec = variableTypespec.getArrayElementType();
                }
            }

            // Record field.
            else 
            {
                SymtabEntry fieldEntry = modCtx.field().entry;
                String fieldName = fieldEntry.getName();

                // Compute a new reference for the field.
                HashMap<String, Cell> map =
                        (HashMap<String, Cell>) variableCell.getValue();
                variableCell = map.get(fieldName);
                variableTypespec = fieldEntry.getTypespec();
            }
        }

        return variableCell;
    }
}
