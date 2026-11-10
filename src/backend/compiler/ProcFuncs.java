package backend.compiler;

import java.util.ArrayList;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.Symtab;
import intermediate.symtab.SymtabEntry;
import intermediate.symtab.SymtabEntry.Kind;
import intermediate.type.Typespec;

import static intermediate.symtab.SymtabEntry.Kind.*;
import static backend.compiler.Directive.*;
import static backend.compiler.Instruction.*;

public class ProcFuncs extends Compiler
{
    /**
     * Emit code for declared procedures and functions.
     */
    Object procedureAndFunctionsPart(ProcedureAndFunctionsPartContext ctx)
    {
        for (ProcFuncDefinitionContext defnCtx : ctx.procFuncDefinition())
        {
            SymtabEntry procFuncEntry = defnCtx.procedureHead() != null 
                    ? defnCtx.procedureHead().identifier().entry
                    : defnCtx.functionHead().identifier().entry;
            emitProcFunc(procFuncEntry);
        }
        
        return null;
    }
    
    /**
     * Emit code for a declared procedure or function.
     * @param procFuncEntry the symbol table entry of the routine's name.
     */
    private void emitProcFunc(SymtabEntry procFuncEntry)
    {
        Symtab symtab = procFuncEntry.getProcFuncSymtab();
        LocalStack localStack = new LocalStack();
        code.setLocalStack(localStack);

        emitHeader(procFuncEntry);
        emitLocalVariables(procFuncEntry);
        
        DataInitializer data = new DataInitializer(code);
        data.emitLocalInitializations(symtab);

        // Emit code for the compound statement.
        CompoundStatementContext stmtCtx = 
            (CompoundStatementContext) procFuncEntry.getExecutable();
        visit(stmtCtx);
        
        emitReturn(procFuncEntry);
        emitEpilogue(symtab);
    }

    /**
     * Emit the routine header.
     * @param procFuncEntry the symbol table entry of the routine's name.
     */
    private void emitHeader(SymtabEntry procFuncEntry)
    {
        String routineName = procFuncEntry.getName();
        ArrayList<SymtabEntry> parmEntries = procFuncEntry.getProcFuncParameters();
        StringBuilder buffer = new StringBuilder();

        // Procedure or function name.
        buffer.append(routineName);
        buffer.append("(");

        // Parameter and return type descriptors.
        if (parmEntries != null) 
        {
            for (SymtabEntry parmEntry : parmEntries)
            {
                buffer.append(code.typeDescriptor(parmEntry));
            }
        }
        buffer.append(")");
        buffer.append(code.typeDescriptor(procFuncEntry));

        code.emitLine();
        if (procFuncEntry.getKind() == PROCEDURE) 
        {
            code.emitComment("PROCEDURE " + routineName);
        }
        else
        {
            code.emitComment("FUNCTION " + routineName);
        }
              
        code.emitDirective(METHOD_PRIVATE_STATIC, buffer.toString());
    }

    /**
     * Emit directives for the local variables.
     * @param procFuncEntry the symbol table entry of the routine's name.
     */
    private void emitLocalVariables(SymtabEntry procFuncEntry)
    {
        Symtab symtab = procFuncEntry.getProcFuncSymtab();

        code.emitLine();

        // Loop over all the routine's identifiers and
        // emit a .var directive for each variable and formal parameter.
        for (SymtabEntry entry : symtab.getUnsorted())
        {
            Kind kind = entry.getKind();

            if ((kind == VARIABLE) || (kind == VALUE_PARAMETER)
                                   || (kind == REFERENCE_PARAMETER)) 
            {
                int slot = entry.getSlotNumber();
                code.emitDirective(VAR, slot + " is " + entry.getName(),
                                        code.typeDescriptor(entry));
            }
        }
        
        code.emitLine();
    }

    /**
     * Emit the routine's return code.
     * @param procFuncEntry the symbol table entry of the routine's name.
     */
    private void emitReturn(SymtabEntry procFuncEntry)
    {
        code.emitLine();

        // Function: Return the value in the implied function variable.
        if (procFuncEntry.getKind() == FUNCTION) 
        {
            Typespec type = procFuncEntry.getTypespec();

            // Get the slot number of the function variable.
            String varName = procFuncEntry.getName();
            SymtabEntry varEntry = procFuncEntry.getProcFuncSymtab().lookup(varName);
            code.emitLoadLocal(type, varEntry.getSlotNumber());
            code.emitReturnValue(type);
        }

        // Procedure: Just return.
        else code.emit(RETURN);
    }

    /**
     * Emit the routine's epilogue.
     */
    private void emitEpilogue(Symtab symtab)
    {
        LocalStack localStack = code.getLocalStack();

        code.emitLine();
        code.emitDirective(LIMIT_LOCALS, symtab.localsSize());
        code.emitDirective(LIMIT_STACK,  localStack.capacity());
        code.emitDirective(END_METHOD);
    }
}
