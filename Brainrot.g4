grammar Brainrot;

@header {
    package intermediate.antlr4;
}

program
    : CLASS identifier COLON separator*
      (importDeclaration separator*)*
      (functionDeclaration separator*)*
      mainMethod (separator* functionDeclaration)*
      separator* PROGRAM_END separator* EOF
    ;

importDeclaration
    : TIM_CHEESE qualifiedName (DOT STAR)? SEMI?
    ;

qualifiedName
    : identifier (DOT identifier)*
    ;

mainMethod
    : MAIN COLON block
    ;

functionDeclaration
    : RIZZ returnType identifier LPAREN parameterList? RPAREN COLON block
    ;

returnType
    : valueType
    | SIGMA
    ;

valueType
    : DABLOONS
    | CHAT
    ;

parameterList
    : parameter (COMMA parameter)*
    ;

parameter
    : valueType identifier
    ;

block
    : separator* (statement (separator+ statement)*)? separator* FADE
    ;

separator
    : NEWLINE
    | SEMI
    ;

statement
    : variableDeclaration
    | constantDeclaration
    | assignmentStatement
    | ifStatement
    | whileStatement
    | forStatement
    | breakStatement
    | continueStatement
    | exitStatement
    | returnStatement
    | printStatement
    | callStatement
    ;

variableDeclaration
    : BET valueType identifier (assignmentOperator expression)?
    ;

constantDeclaration
    : CONST_DECL valueType identifier assignmentOperator expression
    ;

assignmentStatement
    : identifier assignmentOperator expression
    ;

assignmentOperator
    : IS_GIVING
    | ASSIGN
    ;

ifStatement
    : SIX LPAREN expression RPAREN COLON block
      (separator* SEVEN SIX LPAREN expression RPAREN COLON block)*
      (separator* SEVEN COLON block)?
    ;

whileStatement
    : WHILE LPAREN expression RPAREN COLON block
    ;

forStatement
    : FOR LPAREN forInitializer? SEMI expression? SEMI assignmentStatement? RPAREN COLON block
    ;

forInitializer
    : variableDeclaration
    | assignmentStatement
    ;

breakStatement
    : OHIO
    ;

continueStatement
    : TONKA_JAHARI
    ;

exitStatement
    : SKIBIDI
    ;

returnStatement
    : YEET expression?
    ;

printStatement
    : YO_GURT LPAREN argumentList? RPAREN
    ;

callStatement
    : functionCall
    ;

argumentList
    : expression (COMMA expression)*
    ;

functionCall
    : JOHN_PORK identifier LPAREN argumentList? RPAREN
    ;

expression
    : additiveExpression (relationalOperator additiveExpression)*
    ;

relationalOperator
    : MOGGING
    | MOGGED_BY
    | NO_CAP
    | LOWK_MOGGED_BY
    | LOWK_MOGGING
    | CAP
    | GT
    | LT
    | EQ
    | LE
    | GE
    | NE
    ;

additiveExpression
    : multiplicativeExpression ((AURA_GAIN | AURA_LOSS | PLUS | MINUS) multiplicativeExpression)*
    ;

multiplicativeExpression
    : unaryExpression ((AURA_FARM | CHOPPED_CHEESE | STAR | SLASH) unaryExpression)*
    ;

unaryExpression
    : (PLUS | MINUS) unaryExpression
    | primaryExpression
    ;

primaryExpression
    : INTEGER
    | STRING
    | functionCall
    | identifier
    | LPAREN expression RPAREN
    ;

identifier
    : IDENTIFIER
    ;

// Multiword keywords are kept as single lexer tokens; spaces or tabs may separate their words.
CLASS       : 'quandale' [ \t]+ 'dingle';
PROGRAM_END : 'thank' [ \t]+ 'you' [ \t]+ 'thank' [ \t]+ 'you' [ \t]+ 'thank' [ \t]+ 'you';
MAIN        : 'tung' [ \t]+ 'tung' [ \t]+ 'sahur';
IS_GIVING   : 'is' [ \t]+ 'giving';
CONST_DECL  : '24k' [ \t]+ 'gold' [ \t]+ 'labubu';
AURA_FARM   : 'aura' [ \t]+ 'farm';
CHOPPED_CHEESE : 'chopped' [ \t]+ 'cheese';
AURA_GAIN   : 'aura' [ \t]+ 'gain';
AURA_LOSS   : 'aura' [ \t]+ 'loss';
MOGGED_BY   : 'mogged' [ \t]+ 'by';
LOWK_MOGGED_BY : 'lowk' [ \t]+ 'mogged' [ \t]+ 'by';
LOWK_MOGGING : 'lowk' [ \t]+ 'mogging';
JOHN_PORK   : 'john' [ \t]+ 'pork';
YO_GURT     : 'yo' [ \t]+ 'gurt';
TIM_CHEESE  : 'tim' [ \t]+ 'cheese';
TONKA_JAHARI : 'tonka' [ \t]+ 'jahari';
FADE        : 'fade';

RIZZ        : 'rizz';
SIGMA       : 'sigma';
DABLOONS    : 'dabloons';
CHAT        : 'chat';
BET         : 'bet';
SIX         : 'six';
SEVEN       : 'seven';
WHILE       : 'tralalero' [ \t]+ 'tralala';
FOR         : 'ballerina' [ \t]+ 'cappuccina';
OHIO        : 'ohio';
SKIBIDI     : 'skibidi';
YEET        : 'yeet';
MOGGING     : 'mogging';
NO_CAP      : 'no' [ \t]+ 'cap';
CAP         : 'cap';

GT          : '>';
LT          : '<';
EQ          : '==';
LE          : '<=';
GE          : '>=';
NE          : '!=';
PLUS        : '+';
MINUS       : '-';
STAR        : '*';
SLASH       : '/';
ASSIGN      : '=';
LPAREN      : '(';
RPAREN      : ')';
COLON       : ':';
COMMA       : ',';
SEMI        : ';';
DOT         : '.';

LINE_COMMENT
    : 'mewing' ([ \t] ~[\r\n]*)? -> skip
    ;
NEWLINE     : '\r'? '\n';
WHITESPACE  : [ \t\f]+ -> skip;
INTEGER     : [0-9]+;
STRING      : '"' ( '\\' . | ~["\\\r\n] )* '"';
IDENTIFIER  : [a-zA-Z_] [a-zA-Z_0-9]*;
