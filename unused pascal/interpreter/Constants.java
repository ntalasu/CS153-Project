package backend.interpreter;

import intermediate.antlr4.Pcl_C4Parser.*;

public class Constants extends Executor
{
    Object integerConstant(IntegerConstantContext ctx)
    {
        return Integer.parseInt(ctx.getText());
    }
    
    Object realConstant(RealConstantContext ctx)
    {
        return Double.parseDouble(ctx.getText());
    }

    Object characterConstant(CharacterConstantContext ctx) 
    {
        return ctx.getText().charAt(1);
    }

    Object stringConstant(StringConstantContext ctx) 
    {
        String pascalString = ctx.getText();        
        return convertString(pascalString);
    }

    Object booleanConstant(BooleanConstantContext ctx) 
    {
        return ctx.getText().equalsIgnoreCase("true");
    }
}
