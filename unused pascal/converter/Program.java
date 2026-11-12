package backend.converter;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.Symtab;

public class Program extends Converter
{
    Object program(ProgramContext ctx)
    {
        code.emitLine("public class " + programName);
        code.emitLine("{");
        code.indent();
        
        IdentifierContext programIdCtx = ctx.programHeader().identifier();
        Symtab programSymtab = programIdCtx.entry.getProcFuncSymtab();
        
        typesImpl.emitRecordDefinitions(programSymtab);        
        
        DeclarationsContext declCtx = ctx.block().declarations();
        ConstantsPartContext constantsPartCtx = declCtx.constantsPart();
        TypesPartContext typesPartCtx = declCtx.typesPart();
        ProcedureAndFunctionsPartContext procedureAndFunctionsPartCtx =
                                    declCtx.procedureAndFunctionsPart();
        VariablesPartContext variablesPartCtx = declCtx.variablesPart();
        
        if (constantsPartCtx != null) visit(constantsPartCtx); 
        if (typesPartCtx     != null) visit(typesPartCtx);
        if (variablesPartCtx != null) visit(variablesPartCtx);
        
        DataInitializer data = new DataInitializer(code);
        data.emitStaticInitializations(programSymtab);
        
        if (procedureAndFunctionsPartCtx != null)
        {
            visit(procedureAndFunctionsPartCtx);
        }
        
        // Main.
        code.emitLine();
        code.emitLine("public static void main(String[] args)");        
        code.emitLine("{");
        code.indent();
        
        // Execution timer.
        code.emitLine("java.time.Instant _start = java.time.Instant.now();");
        
        // Main compound statement.
        visit(ctx.block().compoundStatement().statementList());
        
        // Print the execution time.
        code.emitLine();
        code.emitLine("java.time.Instant _end = java.time.Instant.now();");
        code.emitLine("long _elapsed = java.time.Duration." +
                      "between(_start, _end).toMillis();");
        code.emitLine("System.out.printf(\"\\n[%,d milliseconds execution time.]" +
                                         "\\n\", _elapsed);");
         
        code.dedent();
        code.emitLine("}");

        code.dedent();
        code.emitLine("}");

        code.close();
        return null;
    }
}
