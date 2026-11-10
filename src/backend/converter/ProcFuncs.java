package backend.converter;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.Symtab;
import intermediate.type.Typespec;

import static intermediate.type.Typespec.Form.*;

public class ProcFuncs extends Converter
{
    Object procFuncDefinition(ProcFuncDefinitionContext ctx)
    {
        FunctionHeadContext funcHeadCtx = ctx.functionHead();
        ProcedureHeadContext procHeadCtx = ctx.procedureHead();
        IdentifierContext idCtx = null;
        ParametersContext parmsCtx = null;
        boolean functionDefinition = funcHeadCtx != null;
        String routineName;

        programDeclarations = false;
        code.emitLine();
        code.emitStart("static ");

        if (functionDefinition)
        {
            idCtx = funcHeadCtx.identifier();
            parmsCtx = funcHeadCtx.parameters();
            visit(funcHeadCtx.typeIdentifier());
        } 
        else
        {
            idCtx = procHeadCtx.identifier();
            parmsCtx = procHeadCtx.parameters();
            code.emit("void");
        }

        routineName = idCtx.entry.getName();
        code.emit(" " + routineName);

        code.emit("(");
        if (parmsCtx != null) visit(parmsCtx);
        code.emitEnd(")");
        code.emitLine("{");
        code.indent();

        if (functionDefinition)
        {
            // Function associated variable.
            code.emitStart();
            visit(funcHeadCtx.typeIdentifier());
            code.emitEnd(" " + routineName + ";");
        }

        Symtab procFuncSymtab = idCtx.entry.getProcFuncSymtab();
        typesImpl.emitRecordDefinitions(procFuncSymtab);

        DeclarationsContext declCtx = ctx.block().declarations();
        visit(declCtx);

        code.emitLine();
        visit(ctx.block().compoundStatement().statementList());

        if (functionDefinition)
        {
            // Return function value.
            code.emitLine();
            code.emitLine("return " + routineName + ";");
        }

        code.dedent();
        code.emitLine("}");

        return null;
    }
    
    Object parameterDeclarations(ParameterDeclarationsContext ctx) 
    {
        ParameterIdentifierListContext parmListCtx = 
                                                ctx.parameterIdentifierList();
        TypeIdentifierContext typeIdCtx = ctx.typeIdentifier();
        Typespec parmTypespec = typeIdCtx.identifier().typespec;
        
        // Loop over the parameters.
        for (IdentifierContext parmIdCtx : parmListCtx.identifier())
        {
            code.emit(currentSeparator);
            
            visit(typeIdCtx);
            code.emit(" " + parmIdCtx.entry.getName());
            
            if (parmTypespec.getForm() == ARRAY) 
            {
                Types.emitEmptyArrayBrackets(parmTypespec);
            }
            currentSeparator = ", ";
        }
        
        return null;
    }
}
