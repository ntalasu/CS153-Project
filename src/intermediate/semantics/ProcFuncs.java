package intermediate.semantics;

import java.util.ArrayList;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.Predefined;
import intermediate.symtab.Symtab;
import intermediate.symtab.SymtabEntry;
import intermediate.symtab.SymtabEntry.Kind;
import intermediate.type.Typespec;

import static backend.BackendMode.Mode.EXECUTOR;
import static intermediate.semantics.SemanticErrorHandler.Code.*;
import static intermediate.symtab.SymtabEntry.Kind.*;
import static intermediate.symtab.SymtabEntry.ProcFuncCode.*;
import static intermediate.type.Typespec.Form.*;

public class ProcFuncs extends Semantics
{
    @SuppressWarnings("unchecked")
    Object procFuncDefinition(ProcFuncDefinitionContext ctx)
    {
        FunctionHeadContext funcCtx = ctx.functionHead();
        ProcedureHeadContext procCtx = ctx.procedureHead();
        IdentifierContext idCtx = null;
        ParametersContext parameters = null;
        boolean isFunctionDefinition = funcCtx != null;
        Typespec returnTypespec = null;
        String procFuncName;

        if (isFunctionDefinition)
        {
            idCtx = funcCtx.identifier();
            parameters = funcCtx.parameters();
        } 
        else
        {
            idCtx = procCtx.identifier();
            parameters = procCtx.parameters();
        }

        procFuncName = idCtx.IDENTIFIER().getText().toLowerCase();
        SymtabEntry procFuncEntry = symtabStack.lookupLocal(procFuncName);

        if (procFuncEntry != null)
        {
            error.flag(REDECLARED_IDENTIFIER, 
                       ctx.getStart().getLine(), procFuncName);
            return null;
        }

        procFuncEntry = symtabStack.enterLocal(
                procFuncName, isFunctionDefinition ? FUNCTION : PROCEDURE);
        procFuncEntry.setProcFuncCode(DECLARED);
        idCtx.entry = procFuncEntry;
        
        Typespec.addRecordPath(procFuncName);

        // Append to the parent's list of subroutines.
        SymtabEntry parentEntry = symtabStack.getLocalSymtab().getOwner();
        parentEntry.appendProcFunc(procFuncEntry);

        procFuncEntry.setProcFuncSymtab(symtabStack.push());
        idCtx.entry = procFuncEntry;

        Symtab symtab = symtabStack.getLocalSymtab();
        symtab.setOwner(procFuncEntry);

        if (parameters != null)
        {
            ArrayList<SymtabEntry> parameterEntries = 
                    (ArrayList<SymtabEntry>) 
                        visit(parameters.parameterDeclarationsList());
            procFuncEntry.setProcFuncParameters(parameterEntries);

            for (SymtabEntry parmEntry : parameterEntries)
            {
                parmEntry.setSlotNumber(symtab.getNextSlotNumber());

                if (parmEntry.getTypespec() == Predefined.realType)
                {
                    symtab.getNextSlotNumber();
                }
            }
        }

        if (isFunctionDefinition)
        {
            TypeIdentifierContext typeIdCtx = funcCtx.typeIdentifier();
            visit(typeIdCtx);
            returnTypespec = typeIdCtx.identifier().typespec;

//            if (returnTypespec.getForm() != SCALAR)
//            {
//                error.flag(INVALID_RETURN_TYPE, typeIdCtx);
//                returnTypespec = Predefined.integerType;
//            }

            procFuncEntry.setTypespec(returnTypespec);
            idCtx.typespec = returnTypespec;
        } 
        else
        {
            idCtx.typespec = null;
        }

        visit(ctx.block().declarations());

        // Enter the function's associated variable into its symbol table.
        if (isFunctionDefinition)
        {
            SymtabEntry assocVarEntry = symtabStack.enterLocal(procFuncName, 
                                                               VARIABLE);
            assocVarEntry.setSlotNumber(symtab.getNextSlotNumber());
            assocVarEntry.setTypespec(returnTypespec);
            
            if (returnTypespec == Predefined.realType)
            {
                symtab.getNextSlotNumber();
            }
        }

        visit(ctx.block().compoundStatement());
        procFuncEntry.setExecutable(ctx.block().compoundStatement());

        symtabStack.pop();
        return null;
    }

    @SuppressWarnings("unchecked")
    Object parameterDeclarationsList(ParameterDeclarationsListContext ctx)
    {
        ArrayList<SymtabEntry> parameterList = new ArrayList<>();

        // Loop over the parameter declarations.
        for (ParameterDeclarationsContext dclCtx : 
                                        ctx.parameterDeclarations())
        {
            ArrayList<SymtabEntry> parameterSublist = 
                            (ArrayList<SymtabEntry>) visit(dclCtx);
            parameterList.addAll(parameterSublist);
        }

        return parameterList;
    }

    Object parameterDeclarations(ParameterDeclarationsContext ctx)
    {
        Kind kind = ctx.VAR() != null ? REFERENCE_PARAMETER 
                                      : VALUE_PARAMETER;
        TypeIdentifierContext typeIdCtx = ctx.typeIdentifier();
        Typespec parmTypespec = (Typespec) visit(typeIdCtx);
        ArrayList<SymtabEntry> parameterSublist = new ArrayList<>();

        // Loop over the parameter identifiers.
        ParameterIdentifierListContext parmListCtx = ctx.parameterIdentifierList();
        for (IdentifierContext parmIdCtx : parmListCtx.identifier())
        {
            int lineNumber = parmIdCtx.getStart().getLine();
            String parmName = parmIdCtx.IDENTIFIER().getText().toLowerCase();
            SymtabEntry parmEntry = symtabStack.lookupLocal(parmName);

            if (parmEntry == null)
            {
                parmEntry = symtabStack.enterLocal(parmName, kind);
                parmEntry.setTypespec(parmTypespec);

                if (   (kind == REFERENCE_PARAMETER) 
                    && (mode != EXECUTOR) 
                    && (parmTypespec.getForm() == SCALAR))
                {
                    error.flag(INVALID_REFERENCE_PARAMETER, parmIdCtx);
                }
            } 
            else
            {
                error.flag(REDECLARED_IDENTIFIER, parmIdCtx);
            }

            parmIdCtx.entry = parmEntry;
            parmIdCtx.typespec = parmTypespec;

            parameterSublist.add(parmEntry);
            parmEntry.appendLineNumber(lineNumber);
        }

        return parameterSublist;
    }
}
