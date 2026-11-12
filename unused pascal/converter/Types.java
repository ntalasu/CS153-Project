package backend.converter;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.Symtab;
import intermediate.symtab.SymtabEntry;
import intermediate.type.Typespec;
import intermediate.type.Typespec.Form;

import static intermediate.symtab.SymtabEntry.Kind.*;
import static intermediate.type.Typespec.Form.*;

import java.util.Hashtable;
//import java.util.Map;

public class Types extends Converter
{
    static Hashtable<String, String> typeNameTable;
    
    static
    {
        typeNameTable = new Hashtable<>();
        typeNameTable.put("integer", "int");
        typeNameTable.put("real",    "double");
        typeNameTable.put("boolean", "boolean");
        typeNameTable.put("char",    "char");
        typeNameTable.put("string",  "String");
    }

    public static String javaTypeName(Typespec pascalTypespec)
    {
        Form form = pascalTypespec.getForm();
        SymtabEntry typeEntry = pascalTypespec.getIdentifier();
        String pascalTypeName = 
            typeEntry != null ? typeEntry.getName() 
                              : null;
        String javaTypeName = null;
        
        switch (form)
        {
            case SCALAR:
                return typeNameTable.get(pascalTypeName);
                
            case ENUMERATED:
                return pascalTypeName != null ? pascalTypeName 
                                              : "int";
                
            case SUBRANGE:
                Typespec baseType = pascalTypespec.baseType();
                
                if (baseType.getIdentifier() != null)
                {
                    pascalTypeName = baseType.getIdentifier().getName();
                    javaTypeName = typeNameTable.get(pascalTypeName);
                    return javaTypeName != null ? javaTypeName 
                                        : pascalTypeName;
                }
                else return "int";
                
            case STRING:
                return "String";
                
            case ARRAY:
                Typespec elmtTypspec = pascalTypespec.getArrayBaseType();
                
                if (elmtTypspec.getIdentifier() != null)
                {
                    if (elmtTypspec.getForm() == STRING)
                    {
                        return "String";
                    }
                    
                    pascalTypeName = elmtTypspec.getIdentifier().getName();
                    javaTypeName = javaTypeName(elmtTypspec);
                    
                    return javaTypeName != null ? javaTypeName 
                                                : pascalTypeName;
                }
                else
                {
                    return "int";
                }
                
            case RECORD:
                return pascalTypespec.getRecordPath();
                
            default: return "*unknown*";
        }
    }
    
    Object typeDefinition(TypeDefinitionContext ctx)
    {
        IdentifierContext typeIdCtx = ctx.identifier();
        TypeSpecificationContext typespecCtx = ctx.typeSpecification();
        String typeName = typeIdCtx.entry.getName();
        Typespec typespec = typespecCtx.typespec;
        Form form = typespec.getForm();
        
        if (form == ENUMERATED)
        {
            if (programDeclarations) code.emitStart("private static ");
            else                  code.emitStart();

            code.emit("enum " + typeName);
            visit(typespecCtx);
        }

        return null;
    }
    
    Object enumeratedType(EnumeratedTypeContext ctx)
    {
        String separator = " {";
        
        for (IdentifierContext constIdCtx : ctx.identifier())
        {
            String name = constIdCtx.entry.getName().toUpperCase();
            code.emit(separator + name);
            separator = ", ";
        }

        code.emitEnd("};");
        return null;
    }
    
    Object recordType(RecordTypeContext ctx)
    {
        emitRecordFields(ctx.typespec);        
        return null;
    }

    /**
     * Emit a record type definition for a record.
     * @param symtab the symbol table that can contain records.
     */
    void emitRecordDefinitions(Symtab symtab)
    {
        DataInitializer data = new DataInitializer(code);

        for (SymtabEntry entry : symtab.getUnsorted())
        {
            Typespec typespec = entry.getTypespec();
            
            if (   (entry.getKind() == TYPE) 
                && (typespec.getForm() == RECORD))
            {
                if (programDeclarations) code.emitStart("private static ");
                else                  code.emitStart();
                
                code.emit("class " + typespec.getRecordPath());
                
                code.emitStart("{");
                code.indent();

                emitRecordFields(typespec);
                data.emitConstructor(entry);
                
                code.dedent();
                code.emitLine("}");
                code.emitLine();
            }
        }
    }
    
    void emitRecordFields(Typespec recordTypespec)
    {
        Symtab symtab = recordTypespec.getRecordSymtab();

        for (SymtabEntry entry : symtab.getUnsorted())
        {
            Typespec typespec = entry.getTypespec();
            String typeName = javaTypeName(typespec);
            
            code.emitStart(typeName + " ");
            code.emit(entry.getName());
            
            if (typespec.getForm() == ARRAY)
            {
                emitEmptyArrayBrackets(typespec);
            }

            code.emitEnd(";");
        }
    }
    
    /**
     * Emit a pair of empty brackets for each dimension.
     * @param type the array data type.
     */
    static void emitEmptyArrayBrackets(Typespec typespec)
    {
        String brackets = "";
        
        while (typespec.getForm() == ARRAY)
        {
            brackets += "[]";
            typespec = typespec.getArrayElementType();
        }
        
        code.emit(brackets);
    }
}
