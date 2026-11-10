package backend.converter;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.SymtabEntry;
import intermediate.symtab.Predefined;
import intermediate.type.Typespec;

import static intermediate.symtab.SymtabEntry.ProcFuncCode.*;

public class Expressions extends Converter
{
    Object expression(ExpressionContext ctx) 
    {
        SimpleExpressionContext simpleCtx1 = 
                            ctx.simpleExpression(0);
        RelOpContext relOpCtx = ctx.relOp();
        
        // Only one simple expression?
        if (relOpCtx == null) visit(simpleCtx1);
        
        // Two simple expressions.
        else
        {
            String op = relOpCtx.getText();
            
            if      (op.equals("="))  op = "==";
            else if (op.equals("<>")) op = "!=";
            
            SimpleExpressionContext simpleCtx2 =
                            ctx.simpleExpression(1);
            
            // Java uses the compareTo method for strings.
            if (simpleCtx1.typespec == 
                                Predefined.stringType)
            {
                code.emit("(");
                visit(simpleCtx1);
                code.emit(")." + "compareTo(");
                visit(simpleCtx2);
                code.emit(") " + op + " 0");
            }
            else
            {
                visit(simpleCtx1);
                code.emit(" " + op + " ");
                visit(simpleCtx2);
            }
        }
        
        return null;
    }

    Object simpleExpression(SimpleExpressionContext ctx) 
    {
        int count = ctx.term().size();
        
        if ((ctx.sign() != null) && (ctx.sign().getText().equals("-")))
        {
            code.emit("-");
        }
        
        // Loop over the terms.
        for (int i = 0; i < count; i++)
        {
            TermContext termCtx = ctx.term(i);
            visit(termCtx);
            
            if (i < count-1)
            {
                String addOp = ctx.addOp(i).getText().toLowerCase();
                if (addOp.equals("or")) addOp = "||";
                
                code.emit(" " + addOp + " ");
            }
        }
        
        return null;
    }

    Object term(TermContext ctx) 
    {
        int count = ctx.factor().size();
        String mulOp = null;
        
        // Loop over the factors.
        for (int i = 0; i < count; i++)
        {
            FactorContext factorCtx = ctx.factor(i);
            Typespec typespec1 = factorCtx.typespec.baseType();
            Typespec typespec2 = null;
            boolean realDivision = false;
            
            if (i < count-1)
            {
                mulOp = ctx.mulOp(i).getText().toLowerCase();
                if      (mulOp.equals("and")) mulOp = " && ";
                else if (mulOp.equals("div")) mulOp = "/";
                else if (mulOp.equals("mod")) mulOp = "%";
                
                else if (mulOp.equals("/"))
                {
                    realDivision = true;
                    typespec2 = ctx.factor(i+1).typespec;
                }
            }
            
            if (realDivision
                && (typespec1 == Predefined.integerType)
                && (typespec2 != Predefined.realType))
            {
                code.emit("((double) ");
                visit(factorCtx);
                code.emit(")");
            }
            else
            {
                visit(factorCtx);
            }
            
            if (i < count-1) code.emit(mulOp);
        }
        
        return null;
    }

    Object functionCall(FunctionCallContext ctx) 
    {
        FunctionNameContext funcNameCtx = ctx.functionName();
        SymtabEntry functionEntry = funcNameCtx.entry;
        String functionName = funcNameCtx.getText().toLowerCase();
        
        if (functionEntry.getProcFuncCode() == LIBRARY)
        {
            code.emit("Stdlib.");
        }
        
        code.emit(functionName + "(");
        if (ctx.argumentList() != null) visit(ctx.argumentList());
        code.emit(")");

        return null;
    }
    
    Object argumentList(ArgumentListContext ctx)
    {
        String separator = "";
        
        for (ArgumentContext argCtx : ctx.argument())
        {
            if (separator.length() > 0) code.emit(separator);
            visit(argCtx.expression());
            separator = ", ";
        }
        
        return null;
    }
    
    Object factorNot(FactorNotContext ctx)
    {
        code.emit("!(");
        visit(ctx.factor());
        code.emit(")");
        
        return null;
    }
    
    Object factorParens(FactorParensContext ctx)
    {
        code.emit("(");
        visit(ctx.expression());
        code.emit(")");
        
        return null;
    }
}
