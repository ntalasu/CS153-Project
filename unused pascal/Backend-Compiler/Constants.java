package backend.compiler;

import intermediate.antlr4.Pcl_C4Parser.*;

public class Constants extends Compiler
{
    /**
     * Emit code to load an integer constant.
     * @parm intCtx the IntegerConstantContext.
     */
    Object integerConstant(IntegerConstantContext ctx)
    {
        int value = (Integer) ctx.value;
        code.emitLoadConstant(value);
        
        return null;
    }
    
    /**
     * Emit code to load real constant.
     * @parm intCtx the IntegerConstantContext.
     */
    Object realConstant(RealConstantContext ctx)
    {
        double value = (Double) ctx.value;
        code.emitLoadConstant(value);
        
        return null;
    }
    
    Object booleanConstant(BooleanConstantContext ctx)
    {
        int value = (Integer) ctx.value;
        code.emitLoadConstant(value);
        
        return null;
    }
    
    Object characterConstant(CharacterConstantContext ctx)
    {
        char value = (Character) ctx.value;
        code.emitLoadConstant(String.valueOf(value));
        
        return null;
    }
    
    Object stringConstant(StringConstantContext ctx)
    {
        String value = (String) ctx.value;
        code.emitLoadConstant(value);
        
        return null;
    }
}
