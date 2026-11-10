package intermediate.symtab;

import java.util.ArrayList;

import intermediate.symtab.SymtabEntry.StandardCode;
import intermediate.type.*;

import static intermediate.symtab.SymtabEntry.Kind.*;
import static intermediate.symtab.SymtabEntry.ProcFuncCode.*;
import static intermediate.symtab.SymtabEntry.StandardCode.*;
import static intermediate.type.Typespec.Form.*;

public class Predefined
{
    private static SymtabStack symtabStack;
    
    public static Typespec integerType;
    public static Typespec realType;
    public static Typespec booleanType;
    public static Typespec charType;
    public static Typespec stringType;
    public static Typespec undefinedType;

    public static void initialize(SymtabStack symtabStack)
    {
        Predefined.symtabStack = symtabStack;
        
        initializeTypes();
        initializeConstants();
        initializeBuiltIns();
    }

    private static void initializeTypes()
    {
        SymtabEntry integerEntry = symtabStack.enterLocal("integer", TYPE);
        integerType = new Typespec(SCALAR);
        integerType.setIdentifier(integerEntry);
        integerEntry.setTypespec(integerType);

        SymtabEntry realEntry = symtabStack.enterLocal("real", TYPE);
        realType = new Typespec(SCALAR);
        realType.setIdentifier(realEntry);
        realEntry.setTypespec(realType);

        SymtabEntry charEntry = symtabStack.enterLocal("char", TYPE);
        charType = new Typespec(SCALAR);
        charType.setIdentifier(charEntry);
        charEntry.setTypespec(charType);

        SymtabEntry booleanEntry = symtabStack.enterLocal("boolean", TYPE);
        booleanType = new Typespec(ENUMERATED);
        booleanType.setIdentifier(booleanEntry);
        booleanEntry.setTypespec(booleanType);

        SymtabEntry stringEntry = symtabStack.enterLocal("string", TYPE);
        stringType = new Typespec(STRING);
        stringType.setIdentifier(stringEntry);
        stringEntry.setTypespec(stringType);

        undefinedType = new Typespec(UNKNOWN);
    }

    private static void initializeConstants()
    {
        SymtabEntry falseEntry = 
                    symtabStack.enterLocal("false", ENUMERATED_CONSTANT);
        falseEntry.setTypespec(booleanType);
        falseEntry.setValue(0);

        SymtabEntry trueEntry = 
                    symtabStack.enterLocal("true", ENUMERATED_CONSTANT);
        trueEntry.setTypespec(booleanType);
        trueEntry.setValue(1);

        ArrayList<SymtabEntry> constants = 
                            booleanType.getEnumeratedConstants();
        constants.add(falseEntry);
        constants.add(trueEntry);
    }
    
    private static void initializeBuiltIns()
    {
        enterStandard("abs",    ABS,    null,        null);
        enterStandard("arctan", ARCTAN, realType,    realType);
        enterStandard("chr",    CHR,    integerType, charType);
        enterStandard("cos",    COS,    realType,    realType);
        enterStandard("eof",    EOF,    null,        booleanType);
        enterStandard("eoln",   EOLN,   null,        booleanType);
        enterStandard("exp",    EXP,    realType,    realType);
        enterStandard("ln",     LN,     realType,    realType);
        enterStandard("odd",    ODD,    integerType, booleanType);
        enterStandard("ord",    ORD,    null,        integerType);
        enterStandard("pred",   PRED,   null,        null);
        enterStandard("round",  ROUND,  realType,    integerType);
        enterStandard("sin",    SIN,    realType,    realType);
        enterStandard("sqr",    SQR,    null,        null);
        enterStandard("sqrt",   SQRT,   realType,    realType);
        enterStandard("succ",   SUCC,   null,        null);
        enterStandard("trunc",  TRUNC,  realType,    integerType);
    }
    
    private static void enterStandard(String name, 
                                      StandardCode standardCode,
                                      Typespec parmTypespec,
                                      Typespec resultTypespec)
    {
        SymtabEntry entry = symtabStack.enterLocal(name, STANDARD);
        entry.setProcFuncCode(LIBRARY);
        entry.setStandardCode(standardCode);
//        entry.setStandardParmTypespec(parmTypespec);
//        entry.setStandardResultTypespec(resultTypespec);
    }
}
