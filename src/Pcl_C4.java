import java.util.ArrayList;
import java.io.InputStream;

import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import frontend.Listing;
import frontend.SyntaxErrorHandler;
import intermediate.antlr4.Pcl_C4Parser;
import intermediate.antlr4.Pcl_C4Lexer;
import intermediate.semantics.Semantics;
import backend.BackendMode;
import backend.compiler.Compiler;

import static backend.BackendMode.Mode.*;

public class Pcl_C4 
{
    public static void main(String[] args) throws Exception 
    {
        BackendMode.mode = EXECUTOR;
        String option = args[0];
        
        if      (option.equals("-convert")) BackendMode.mode = CONVERTER;
        else if (option.equals("-execute")) BackendMode.mode = EXECUTOR;
        else if (option.equals("-debug"  )) BackendMode.mode = DEBUGGER;
        else if (option.equals("-compile")) BackendMode.mode = COMPILER;
        else
        {
            System.out.println("ERROR: Invalid option.");
            System.out.println(
                    "   option: -convert, -execute, -debug, or -compile");
        }

        String sourceFileName = args[1];
        
        try
        {
            Listing lstng = new Listing(sourceFileName);
            lstng.print();
        }
        catch(IOException ex)
        {
            System.out.printf("ERROR: %s\n", ex.getMessage());
            System.exit(-1);
        }        
        
        InputStream source = 
                new FileInputStream(sourceFileName);
        CharStream cs = CharStreams.fromStream(source);
        
        SyntaxErrorHandler syntaxErrorHandler = new SyntaxErrorHandler();

        System.out.printf("\nPASS 1 Syntax:\n");
        
        Pcl_C4Lexer lexer = new Pcl_C4Lexer(cs);
        lexer.removeErrorListeners();
        lexer.addErrorListener(syntaxErrorHandler);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Pcl_C4Parser parser = new Pcl_C4Parser(tokens);

        parser.removeErrorListeners();
        parser.addErrorListener(syntaxErrorHandler);

        ParseTree tree = parser.program();
        
        int errorCount = syntaxErrorHandler.getCount();
        if (errorCount > 0) 
        {
            System.out.printf("\nThere were %d syntax errors.\n", 
                              errorCount);
            return;
        }
        else
        {
            System.out.println("There were no syntax errors.");
        }
        
        System.out.printf("\nPASS 2 Semantics:\n");
        
        Semantics pass2 = new Semantics();
        pass2.visit(tree);
        
        errorCount = pass2.getErrorCount();
        if (errorCount > 0)
        {
            System.out.printf("\nThere were %d semantic errors.\n", 
                              errorCount);
            System.out.println("Object file not created or modified.");
            return;
        }
        else
        {
            System.out.println("There were no semantic errors.");
        }
        
        switch (BackendMode.mode)
        {
            case CONVERTER:
            case EXECUTOR:
            case DEBUGGER:
            {
                System.out.println("\nOnly -compile is implemented so far.");
                break;
            }

            case COMPILER:
            {
                // Compile the Pascal program.
                System.out.printf("\nPASS 3 Compilation: ");
                Compiler pass3 = new Compiler();
                pass3.visit(tree);
                
                ArrayList<String> objectFileNames = 
                                        pass3.getObjectFileNames();
                int size = objectFileNames.size();
                
                System.out.printf("Object file \"%s\" created.\n",
                                  objectFileNames.get(0));
                
                if (size > 1)
                {
                    System.out.println();
                    System.out.printf("       Record files created: %s\n",
                                      objectFileNames.get(1));
                    for (int i = 2; i < size; ++i)
                    {
                        System.out.print ("                             ");
                        System.out.printf("%s\n", objectFileNames.get(i));
                    }
                    System.out.println();
                }
                break;
            }
        }
    }
}
