package backend.converter;

import intermediate.symtab.*;
import intermediate.type.*;
import intermediate.symtab.SymtabEntry.Kind;
import intermediate.type.Typespec.Form;

import static intermediate.symtab.SymtabEntry.Kind.*;
import static intermediate.type.Typespec.Form.*;

public class DataInitializer
{
    private JavaEmitter code;

    /**
     * Constructor.
     * @param the parent generator.
     */
    public DataInitializer(JavaEmitter code)
    {
        this.code = code;
    }
    
    private boolean initializationsNeeded(Symtab symtab)
    {
        for (SymtabEntry entry : symtab.getUnsorted())
        {
            Kind kind = entry.getKind();
            
            if ((kind == VARIABLE) || (kind == RECORD_FIELD))
            {
                Form form = entry.getTypespec().getForm();                
                if ((form == RECORD) || (form == ARRAY)) return true;
            }
        }

        return false;
    }

    /**
     * Emit a program's static initializations.
     * @param programSymtab the program's symbol table.
     */
    void emitStaticInitializations(Symtab programSymtab)
    {
        if (!initializationsNeeded(programSymtab)) return;
        
        code.emitLine();
        code.emitStart("static");
        code.emitStart("{");
        code.indent();

        emitInitializations(programSymtab, false);
        
        code.dedent();
        code.emitStart("}");
    }
    
    /**
     * Emit a record class's constructor.
     * @param recordEntry the record's symbol table entry.
     */
    void emitConstructor(SymtabEntry recordEntry)
    {
        Typespec recordTypespec = recordEntry.getTypespec();
        Symtab symtab = recordTypespec.getRecordSymtab();
        
        if (!initializationsNeeded(symtab)) return;
        
        code.emitLine();
        code.emitStart(recordTypespec.getRecordPath() + "()");
        code.emitStart("{");
        code.indent();

        emitInitializations(symtab, false);
        
        code.dedent();
        code.emitStart("}");
    }
    
    /**
     * Emit initializations of records and arrays.
     * @param symtab the symbol table containing the records and arrays.
     * @param includeType if true, include the data type.
     */
    void emitInitializations(Symtab symtab, boolean includeType)
    {
        // Loop over all the symbol table's identifiers to emit
        // data allocation code for array and record variables.
        for (SymtabEntry entry : symtab.getUnsorted())
        {
            Kind kind = entry.getKind();
            
            if ((kind == VARIABLE) || (kind == RECORD_FIELD))
            {
                Typespec typespec = entry.getTypespec();
                if (typespec.getForm() == RECORD) 
                {
                    emitNewRecord(entry, includeType);
                }
            }
        }

        for (SymtabEntry entry : symtab.getUnsorted())
        {
            Kind kind = entry.getKind();
            
            if ((kind == VARIABLE) || (kind == RECORD_FIELD))
            {
                Typespec typespec = entry.getTypespec();
                if (typespec.getForm() == ARRAY) 
                {
                    emitNewArray(entry, includeType);
                    emitAllocateArray(entry);
                }
            }
        }
    }
    
    /**
     * Emit a new record.
     * @param recordEntry the record's symbol table entry.
     * @param includeType if true, include the data type.
     */
    void emitNewRecord(SymtabEntry recordEntry, 
                               boolean includeType)
    {
        Typespec recordTypespec = recordEntry.getTypespec();
        String javaTypeName = Types.javaTypeName(recordTypespec);
        
        code.emitStart();
        if (includeType) 
        {
            code.emit(Types.javaTypeName(recordTypespec) + " ");
        }
        code.emitEnd(recordEntry.getName().toLowerCase() 
                     + " = new " + javaTypeName + "();");
    }
    
    /**
     * Emit a new array.
     * @param arrayEntry the array's symbol table entry.
     * @param includeType if true, include the data type.
     */
    void emitNewArray(SymtabEntry arrayEntry,
                              boolean includeType)
    {
        Typespec arrayTypespec = arrayEntry.getTypespec();
        String javaTypeName = Types.javaTypeName(arrayTypespec);

        code.emitStart();
        if (includeType) 
        {
            code.emit(Types.javaTypeName(arrayTypespec) + " ");
        }
        code.emit(arrayEntry.getName().toLowerCase());
        if (includeType) Types.emitEmptyArrayBrackets(arrayTypespec);
        code.emit(" = new " + javaTypeName);
        
        while (arrayTypespec.getForm() == ARRAY)
        {
            int elmtCount = arrayTypespec.getArrayElementCount();
            code.emit("[" + elmtCount + "]");
            arrayTypespec = arrayTypespec.getArrayElementType();
        }
        
        code.emitEnd(";");
    }

    /**
     * Emit code to allocate an array's elements.
     * @param arrayEntry the array's symbol table entry.
     */
    private void emitAllocateArray(SymtabEntry arrayEntry)
    {
        Typespec arrayTypespec = arrayEntry.getTypespec();
        Typespec elmtTypespec  = arrayTypespec.getArrayElementType();
        Form form = elmtTypespec.getForm();
        
        while (form == ARRAY)
        {
            elmtTypespec = elmtTypespec.getArrayElementType();
            form = elmtTypespec.getForm();
        }

        if (form == RECORD)
        {
            String arrayName = arrayEntry.getName();
            emitNewArrayElement(arrayName, arrayTypespec);
        }
    }
    
    /**
     * Emit code to allocate an array's elements.
     * @param arrayName the name of the array.
     * @param elmtTypespec the element's datatype.
     */
    private void emitNewArrayElement(String arrayName, 
                                     Typespec elmtTypespec)
    {        
        int dimensionCount = 0;

        do
        {
            int elmtCount = elmtTypespec.getArrayElementCount();
            ++dimensionCount;
            String index = "_i" + dimensionCount;
            arrayName += "[" + index + "]";
            
            code.emitLine("for (int " + index + " = 0; " +
                          index + " < " + elmtCount +
                          "; " + index + "++)");
            code.indent();
            
            elmtTypespec = elmtTypespec.getArrayElementType();
        } while (elmtTypespec.getForm() == ARRAY);
        
        String typeName = Types.javaTypeName(elmtTypespec);
        code.emitStart(arrayName + " = new " + typeName + "()");
        code.emitEnd(";");
        
        while (--dimensionCount >= 0) code.dedent();
    }
}
