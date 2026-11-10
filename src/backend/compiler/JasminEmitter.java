package backend.compiler;

import java.io.FileWriter;
import java.io.PrintWriter;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.SymtabEntry;
import intermediate.symtab.SymtabEntry.Kind;
import intermediate.type.Typespec;
import intermediate.type.Typespec.Form;

import static intermediate.symtab.SymtabEntry.Kind.*;
import static intermediate.symtab.Predefined.*;
import static intermediate.type.Typespec.Form.*;
import static backend.compiler.Instruction.*;

public class JasminEmitter
{
    private String className;
    private String objectFileName;
    private PrintWriter objectFile;

    private LocalStack localStack;
    private int count;  // instruction count
    
    /**
     * Constructor.
     * Create and open the object file.
     * @param className the name of the program.
     */
    public JasminEmitter(String className)
    {
        this.className = className;
        count = 0;
        
        // Create the Jasmin object file.
        try 
        {
            objectFileName = className + ".j";
            objectFile = new PrintWriter(new FileWriter(objectFileName));
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
    
    /**
     * Close the object file.
     */
    public void closeObjectFile()
    {
        objectFile.close();
    }
    
    /**
     * Get the name of the class.
     * @return the name.
     */
    public String getClassName() { return className; }
    
    /**
     * Get the name of the object (Jasmin) file.
     * @return the name.
     */
    public String getObjectFileName() { return objectFileName; }
    
    /**
     * Close the object file.
     */
    public void close() { objectFile.close(); }
    
    /**
     * Get the instruction count.
     * @return the count.
     */
    public int getCount() { return count; }
    
    /**
     * Set the local variables to use.
     * @param localVariables the variables to use.
     */
//    public void setLocalVariables(LocalVariables localVariables)
//    {
//        this.localVariables = localVariables;
//    }
//    
//    /**
//     * Get the local variables.
//     * @return the variables.
//     */
//    LocalVariables getLocalVariables() { return localVariables; }
    
    /**
     * Set the local stack to use.
     * @param localStack the stack to use.
     */
    public void setLocalStack(LocalStack localStack)
    {
        this.localStack = localStack;
    }
    
    /**
     * Get the local stack.
     * @return the stack.
     */
    LocalStack getLocalStack() { return localStack; }

    // =====================
    // General code emitters
    // =====================

    /**
     * Emit a blank line.
     */
    public void emitLine()
    {
        objectFile.println();
        objectFile.flush();
    }
    
    /**
     * Emit a comment.
     * @param text the comment text.
     */
    public void emitComment(String text)
    {
        objectFile.println(";");
        objectFile.println("; " + text);
        objectFile.println(";");
        objectFile.flush();
    }

    /**
     * Emit a statement comment.
     * @param ctx the StatementContext.
     */
    public void emitComment(StatementContext ctx)
    {
        String text = String.format("%03d %s", ctx.getStart().getLine(), 
                                               ctx.getText());
        
        if (text.length() <= 72) emitComment(text);
        else                     emitComment(text.substring(0, 72) + " ...");
    }

    /**
     * Emit a label.
     * @param label the label.
     */
    public void emitLabel(Label label)
    {
        objectFile.println(label + ":");
        objectFile.flush();
    }

    /**
     * Emit a label preceded by an integer value for a switch table.
     * @param label the label.
     */
    public void emitLabel(int value, Label label)
    {
        objectFile.println("\t  " + value + ": " + label);
        objectFile.flush();
    }

    /**
     * Emit a label preceded by a string value for a switch table.
     * @param label the label.
     */
    public void emitLabel(String value, Label label)
    {
        objectFile.println("\t  " + value + ": " + label);
        objectFile.flush();
    }

    /**
     * Emit a directive.
     * @param directive the directive code.
     */
    public void emitDirective(Directive directive)
    {
        objectFile.println(directive.toString());
        objectFile.flush();
        ++count;
    }

    /**
     * Emit a 1-operand directive.
     * @param directive the directive code.
     * @param operand the directive operand.
     */
    public void emitDirective(Directive directive, String operand)
    {
        objectFile.println(directive.toString() + " " + operand);
        objectFile.flush();
        ++count;
    }

    /**
     * Emit a 1-operand directive.
     * @param directive the directive code.
     * @param operand the directive operand.
     */
    public void emitDirective(Directive directive, int operand)
    {
        objectFile.println(directive.toString() + " " + operand);
        objectFile.flush();
        ++count;
    }

    /**
     * Emit a 2-operand directive.
     * @param directive the directive code.
     * @param operand the operand.
     */
    public void emitDirective(Directive directive,
                              String operand1, String operand2)
    {
        objectFile.println(directive.toString() + " " + operand1 +
                                                    " " + operand2);
        objectFile.flush();
        ++count;
    }

    /**
     * Emit a 3-operand directive.
     * @param directive the directive code.
     * @param operand the operand.
     */
    public void emitDirective(Directive directive,
                               String operand1, String operand2,
                               String operand3)
    {
        objectFile.println(directive.toString() + " " + operand1 
                                                + " " + operand2
                                                + " " + operand3);
        objectFile.flush();
        ++count;
    }

    /**
     * Emit a 0-operand instruction.
     * @param instruction the operation code.
     */
    public void emit(Instruction instruction)
    {
        objectFile.println("\t" + instruction.toString());
        objectFile.flush();
        
        localStack.increase(instruction.stackUse);
        ++count;
    }

    /**
     * Emit a 1-operand instruction.
     * @param instruction the operation code.
     * @param operand the operand text.
     */
    public void emit(Instruction instruction, String operand)
    {
        objectFile.println("\t" + instruction.toString() + "\t" + operand);
        objectFile.flush();
        
        localStack.increase(instruction.stackUse);
        ++count;
    }

    /**
     * Emit a 1-operand instruction.
     * @param instruction the operation code.
     * @param operand the operand value.
     */
    public void emit(Instruction instruction, int operand)
    {
        objectFile.println("\t" + instruction.toString() + "\t" + operand);
        objectFile.flush();
        
        localStack.increase(instruction.stackUse);
        ++count;
    }

    /**
     * Emit a 1-operand instruction.
     * @param instruction the operation code.
     * @param operand the operand value.
     */
    public void emit(Instruction instruction, double operand)
    {
        objectFile.println("\t" + instruction.toString() + "\t" + operand);
        objectFile.flush();
        
        localStack.increase(instruction.stackUse);
        ++count;
    }

    /**
     * Emit a 1-operand instruction.
     * @param instruction the operation code.
     * @param label the label operand.
     */
    public void emit(Instruction instruction, Label label)
    {
        objectFile.println("\t" + instruction.toString() + "\t" + label);
        objectFile.flush();
        
        localStack.increase(instruction.stackUse);
        ++count;
    }

    /**
     * Emit a 2-operand instruction.
     * @param instruction the operation code.
     * @param operand1 the value of the first operand.
     * @param operand2 the value of the second operand.
     */
    public void emit(Instruction instruction, int operand1, int operand2)
    {
        objectFile.println("\t" + instruction.toString() +
                           "\t" + operand1 + " " + operand2);
        objectFile.flush();
        
        localStack.increase(instruction.stackUse);
        ++count;
    }

    /**
     * Emit a 2-operand instruction.
     * @param instruction the operation code.
     * @param operand1 the text of the first operand.
     * @param operand2 the text of the second operand.
     */
    public void emit(Instruction instruction, String operand1, String operand2)
    {
        objectFile.println("\t" + instruction.toString() +
                           "\t" + operand1 + " " + operand2);
        objectFile.flush();
        
        localStack.increase(instruction.stackUse);
        ++count;
    }

    // =====
    // Loads
    // =====

    /**
     * Emit a load of an integer constant value.
     * @param value the constant value.
     */
    public void emitLoadConstant(int value)
    {
        switch (value) 
        {
            case -1: emit(ICONST_M1); break;
            case  0: emit(ICONST_0);  break;
            case  1: emit(ICONST_1);  break;
            case  2: emit(ICONST_2);  break;
            case  3: emit(ICONST_3);  break;
            case  4: emit(ICONST_4);  break;
            case  5: emit(ICONST_5);  break;

            default: 
            {
                if (   (-128 <= value) 
                    && (value <= 127))        emit(BIPUSH, value);
                else if (   (-32768 <= value) 
                         && (value <= 32767)) emit(SIPUSH, value);
                else                          emit(LDC, value);
            }
        }
    }

    /**
     * Emit a load of a real constant value.
     * @param value the constant value.
     */
    public void emitLoadConstant(double value)
    {
        if      (value == 0.0f) emit(DCONST_0);
        else if (value == 1.0f) emit(DCONST_1);
        else if (value == 2.0f) emit(DCONST_2);
        else                    emit(LDC2_W, value);
    }

    /**
     * Emit a load of a string constant value.
     * @param value the constant value.
     */
    public void emitLoadConstant(String value)
    {
        emit(LDC, "\"" + value + "\"");
    }

    /**
     * Emit code to load the value of a variable, which can be
     * a program variable, a local variable, a constant, or a VAR parameter.
     * @param variableEntry the variable's symbol table entry.
     */
    public void emitLoadValue(SymtabEntry variableEntry)
    {
        Typespec typespec = variableEntry.getTypespec().baseType();
        Kind kind = variableEntry.getKind();
        int nestingLevel = variableEntry.getSymtab().getNestingLevel();
        
        // Constant
        if (kind == CONSTANT)
        {
            Object value = variableEntry.getValue();
            
            if (typespec == integerType)
            {
                emitLoadConstant((Integer) value);
            }
            else if (typespec == realType)
            {
                emitLoadConstant((Double) value);
            }
            else if (typespec == charType)
            {
                int ch = (Character) value;
                emitLoadConstant(ch);
            }
            else  // string
            {
                emitLoadConstant((String) value);
            }
        }
        
        // Enumeration constant
        else if (kind == ENUMERATED_CONSTANT)
        {
            Object value = variableEntry.getValue();
            emitLoadConstant((Integer) value);
        }

        // Program variable.
        else if (nestingLevel == 1) 
        {
            String variableName = variableEntry.getName();
            String name = className + "/" + variableName;
            emit(GETSTATIC, name, typeDescriptor(typespec));
            
            localStack.increase(variableEntry.getTypespec() == realType
                                                             ? 2 : 1);
        }

        // Local variable or parameter.
        else 
        {
            int slot =  variableEntry.getSlotNumber();
            emitLoadLocal(typespec, slot);
        }
    }

    /**
     * Emit a load instruction for a local variable.
     * @param typespec the variable's data type.
     * @param index the variable's index into the local variables array.
     */
    public void emitLoadLocal(Typespec typespec, int index)
    {
        Form form = null;

        if (typespec != null) 
        {
            typespec = typespec.baseType();
            form = typespec.getForm();
        }

        if (   (typespec == integerType)
            || (typespec == booleanType)
            || (typespec == charType)
            || (form == ENUMERATED))
        {
            switch (index) 
            {
                case 0:  emit(ILOAD_0); break;
                case 1:  emit(ILOAD_1); break;
                case 2:  emit(ILOAD_2); break;
                case 3:  emit(ILOAD_3); break;
                default: emit(ILOAD, index);
            }
        }
        else if (typespec == realType) 
        {
            switch (index) {
                case 0:  emit(DLOAD_0); break;
                case 1:  emit(DLOAD_1); break;
                case 2:  emit(DLOAD_2); break;
                case 3:  emit(DLOAD_3); break;
                default: emit(DLOAD, index);
            }
        }
        else 
        {
            switch (index) 
            {
                case 0:  emit(ALOAD_0); break;
                case 1:  emit(ALOAD_1); break;
                case 2:  emit(ALOAD_2); break;
                case 3:  emit(ALOAD_3); break;
                default: emit(ALOAD, index);
            }
        }
    }
    
    /**
     * Emit a load from a record field.
     * @param fieldEntry the symbol table entry of the field.
     */
    void emitLoadRecordField(SymtabEntry fieldEntry)
    {
        String fieldName = fieldEntry.getName();
        Typespec fieldTypespec = fieldEntry.getTypespec();  
        Typespec recordTypespec = 
                fieldEntry.getSymtab().getOwner().getTypespec();
        
        String recordPath = recordTypespec.getRecordPath();
        String fieldPath = recordPath + "/" + fieldName;
        
        emit(GETFIELD, fieldPath, typeDescriptor(fieldTypespec));
        localStack.increase(fieldTypespec == realType ? 2 : 1);
    }

    // ======
    // Stores
    // ======
    
    /**
     * Emit a store of a value that is on top of the operand stack.
     * Store to an array element, a record field, or an ummodified variable.
     * @param targetEntry the symbol table entry of the target.
     * @param targetTypespec the target's datatype.
     */
    public void emitStoreValue(SymtabEntry targetEntry, 
                               Typespec targetTypespec)
    {
        if (targetEntry == null)
        {
            emitStoreToArrayElement(targetTypespec);
        }
        else if (targetEntry.getKind() == RECORD_FIELD) 
        {
            emitStoreToRecordField(targetEntry);
        }
        else
        {
            emitStoreToUnmodifiedVariable(targetEntry, targetTypespec);
        }
    }

    /**
     * Emit code to store a value to an ummodified target variable, 
     * which can be a program variable or a local variable.
     * @param targetEntry the symbol table entry of the variable.
     */
    private void emitStoreToUnmodifiedVariable(SymtabEntry targetEntry, 
                                               Typespec targetTypespec)
    {
        int nestingLevel = targetEntry.getSymtab().getNestingLevel();
        int slot = targetEntry.getSlotNumber();
        
        // Program variable.
        if (nestingLevel == 1) 
        {
            String targetName = targetEntry.getName();
            String name = className + "/" + targetName;
            Typespec typespec = targetTypespec.baseType();

            emitRangeCheck(targetTypespec);
            emit(PUTSTATIC, name, typeDescriptor(typespec));
            localStack.decrease(typespec == realType ? 2 : 1);
        }

        // Local variable.
        else 
        {
            emitRangeCheck(targetTypespec);
            emitStoreLocal(targetTypespec.baseType(), slot);
        }
    }

    /**
     * Emit a store instruction into a local variable.
     * @param typespec the data type of the variable.
     * @param slot the variable's slot number.
     */
    public void emitStoreLocal(Typespec typespec, int slot)
    {
        Form form = null;

        if (typespec != null) 
        {
            typespec = typespec.baseType();
            form = typespec.getForm();
        }

        if (   (typespec == integerType)
            || (typespec == booleanType)
            || (typespec == charType)
            || (form == ENUMERATED))
        {
            switch (slot) 
            {
                case 0:  emit(ISTORE_0); break;
                case 1:  emit(ISTORE_1); break;
                case 2:  emit(ISTORE_2); break;
                case 3:  emit(ISTORE_3); break;
                default: emit(ISTORE, slot);
            }
        }
        else if (typespec == realType) 
        {
            switch (slot) {
                case 0:  emit(DSTORE_0); break;
                case 1:  emit(DSTORE_1); break;
                case 2:  emit(DSTORE_2); break;
                case 3:  emit(DSTORE_3); break;
                default: emit(DSTORE, slot);
            }
        }
        else 
        {
            switch (slot) 
            {
                case 0:  emit(ASTORE_0); break;
                case 1:  emit(ASTORE_1); break;
                case 2:  emit(ASTORE_2); break;
                case 3:  emit(ASTORE_3); break;
                default: emit(ASTORE, slot);
            }
        }
    }

    /**
     * Emit a store to an array element.
     * @param elmtTypespec the element type.
     */
    private void emitStoreToArrayElement(Typespec elmtTypespec)
    {
        Form form = null;

        if (elmtTypespec != null) 
        {
            elmtTypespec = elmtTypespec.baseType();
            form = elmtTypespec.getForm();
        }

        emit(  elmtTypespec == integerType ? IASTORE
             : elmtTypespec == realType    ? DASTORE
             : elmtTypespec == booleanType ? BASTORE
             : elmtTypespec == charType    ? CASTORE
             : form == ENUMERATED                     ? IASTORE
             :                                          AASTORE);
    }
    
    /**
     * Emit a store to a record field.
     * @param fieldEntry the symbol table entry of the field.
     */
    private void emitStoreToRecordField(SymtabEntry fieldEntry)
    {
        String fieldName = fieldEntry.getName();
        Typespec fieldTypespec = fieldEntry.getTypespec();  
        Typespec recordTypespec = 
                fieldEntry.getSymtab().getOwner().getTypespec();
        
        String recordPath = recordTypespec.getRecordPath();
        String fieldPath = recordPath + "/" + fieldName;
        
        emit(PUTFIELD, fieldPath, typeDescriptor(fieldTypespec));
        localStack.decrease(fieldTypespec == realType ? 2 : 1);
    }

    // ======================
    // Miscellaneous emitters
    // ======================

    /**
     * Emit the CHECKCAST instruction for a scalar type.
     * @param typespec the data type.
     */
    public void emitCheckCast(Typespec typespec)
    {
        String descriptor = typeDescriptor(typespec);

        // Don't bracket the type with L; if it's not an array.
        if (descriptor.charAt(0) == 'L') 
        {
            descriptor = descriptor.substring(1, descriptor.length() - 1);
        }

        emit(CHECKCAST, descriptor);
    }

    /**
     * Emit the CHECKCAST instruction for a class.
     * @param typespec the data type.
     */
    public void emitCheckCastClass(Typespec typespec)
    {
        String descriptor = objectTypeName(typespec);

        // Don't bracket the type with L; if it's not an array.
        if (descriptor.charAt(0) == 'L') 
        {
            descriptor = descriptor.substring(1, descriptor.length() - 1);
        }

        emit(CHECKCAST, descriptor);
    }

    /**
     * Emit a function return of a value.
     * @param typespec the type of the return value.
     */
    public void emitReturnValue(Typespec typespec)
    {
        Form form = null;

        if (typespec != null) 
        {
            typespec = typespec.baseType();
            form = typespec.getForm();
        }

        if (   (typespec == integerType)
            || (typespec == booleanType)
            || (typespec == charType)
            || (form == ENUMERATED))         emit(IRETURN);
        else if (typespec == realType) emit(DRETURN);
        else                                  emit(ARETURN);
    }

    /**
     * Emit code to perform a runtime range check before an assignment.
     * @param targetTypespec the type of the assignment target.
     */
    public void emitRangeCheck(Typespec targetTypespec)
    {
//        if (targetTypespec.getForm() == SUBRANGE) 
//        {
//            int min = targetTypespec.getSubrangeMinValue();
//            int max = targetTypespec.getSubrangeMaxValue();
//
//            emit(DUP);
//            emitLoadConstant(min);
//            emitLoadConstant(max);
//            emit(INVOKESTATIC, "RangeChecker/check(III)V");
//
//            localStack.use(3);
//        }
    }

    // =========
    // Utilities
    // =========

    /**
     * Emit a type descriptor of an identifier's type.
     * @param entry the symbol table entry of an identifier.
     * @return the type descriptor.
     */
    public String typeDescriptor(SymtabEntry entry)
    {
        Typespec type = entry.getTypespec();
        return type != null ? typeDescriptor(type) : "V";
    }

    /**
     * Return a type descriptor for a Pascal datatype.
     * @param pascalTypespec the datatype.
     * @return the type descriptor.
     */
    public String typeDescriptor(Typespec pascalTypespec)
    {
        Form form = pascalTypespec.getForm();
        StringBuffer buffer = new StringBuffer();

        while (form == ARRAY) 
        {
            buffer.append("[");
            pascalTypespec =  pascalTypespec.getArrayElementType();
            form = pascalTypespec.getForm();
        }

        pascalTypespec = pascalTypespec.baseType();
        String str;

        if      (pascalTypespec == integerType) str = "I";
        else if (pascalTypespec == realType)    str = "D";
        else if (pascalTypespec == booleanType) str = "Z";
        else if (pascalTypespec == charType)    str = "C";
        else if (pascalTypespec == stringType)  
        {
            str = "Ljava/lang/String;";
        }
        else if (form == ENUMERATED) str = "I";
        else if (form == RECORD)   
        {
            str = "L" + pascalTypespec.getRecordPath() + ";";
        }
        else str = "*unknown*"; 

        buffer.append(str);
        return buffer.toString();
    }

    /**
     * Return the Java object name for a Pascal datatype.
     * @param pascalTypespec the datatype.
     * @return the object name.
     */
    private String objectTypeName(Typespec pascalTypespec)
    {
        Form form = pascalTypespec.getForm();
        StringBuffer buffer = new StringBuffer();
        boolean isArray = false;

        while (form == ARRAY) 
        {
            buffer.append("[");
            pascalTypespec = pascalTypespec.getArrayElementType();
            form = pascalTypespec.getForm();
            isArray = true;
        }

        if (isArray) buffer.append("L");

        pascalTypespec = pascalTypespec.baseType();
        String str;

        if (pascalTypespec == integerType)
            str = "java/lang/Integer";               
        else if (pascalTypespec == realType)    
            str = "java/lang/Double";
        else if (pascalTypespec == booleanType) 
            str = "java/lang/Boolean";
        else if (pascalTypespec == charType)    
            str = "java/lang/Character";
        else if (pascalTypespec == stringType)  
            str = "Ljava/lang/String;";
        else if (form == ENUMERATED)                       
            str = "java/lang/Integer";               
        else if (form == RECORD)                       
            str = "L" + pascalTypespec.getRecordPath() + ";";
        else                                           
            str = "*unknown*";               
        
        buffer.append(str);
        if (isArray) buffer.append(";");

        return buffer.toString();
    }

    /**
     * Return whether or not a value needs to be cloned to pass by value.
     * @param formalEntry the symbol table entry of the formal parameter.
     * @return true if needs wrapping, false if not.
     */
    public boolean needsCloning(SymtabEntry formalEntry)
    {
        Typespec type = formalEntry.getTypespec();
        Form form = type.getForm();
        Kind kind = formalEntry.getKind();

        // Arrays and records are normally passed by reference
        // and so must be cloned to be passed by value.
        return (   (kind == VALUE_PARAMETER))
                && ((form == ARRAY) || (form == RECORD));
    }

    /**
     * Return the valueOf() signature for a given scalar type.
     * @param type the scalar type.
     * @return the valueOf() signature.
     */
    public String valueOfSignature(Typespec type)
    {
        String javaType = objectTypeName(type);
        String typeCode = typeDescriptor(type);

        return String.format("%s/valueOf(%s)L%s;",
                             javaType, typeCode, javaType);
    }

    /**
     * Return the xxxValue() signature for a given scalar type.
     * @param type the scalar type.
     * @return the valueOf() signature.
     */
    public String valueSignature(Typespec type)
    {
        String javaType = objectTypeName(type);
        String typeCode = typeDescriptor(type);
        String typeName = type == integerType ? "int"
                        : type == realType    ? "double"
                        : type == booleanType ? "boolean"
                        : type == charType    ? "char"
                        :                                  "int";

        return (String.format("%s.%sValue()%s",
                              javaType, typeName, typeCode));
    }
    
    /**
     * Convert a Pascal string to a Java string.
     * @param pascalString the Pascal string.
     * @return the Java string.
     */
    public String convertString(String pascalString)
    {
        String unquoted = pascalString.substring(1, pascalString.length()-1);
        return unquoted.replace("''", "'").replace("\"", "\\\"");
    }
}
