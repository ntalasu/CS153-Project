/**
 * <h1>StructuredDataGenerator</h1>
 *
 * <p>Generate code to allocate arrays, records, and strings.</p>
 *
 * <p>Copyright (c) 2024 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
package backend.compiler;

import intermediate.symtab.*;
import intermediate.symtab.SymtabEntry.Kind;
import intermediate.type.*;
import intermediate.type.Typespec.Form;

import static intermediate.symtab.SymtabEntry.Kind.*;
import static intermediate.type.Typespec.Form.*;
import static backend.compiler.Instruction.*;
import static backend.compiler.Directive.*;

public class DataInitializer
{
    private JasminEmitter code;
    private boolean isProgram;

    /**
     * Constructor.
     * @param the parent generator.
     */
    DataInitializer(JasminEmitter code)
    {
        this.code = code;
        this.isProgram = true;
    }

    void emitLocalInitializations(Symtab symtab)
    {
        for (SymtabEntry entry : symtab.getUnsorted())
        {
            Kind kind = entry.getKind();
            
            if ((kind == VARIABLE) || (kind == RECORD_FIELD))
            {
                Typespec typespec = entry.getTypespec();
                Form form = typespec.getForm();
                int slot = entry.getSlotNumber();
                
                if (form == RECORD)
                {
                    emitInitializeRecord(entry, entry.getTypespec(),
                                         ASTORE, slot);
                }
                else if (form == ARRAY)
                {
                    emitInitializeArray(entry, 0);
                }
            }
        }
    }
    
    void generateClassFile(Symtab symtab, boolean isProgram)
    {
        this.isProgram = isProgram;
        
        String name = code.getClassName();        
        code.emitDirective(CLASS_PUBLIC, name);
        code.emitDirective(SUPER, "java/lang/Object");

        emitProgramVariables(symtab);        
        if (isProgram) emitClassInitializer(symtab);      
        emitConstructor(symtab);   
    }
    
    private void emitClassInitializer(Symtab symtab)
    {
        int maxDimensions = 0;
        
        LocalStack localStack = new LocalStack();
        code.setLocalStack(localStack);
        
        code.emitLine();
        code.emitComment("Class initializer");
        code.emitDirective(METHOD_STATIC, "<clinit>()V");
        code.emitLine();
        
        code.emit(NEW, "java/util/Scanner");
        code.emit(DUP);
        code.emit(GETSTATIC, 
                  "java/lang/System/in Ljava/io/InputStream;");
        localStack.increase(1);
        code.emit(INVOKESPECIAL, 
                  "java/util/Scanner/<init>(Ljava/io/InputStream;)V");
        code.emit(PUTSTATIC, 
                  code.getClassName() + "/_sysin Ljava/util/Scanner;");
        localStack.decrease(1);
    
        // Loop over all the symbol table's identifiers to emit
        // data allocation code for array and record variables.
        for (SymtabEntry entry : symtab.getUnsorted())
        {
            if (entry.getKind() == VARIABLE) 
            {
                Form form = entry.getTypespec().getForm();
                if (form == RECORD) 
                {
                    emitInitializeRecord(entry, entry.getTypespec(), 
                                         null, -1);
                }
                else if (form == ARRAY)  
                {
                    maxDimensions = emitInitializeArray(entry, maxDimensions);
                }
            }
        }
        
        code.emitLine();
        code.emit(RETURN);
        
        code.emitLine();
        code.emitDirective(LIMIT_LOCALS, maxDimensions + 1);
        code.emitDirective(LIMIT_STACK,  localStack.capacity());
        code.emitDirective(END_METHOD);
    }

    /**
     * Emit code for the program's constructor.
     */
    private void emitConstructor(Symtab symtab)
    {
        LocalStack localStack = new LocalStack();
        code.setLocalStack(localStack);
        
        code.emitLine();
        code.emitComment("Class constructor");
        code.emitDirective(METHOD_PUBLIC, "<init>()V");                
        code.emitDirective(VAR, 
                "0 is this L" + code.getClassName() + ";");
        code.emitLine();

        code.emit(ALOAD_0);
        code.emit(INVOKESPECIAL, "java/lang/Object/<init>()V");
        code.emitLine();

        int maxDimensions = 0;
        if (!isProgram)
        {
            maxDimensions = emitRecordFields(symtab);
        }
        
        code.emit(RETURN);
        code.emitLine();
        code.emitDirective(LIMIT_LOCALS, maxDimensions + 1);
        code.emitDirective(LIMIT_STACK, localStack.capacity());
        code.emitDirective(END_METHOD);
    }
    
    private int emitRecordFields(Symtab symtab)
    {
        int maxDimensions = 0;
        
        for (SymtabEntry entry : symtab.getUnsorted())
        {
            Typespec typespec = entry.getTypespec();
            Form form = typespec.getForm();
            
            if (form == RECORD)
            {
                String path = typespec.getRecordPath();
                
                code.emit(ALOAD_0);
                code.emit(NEW, path);
                code.emit(DUP);
                code.emit(INVOKESPECIAL, path + "/<init>()V");
                code.emit(PUTFIELD, 
                          code.getClassName() + "/" + entry.getName(), 
                          "L" + path +";");
                code.getLocalStack().decrease(1);
            }
            else if (form == ARRAY)
            {
                code.emit(ALOAD_0);
                maxDimensions = emitInitializeArray(entry, 0);
            }
        }
        
        return maxDimensions;
    }
   
    /**
     * Emit field directives for the program variables.
     */
    private void emitProgramVariables(Symtab symtab)
    {
        Directive directive = FIELD;
        
        code.emitLine();
        if (isProgram)
        {
            directive = FIELD_PRIVATE_STATIC;
            code.emitDirective(directive, "_sysin", "Ljava/util/Scanner;");
        }
        
        // Loop over all the program's identifiers and
        // emit a .field directive for each variable.
        for (SymtabEntry entry : symtab.getUnsorted())
        {
            Kind kind = entry.getKind();
            
            if ((kind == VARIABLE) || (kind == RECORD_FIELD))
            {
                code.emitDirective(directive, entry.getName(),
                                   code.typeDescriptor(entry));
            }
        }
    }
    
    private void emitInitializeRecord(SymtabEntry variableEntry,
                                      Typespec recordTypespec,
                                      Instruction storeInstruction,
                                      int slot)
    {
        String recordPath = recordTypespec.getRecordPath();
        
        code.emitLine();
        code.emit(NEW, recordPath);
        code.emit(DUP);
        code.emit(INVOKESPECIAL, recordPath + "/<init>()V");
        
        if (storeInstruction == null)
        {
            String variableName = variableEntry.getName();
            String ownerName = variableEntry.getSymtab().getOwner().getName();
            code.emit(PUTSTATIC, ownerName + "/" + variableName,
                                 "L" + recordPath + ";");
            code.getLocalStack().decrease(1);
            
        }
        else if (slot < 0) code.emit(storeInstruction);
        else if (slot > 3) code.emit(storeInstruction, slot);
        else
        {
            code.emit(
                Instruction.of(storeInstruction.toString() + "_" + slot));
        }
    }

    /**
     * Emit code to initialize an array for a target variable or field.
     * @param arrayEntry the target variable's or field's symbol table entry.
     * @param arrayTypespec the array type.
     */
    private int emitInitializeArray(SymtabEntry arrayEntry, 
                                    int maxDimensions)
    {
        Typespec arrayTypespec = arrayEntry.getTypespec();
        Typespec elmtTypespec  = arrayTypespec.getArrayElementType();
        int dimensionCount = 0;

        // Count the dimensions and emit a load constant of each element count.
        code.emitLine();
        Typespec typespec = arrayTypespec;
        do 
        {
            int elmtCount = typespec.getArrayElementCount();
            ++dimensionCount;
            code.emitLoadConstant(elmtCount);
            typespec = typespec.getArrayElementType();
        } while (typespec.getForm() == ARRAY);
        
        if (dimensionCount > maxDimensions) maxDimensions = dimensionCount;

        // The array element type.
        elmtTypespec = typespec.baseType();
        Form elmtForm = elmtTypespec.getForm();
        String typeName =
              elmtTypespec == Predefined.integerType ? "int"
            : elmtTypespec == Predefined.realType    ? "double"
            : elmtTypespec == Predefined.booleanType ? "boolean"
            : elmtTypespec == Predefined.charType    ? "char"
            : elmtTypespec == Predefined.stringType  ? "java/lang/String"
            : elmtForm == ENUMERATED                 ? "int"
            : elmtForm == RECORD ? elmtTypespec.getRecordPath()
            :                      null;

        // One-dimensional array.
        if (dimensionCount == 1) 
        {
            if (elmtTypespec.getForm() == RECORD)
            {
                code.emit(ANEWARRAY, typeName);
            }
            else if (elmtTypespec == Predefined.stringType) 
            {
                code.emit(ANEWARRAY, typeName);
            }
            else
            {
                code.emit(NEWARRAY, typeName);
            }
        }

        // Multidimensional array.
        else 
        {
            code.emit(MULTIANEWARRAY, 
                      code.typeDescriptor(arrayEntry.getTypespec()),
                      Integer.toString(dimensionCount));
            
            LocalStack localStack = code.getLocalStack();
            localStack.decrease(dimensionCount - 1);
        }

        // Store the allocation into the array variable.
        code.emitStoreValue(arrayEntry, arrayEntry.getTypespec());
        code.emitLine();

        // Initialize record elements.
        if (elmtTypespec.getForm() == RECORD)
        {
            emitInitializeArrayElements(arrayEntry, arrayTypespec,
                                      elmtTypespec, 
                                      1, dimensionCount, 0);
        }
        
        return maxDimensions;
    }

    /**
     * Emit code that loops over the array dimensions to initialize
     * each element.
     * @param arrayEntry the symbol table entry of the target variable.
     * @param recordTypespec the data type of the array element.
     * @param dimensionIndex the first dimension is 1, second is 2, etc.
     * @param dimensionCount the count of dimensions.
     */
    private void emitInitializeArrayElements(SymtabEntry arrayEntry, 
                                             Typespec arrayTypespec,
                                             Typespec recordTypespec, 
                                             int dimensionIndex, 
                                             int dimensionCount,
                                             int prevTempIndex)
    {
        Form form = arrayTypespec.getForm();        
        int count = arrayTypespec.getArrayElementCount();
        int tempIndex = prevTempIndex + 1;
        
        Label loopTopLabel  = new Label();
        Label loopTestLabel = new Label();

        // Initialize temporary variable to 0.
        code.emitLoadConstant(0);
        code.emitStoreLocal(Predefined.integerType, tempIndex);
        code.emit(GOTO, loopTestLabel);

        // Top of the loop: 
        // Compare the temporary variable to the element count.
        code.emitLabel(loopTopLabel);
        
        // The next array dimension.
        arrayTypespec = arrayTypespec.getArrayElementType();
        form = arrayTypespec.getForm();
        
        if (form == ARRAY)
        {
            emitInitializeArrayElements(arrayEntry, 
                                        arrayTypespec, recordTypespec,
                                        dimensionIndex + 1, dimensionCount,
                                        tempIndex);
        }
        else
        {
            // Initialize a record element.
            if (isProgram)
            {
                code.emitLoadValue(arrayEntry);
            }
            else
            {
                code.emitLoadLocal(recordTypespec, 0);
                code.emitLoadRecordField(arrayEntry);
            }
            
            if (prevTempIndex >= 1)
            {
                // Subscript
                code.emitLoadLocal(Predefined.integerType, 
                                   prevTempIndex);
                
                code.emit(AALOAD);
            }
            
            code.emitLoadLocal(Predefined.integerType, 
                               tempIndex);
            
            Instruction storeInstruction =
                  recordTypespec == Predefined.integerType ? IASTORE
                : recordTypespec == Predefined.realType    ? DASTORE
                : recordTypespec == Predefined.booleanType ? BASTORE
                : recordTypespec == Predefined.charType    ? CASTORE
                : form == ENUMERATED                       ? IASTORE
                :                                            AASTORE;
            
            emitInitializeRecord(null, recordTypespec, 
                                 storeInstruction, -1);
        }

        // Bottom of the loop: 
        // Increment and test the temporary variable 
        // and branch back to the top of the loop.
        code.emitLine();
        code.emit(IINC, tempIndex, 1);
        
        code.emitLabel(loopTestLabel);
        code.emitLoadLocal(Predefined.integerType, tempIndex);
        code.emitLoadConstant(count);
        code.emit(IF_ICMPLT, loopTopLabel);
    }
}
