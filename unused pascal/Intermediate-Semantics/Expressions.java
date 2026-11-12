package intermediate.semantics;

import java.util.ArrayList;

import intermediate.antlr4.Pcl_C4Parser.*;
import intermediate.symtab.Predefined;
import intermediate.symtab.SymtabEntry;
import intermediate.symtab.SymtabEntry.Kind;
import intermediate.symtab.SymtabEntry.StandardCode;
import intermediate.type.Typespec;

import static intermediate.symtab.SymtabEntry.Kind.*;
import static intermediate.symtab.SymtabEntry.ProcFuncCode.*;
import static intermediate.symtab.Predefined.*;
import static intermediate.type.Typespec.Form.*;
import static intermediate.type.TypeChecker.*;
import static intermediate.semantics.SemanticErrorHandler.Code.*;

public class Expressions extends Semantics
{
    Typespec expression(ExpressionContext ctx) 
    {
        // First simple expression.
        SimpleExpressionContext simpleExprCtx1 = 
                ctx.simpleExpression(0);
        Typespec simpleTypespec1 = 
                    (Typespec) visit(simpleExprCtx1);
        ctx.typespec = simpleTypespec1;
        
        RelOpContext relOpCtx = ctx.relOp();
        
        // Second simple expression?
        if (relOpCtx != null)
        {
            SimpleExpressionContext simpleExprCtx2 = 
                                ctx.simpleExpression(1);
            
            Typespec simpleTypespec2 = 
                            (Typespec) visit(simpleExprCtx2);
            if (!comparisonCompatible(simpleTypespec1, 
                                      simpleTypespec2))
            {
                error.flag(INCOMPATIBLE_COMPARISON, ctx);
            }
            
            ctx.typespec = Predefined.booleanType;
        }
        
        return ctx.typespec;
    }
    
    Typespec simpleExpression(SimpleExpressionContext ctx) 
    {
        int count = ctx.term().size();
        SignContext signCtx = ctx.sign();
        Boolean hasSign = signCtx != null;
        
        // First term.
        TermContext termCtx1 = ctx.term(0);
        Typespec termTypespec1 = (Typespec) visit(termCtx1); 
        ctx.typespec = termTypespec1;
        
        // Loop over any subsequent terms.
        for (int i = 1; i < count; i++)
        {
            String op = ctx.addOp(i-1).getText().toLowerCase();
            TermContext termCtx2 = ctx.term(i);
            Typespec termTypespec2 = (Typespec) visit(termCtx2);
            
            if (op.equals("or"))
            {
                if (!termTypespec1.isBoolean()) 
                {
                    error.flag(TYPE_MUST_BE_BOOLEAN, termCtx1);
                }
                if (!termTypespec2.isBoolean()) 
                {
                    error.flag(TYPE_MUST_BE_BOOLEAN, termCtx2);
                }
                if (hasSign)
                {
                    error.flag(INVALID_SIGN, signCtx);
                }
                
                ctx.typespec = Predefined.booleanType;
            }
            else if (areBothInteger(termTypespec1, termTypespec2)) 
            {
                ctx.typespec = Predefined.integerType;
            }
            else if (isAtLeastOneReal(termTypespec1, termTypespec2)) 
            {
                ctx.typespec = Predefined.realType;
            }
            else if (areBothString(termTypespec1, termTypespec2))
            {
                if (!op.equals("+"))
                {
                    error.flag(TYPE_MUST_BE_NUMERIC, termCtx1);
                    ctx.typespec = Predefined.integerType;
                }
                else if (hasSign) 
                {
                    error.flag(INVALID_SIGN, signCtx);                  
                }
                ctx.typespec = Predefined.stringType;
            }
            else
            {
                if (!termTypespec1.isNumeric())
                {
                    error.flag(TYPE_MUST_BE_NUMERIC, termCtx1);
                }
                if (!termTypespec2.isNumeric())
                {
                    error.flag(TYPE_MUST_BE_NUMERIC, termCtx2);
                }
                ctx.typespec = Predefined.integerType;
            }
            
            termTypespec1 = termTypespec2;
        }
        
        return ctx.typespec;
    }
    
    Typespec term(TermContext ctx) 
    {
        int count = ctx.factor().size();
        
        // First factor.
        FactorContext factorCtx1 = ctx.factor(0);
        Typespec factorTypespec1 = (Typespec) visit(factorCtx1); 
        ctx.typespec = factorTypespec1;
        
        // Loop over any subsequent factors.
        for (int i = 1; i < count; i++)
        {
            String op = ctx.mulOp(i-1).getText().toLowerCase();
            FactorContext factorCtx2 = ctx.factor(i);
            Typespec factorTypespec2 = (Typespec) visit(factorCtx2);
            
            if (op.equals("and"))
            {
                if (!factorTypespec1.isBoolean())
                {
                    error.flag(TYPE_MUST_BE_BOOLEAN, factorCtx1);
                }
                if (!factorTypespec2.isBoolean())
                {
                    error.flag(TYPE_MUST_BE_BOOLEAN, factorCtx2);
                }
                ctx.typespec = Predefined.booleanType;
            }
            else if (op.equals("*"))
            {
                if (areBothInteger(factorTypespec1, factorTypespec2)) 
                {
                    ctx.typespec = Predefined.integerType;
                }
                else if (isAtLeastOneReal(factorTypespec1, factorTypespec2)) 
                {
                    ctx.typespec = Predefined.realType;
                }
                else
                {
                    if (!factorTypespec1.isNumeric())
                    {
                        error.flag(TYPE_MUST_BE_NUMERIC, factorCtx1);
                    }
                    if (!factorTypespec2.isNumeric())
                    {
                        error.flag(TYPE_MUST_BE_NUMERIC, factorCtx2);
                    }
                    ctx.typespec = Predefined.integerType;
                }
            }
            else if (op.equals("/"))
            {
                if (   areBothInteger(factorTypespec1, factorTypespec2)
                    || isAtLeastOneReal(factorTypespec1, factorTypespec2))
                {
                    ctx.typespec = Predefined.realType;
                }
                else 
                {
                    if (!factorTypespec1.isNumeric())
                    {
                        error.flag(TYPE_MUST_BE_NUMERIC, factorCtx1);
                    }
                    if (!factorTypespec2.isNumeric())
                    {
                        error.flag(TYPE_MUST_BE_NUMERIC, factorCtx2);
                    }
                    ctx.typespec = Predefined.realType;
                }
            }
            else if (op.equals("div") || op.equals("mod"))
            {
                if (!factorTypespec1.isInteger())
                {
                    error.flag(TYPE_MUST_BE_INTEGER, factorCtx1);
                }
                if (!factorTypespec2.isInteger())
                {
                    error.flag(TYPE_MUST_BE_INTEGER, factorCtx2);
                }
                ctx.typespec = Predefined.integerType;
            }
            
            factorTypespec1 = factorTypespec2;
        }
    
        return ctx.typespec;
    }
    
    Typespec factorVariable(FactorVariableContext ctx)
    {
        VariableContext variableCtx = ctx.variable();
        ctx.typespec = variableCtx.typespec 
                     = variablesImpl.variable(variableCtx);
        
        return ctx.typespec;
    }
    
    Typespec factorIntegerConstant(FactorIntegerConstantContext ctx)
    {
        IntegerConstantContext integerConstCtx = ctx.integerConstant();
        ctx.typespec = integerConstCtx.typespec
                     = (Typespec) visit(integerConstCtx);
        
        return ctx.typespec;
    }
    
    Typespec factorRealConstant(FactorRealConstantContext ctx)
    {
        RealConstantContext realConstCtx = ctx.realConstant();
        ctx.typespec = realConstCtx.typespec
                     = (Typespec) visit(realConstCtx);
        
        return ctx.typespec;
    }
    
    Typespec factorCharacterConstant(FactorCharacterConstantContext ctx)
    {
        CharacterConstantContext characterConstCtx = ctx.characterConstant();
        ctx.typespec = characterConstCtx.typespec
                     = (Typespec) visit(characterConstCtx);
        
        return ctx.typespec;
    }
    
    Typespec factorStringConstant(FactorStringConstantContext ctx)
    {
        StringConstantContext stringConstCtx = ctx.stringConstant();
        ctx.typespec = stringConstCtx.typespec
                     = (Typespec) visit(stringConstCtx);
        
        return ctx.typespec;
    }
    
    Typespec factorBooleanConstant(FactorBooleanConstantContext ctx)
    {
        BooleanConstantContext booleanConstCtx = ctx.booleanConstant();
        ctx.typespec = booleanConstCtx.typespec
                     = (Typespec) visit(booleanConstCtx);
        
        return ctx.typespec;
    }
    
    Typespec factorFunctionCall(FactorFunctionCallContext ctx) 
    {
        FunctionCallContext callCtx = ctx.functionCall();
        FunctionNameContext functionNameCtx = callCtx.functionName();
        IdentifierContext functionIdCtx = functionNameCtx.identifier();
        ArgumentListContext argListCtx = callCtx.argumentList();
        String name = callCtx.functionName().getText().toLowerCase();
        SymtabEntry functionEntry = symtabStack.lookup(name);
        boolean badName = false;
        
        ctx.typespec = Predefined.integerType;

        if (functionEntry == null)
        {
            error.flag(UNDECLARED_IDENTIFIER, functionNameCtx);
            badName = true;
        }
        else
        {
            Kind kind = functionEntry.getKind();
            
            if ((kind != FUNCTION) && (kind != STANDARD))
            {
                error.flag(NAME_MUST_BE_FUNCTION, functionNameCtx);
                badName = true;
            }
        }
        
        // Bad function name. Do a simple arguments check and then leave.
        if (badName)
        {
            for (ArgumentContext exprCtx : argListCtx.argument())
            {
                visit(exprCtx);
            }
        }
        
        // Good function name.
        else
        {
            if (functionEntry.getProcFuncCode() == LIBRARY)
            {
                ctx.typespec = standardFunction(functionEntry, argListCtx);
            }
            else
            {
                ArrayList<SymtabEntry> parameters = 
                                    functionEntry.getProcFuncParameters();
                statementsImpl.checkCallArguments(argListCtx, parameters);
                ctx.typespec = functionEntry.getTypespec();
            }
        }
        
        functionNameCtx.entry = functionIdCtx.entry = functionEntry;
        functionNameCtx.typespec = functionIdCtx.typespec = ctx.typespec;

        return ctx.typespec;
    }    
    
    private Typespec standardFunction(SymtabEntry functionEntry,
                                      ArgumentListContext argListCtx)
    {
        int argCount = argListCtx.argument().size();
        ExpressionContext exprCtx = argListCtx.argument(0).expression();
        Typespec argTypespec = ((Typespec) visit(exprCtx)).baseType();
        StandardCode standardCode = functionEntry.getStandardCode();
        
        switch (standardCode)
        {
            case EOF:
            case EOLN:
                if (argCount != 0) badArgumentList(argListCtx);
                break;
                
            default:
                if (argCount != 1) badArgumentList(argListCtx);
                break;
        }
                
        switch (standardCode)
        {
            case ABS:
            case SQR:
                if (   (argTypespec == integerType)
                    || (argTypespec == realType)  )
                {
                    return argTypespec;
                }
                                
            case ARCTAN:
            case COS:
            case EXP:
            case LN:
            case SIN:
            case SQRT:
                if (   (argTypespec == integerType)
                    || (argTypespec == realType)  )
                {
                    return realType;
                }
                
            case PRED:
            case SUCC:
                if (   (argTypespec == integerType)
                    || (argTypespec.getForm() == ENUMERATED))
                {
                    return argTypespec;
                }
                
            case CHR:
                if (argTypespec == integerType)
                {
                    return charType;
                }
                
            case ORD:
                if (   (argTypespec == charType)
                    || (argTypespec.getForm() == ENUMERATED))
                {
                    return integerType;
                }
                
            case ODD:
                if (argTypespec == integerType)
                {
                    return integerType;
                }
                
            case EOF:
            case EOLN:
                return booleanType;
                
            case ROUND:
            case TRUNC:
                if (argTypespec == realType)
                {
                    return integerType;
                }
        }
        
        error.flag(TYPE_MISMATCH, exprCtx);
        return integerType;
    }
    
    private void badArgumentList(ArgumentListContext argListCtx)
    {
        error.flag(ARGUMENT_COUNT_MISMATCH, argListCtx);
        
        for (ArgumentContext exprCtx : argListCtx.argument())
        {
            visit(exprCtx);
        }
    }
    
    Typespec factorNot(FactorNotContext ctx)
    {
        FactorContext factorCtx = ctx.factor();
        ctx.typespec = factorCtx.typespec 
                     = (Typespec) visit(factorCtx);
        
        if (factorCtx.typespec != Predefined.booleanType)
        {
            error.flag(TYPE_MUST_BE_BOOLEAN, factorCtx);
        }

        return ctx.typespec;
    }
    
    Typespec factorParens(FactorParensContext ctx)
    {
        ExpressionContext exprCtx = ctx.expression();
        ctx.typespec = (Typespec) visit(exprCtx);
        
        return ctx.typespec;
    }
    
    /**
     * Determine whether or not an expression is a variable only.
     * @param exprCtx the ExpressionContext.
     * @return true if it's an expression only, else false.
     */
    boolean expressionIsVariable(ExpressionContext exprCtx)
    {
        // Only a single simple expression?
        if (exprCtx.simpleExpression().size() == 1)
        {
            SimpleExpressionContext simpleCtx = 
                                              exprCtx.simpleExpression().get(0);
            // Only a single term?
            if (simpleCtx.term().size() == 1)
            {
                TermContext termCtx = simpleCtx.term().get(0);
                
                // Only a single factor?
                if (termCtx.factor().size() == 1)
                {
                    return termCtx.factor().get(0) instanceof 
                                            FactorVariableContext;
                }
            }
        }
        
        return false;
    }
}
