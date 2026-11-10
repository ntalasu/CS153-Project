package intermediate.symtab;

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

import intermediate.symtab.SymtabEntry.Kind;
import intermediate.type.Typespec;

import static intermediate.symtab.SymtabEntry.Kind.*;

public class Symtab extends TreeMap<String, SymtabEntry>
{
    private static final long serialVersionUID = 0L;
    
    private int nestingLevel;
    private int nextSlotNumber;
    private SymtabEntry ownerEntry; // symbol table entry of this symtab's owner
    private ArrayList<SymtabEntry> unsorted;

    public static final String UNNAMED_PREFIX = "_record_";
    private static int unnamedIndex = 0;

    /**
     * Generate a name for an unnamed type.
     * @return the name;
     */
    public static String generateUnnamedName()
    {
        unnamedIndex++;
        return UNNAMED_PREFIX + unnamedIndex;
    }
    
    public Symtab(int nestingLevel)
    {
        this.nestingLevel = nestingLevel;
        this.nextSlotNumber = -1;
        this.unsorted = new ArrayList<>();
    }

    public int getNestingLevel()   { return nestingLevel; }
    public SymtabEntry getOwner()  { return ownerEntry; }
    public int getNextSlotNumber() { return ++nextSlotNumber; }
    
    public ArrayList<SymtabEntry> getUnsorted() { return unsorted; }
    
    public void setOwner(SymtabEntry ownerEntry) 
    { 
        this.ownerEntry = ownerEntry;
    }

    public SymtabEntry enter(String name, Kind kind) 
    { 
        SymtabEntry entry = new SymtabEntry(name, kind, this);
        put(name.toLowerCase(), entry);
        unsorted.add(entry);
        
        return entry;
    }
    
    public SymtabEntry lookup(String name) 
    { 
        return get(name.toLowerCase()); 
    }
    
    /**
     * Reset all the variables to a given kind.
     * @param kind the kind to reset.
     */
    public void resetVariables(Kind kind)
    {
        for (Map.Entry<String, SymtabEntry> mapEntry : entrySet())
        {            
            SymtabEntry entry = (SymtabEntry) mapEntry.getValue();
            if (entry.getKind() == VARIABLE) entry.setKind(kind);
        }
    }
    
    /**
     * Calculate the total size of all the variables and parameters.
     * @return the size.
     */
    public int localsSize()
    {
        int size = 0;
        
        for (Map.Entry<String, SymtabEntry> mapEntry : entrySet())
        {            
            SymtabEntry entry = (SymtabEntry) mapEntry.getValue();
            Kind kind = entry.getKind();
            
            if (   (kind == VARIABLE) 
                || (kind == VALUE_PARAMETER)
                || (kind == REFERENCE_PARAMETER))
            {
                ++size;
                
                Typespec typespec = entry.getTypespec();
                if (typespec == Predefined.realType) ++size;
            }            
        }
        
        return size;
    }
}
