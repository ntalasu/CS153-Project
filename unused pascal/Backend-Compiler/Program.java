package backend.compiler;

import java.util.Map;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.Symtab;
import intermediate.symtab.SymtabEntry;
import intermediate.type.Typespec;

import static backend.compiler.Directive.*;
import static backend.compiler.Instruction.*;
import static intermediate.semantics.Semantics.programEntry;
import static intermediate.symtab.SymtabEntry.Kind.*;
import static intermediate.type.Typespec.Form.*;

public class Program extends Compiler
{
    /**
     * Emit a Jasmin class file for a Pascal program.
     * @param ctx the ProgramContext.
     * @return null.
     */
    Object program(ProgramContext ctx)
    {
        Symtab symtab = programEntry.getProcFuncSymtab();
        code = new JasminEmitter(programName);
        DataInitializer data = new DataInitializer(code);
        
        data.generateClassFile(symtab, true);
        objectFileNames.add(programName + ".j");
        
        ProcedureAndFunctionsPartContext procedureAndFunctionsPart =
                ctx.block().declarations().procedureAndFunctionsPart();
        if (procedureAndFunctionsPart != null)
        {
            visit(procedureAndFunctionsPart);
        }
        
        emitMainMethod(ctx);
        code.closeObjectFile();      
        
        emitRecords();
        return null;
    }
    
    /**
     * Emit a Jasmin class file for a Pascal record type.
     */
    private void emitRecords()
    {
        Symtab symtab = programEntry.getProcFuncSymtab();
        for (Map.Entry<String, SymtabEntry> mapEntry : symtab.entrySet())
        {            
            SymtabEntry entry = (SymtabEntry) mapEntry.getValue();
            
            if (entry.getKind() == TYPE) 
            {
                Typespec typespec = entry.getTypespec();
                if (typespec.getForm() == RECORD)
                {
                    String recordPath = typespec.getRecordPath();
                    code = new JasminEmitter(recordPath);
                    
                    DataInitializer data = new DataInitializer(code);
                    data.generateClassFile(typespec.getRecordSymtab(), false);
                    
                    objectFileNames.add(recordPath + ".j");
                    code.closeObjectFile();
                }
            }
        }
    }

    /**
     * Emit code for the program body as the main method.
     * @param ctx the ProgramContext.
     */
    private void emitMainMethod(ProgramContext ctx)
    {
        LocalStack localStack = new LocalStack();
        code.setLocalStack(localStack);
        
        code.emitLine();
        code.emitComment("MAIN");
        code.emitDirective(METHOD_PUBLIC_STATIC, 
                              "main([Ljava/lang/String;)V");

        emitMainPrologue(programEntry);

        // Emit code for the compound statement.
        code.emitLine();
        visit(ctx.block().compoundStatement());
        
        emitMainEpilogue();
    }

    /**
     * Emit the main method prologue.
     * @parm programEntry the symbol table entry for the program name.
     */
    private void emitMainPrologue(SymtabEntry programEntry)
    {
        LocalStack localStack = code.getLocalStack();

        code.emitDirective(VAR, "0 is args [Ljava/lang/String;");
        code.emitDirective(VAR, "1 is _start Ljava/time/Instant;");
        code.emitDirective(VAR, "2 is _end Ljava/time/Instant;");
        code.emitDirective(VAR, "3 is _elapsed J");
        
        // Runtime timer.
        code.emitLine();
        code.emit(INVOKESTATIC, "java/time/Instant/now()Ljava/time/Instant;");
        localStack.increase(1);
        code.emit(ASTORE_1);
    }

    /**
     * Emit the main method epilogue.
     */
    private void emitMainEpilogue()
    {
        LocalStack localStack = code.getLocalStack();
    
        // Print the execution time.
        code.emitLine();
        code.emit(INVOKESTATIC, 
                  "java/time/Instant/now()Ljava/time/Instant;");
        localStack.increase(1);
        code.emit(ASTORE_2);           
        code.emit(ALOAD_1);             
        code.emit(ALOAD_2);             
        code.emit(INVOKESTATIC, 
              "java/time/Duration/between(Ljava/time/temporal/Temporal;" +
              "Ljava/time/temporal/Temporal;)Ljava/time/Duration;");
        localStack.decrease(1);
        code.emit(INVOKEVIRTUAL, "java/time/Duration/toMillis()J");
        localStack.increase(2);
        code.emit(LSTORE_3);   
        
        code.emit(GETSTATIC, "java/lang/System/out Ljava/io/PrintStream;");
        code.emit(LDC, "\"\\n[%,d milliseconds execution time.]\\n\"");
        code.emit(ICONST_1);             
        code.emit(ANEWARRAY, "java/lang/Object");
        code.emit(DUP);                 
        code.emit(ICONST_0);         
        code.emit(LLOAD_3);             
        code.emit(INVOKESTATIC, 
                  "java/lang/Long/valueOf(J)Ljava/lang/Long;");
        code.emit(AASTORE);        
        code.emit(INVOKEVIRTUAL, 
                  "java/io/PrintStream/printf(Ljava/lang/String;" +
                  "[Ljava/lang/Object;)Ljava/io/PrintStream;");
        localStack.decrease(2);
        code.emit(POP);          
    
        code.emitLine();
        code.emit(RETURN);
        code.emitLine();
        
        code.emitDirective(LIMIT_LOCALS, 5);
        code.emitDirective(LIMIT_STACK,  localStack.capacity());
        code.emitDirective(END_METHOD);
    }
}