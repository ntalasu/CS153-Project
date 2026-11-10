package intermediate.symtab;

import java.util.ArrayList;

import intermediate.type.Typespec;

public class SymtabEntry
{
    public enum Kind
    {
        CONSTANT, ENUMERATED_CONSTANT, TYPE, VARIABLE, RECORD_FIELD,
        VALUE_PARAMETER, REFERENCE_PARAMETER, PROGRAM_PARAMETER,
        PROGRAM, PROCEDURE, FUNCTION, STANDARD, UNDEFINED;
        
        public String toString() 
        { 
            return super.toString().toLowerCase(); 
        }
    }

    public enum ProcFuncCode
    {
        DECLARED, FORWARD, LIBRARY
    }
    
    public enum StandardCode
    {
        ABS, ARCTAN, CHR, COS, EOF, EOLN, EXP, LN, ODD, ORD,
        PRED, ROUND, SIN, SQR, SQRT, SUCC, TRUNC
    }

    private interface EntryInfo {}
    
    private class ValueInfo implements EntryInfo
    {
        private Object value;
    }
    
    private class ProcFuncInfo implements EntryInfo
    {
        private ProcFuncCode procFuncCode;
        private StandardCode standardCode;
        private Symtab symtab;                       // proc/func symbol table
        private ArrayList<SymtabEntry> parameters;   // proc/func formal parameters
        private ArrayList<SymtabEntry> procFuncs;    // nested procs and funcs
        private Object executable;                   // executable code
    }

    private String name;
    private Kind kind;
    private Typespec typespec;
    private Symtab symtab;
    private SymtabEntry parentEntry;
    private int slotNumber;
    private ArrayList<Integer> lineNumbers;
    private EntryInfo info;

    public SymtabEntry(String name, Kind kind, Symtab symtab)
    {
        this.name = name;
        this.kind = kind;
        this.symtab = symtab;
        lineNumbers = new ArrayList<>();

        // Initialize the appropriate entry information.
        switch (kind)
        {
            case CONSTANT:
            case ENUMERATED_CONSTANT:
            case VARIABLE:
            case RECORD_FIELD:
            case VALUE_PARAMETER:
            case UNDEFINED:
                info = new ValueInfo();
                break;
                
            case PROGRAM:
            case PROCEDURE:
            case FUNCTION:
            case STANDARD:
                info = new ProcFuncInfo();
                ((ProcFuncInfo) info).parameters = 
                                        new ArrayList<SymtabEntry>();
                ((ProcFuncInfo) info).procFuncs =
                                        new ArrayList<SymtabEntry>();
                break;
                
//            case STANDARD:
//                info = new StandardInfo();
//                ((StandardInfo) info).parmTypespec = null;
//                ((StandardInfo) info).resultTypespec = null;
//                
//                break;
            
            default: break;
        }
    }
    
    public String getName()       { return name; }
    public Typespec getTypespec() { return typespec; }
    
    public SymtabEntry getParentEntry() { return parentEntry; }
    
    public void setParentEntry(SymtabEntry parentEntry) 
    { 
        this.parentEntry = parentEntry; 
    }
    
    public void setTypespec(Typespec typespec) 
    { 
        this.typespec = typespec; 
    }
    
    public Kind getKind()          { return kind; }
    public void setKind(Kind kind) { this.kind = kind; }
    
    public Symtab getSymtab() { return symtab; }

    public int getSlotNumber() { return slotNumber; }
    
    public void setSlotNumber(int slotNumber) 
    { 
        this.slotNumber = slotNumber; 
    }
    
    public ArrayList<Integer> getLineNumbers() { return lineNumbers; }

    public void appendLineNumber(int lineNumber)
    {
        lineNumbers.add(lineNumber);
    }

    public Object getValue() { return ((ValueInfo) info).value; }
    
    public void setValue(Object value) 
    { 
        ((ValueInfo) info).value = value; 
    }
    
    public ProcFuncCode getProcFuncCode() 
    { 
        return ((ProcFuncInfo) info).procFuncCode; 
    }
    
    public void setProcFuncCode(ProcFuncCode code) 
    { 
        ((ProcFuncInfo) info).procFuncCode = code; 
    }
    
    public Symtab getProcFuncSymtab()  
    { 
        return ((ProcFuncInfo) info).symtab; 
    }

    public void setProcFuncSymtab(Symtab symtab) 
    { 
        ((ProcFuncInfo) info).symtab = symtab; 
    }

    public StandardCode getStandardCode()
    { 
        return ((ProcFuncInfo) info).standardCode;
    }
    
    public void setStandardCode(StandardCode code)
    {
        ((ProcFuncInfo) info).standardCode = code;
    }
    
//    public Typespec getStandardParmTypespec()
//    {
//        return ((StandardInfo) info).parmTypespec;
//    }
//    
//    public void setStandardParmTypespec(Typespec typespec)
//    {
//        ((StandardInfo) info).parmTypespec = typespec;
//    }
//    
//    public Typespec getStandardResultTypespec()
//    {
//        return ((StandardInfo) info).resultTypespec;
//    }
//    
//    public void setStandardResultTypespec(Typespec typespec)
//    {
//        ((StandardInfo) info).resultTypespec = typespec;
//    }
    
    public ArrayList<SymtabEntry> getProcFuncParameters() 
    { 
        return ((ProcFuncInfo) info).parameters; 
    }

    public void setProcFuncParameters(ArrayList<SymtabEntry> parameters)
    {
        ((ProcFuncInfo) info).parameters = parameters;
    }
    public ArrayList<SymtabEntry> getProcFuncs() 
    { 
        return ((ProcFuncInfo) info).procFuncs; 
    }

    public void appendProcFunc(SymtabEntry subroutineEntry)
    {
        ((ProcFuncInfo) info).procFuncs.add(subroutineEntry);
    }
    
    public Object getExecutable() 
    { 
        return ((ProcFuncInfo) info).executable; 
    }

    public void setExecutable(Object executable)
    {
        ((ProcFuncInfo) info).executable = executable;
    }
}
