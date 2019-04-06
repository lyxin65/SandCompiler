grammar Mxstar;

program: (varDef | funcDef | classDef)* EOF;

varDef: exType ID ('=' expr)? ';' ;

funcDef: result=exType name=ID '(' paraList? ')' block;

paraList: para (',' para)*;
classDef: CLASS name=ID '{' (funcDef | constructDef | varDef)* '}';
lyxin65
constructDef: ID '(' paraList? ')' block;

para: exType ID;

baseType: type=(BOOL | INT | VOID);

atomType: baseType | type=ID | type=STRING;

exType: atomType ('[' empty ']')* ;

block: '{' stmt* '}';

stmt: block                                                         # blockStmt
    | expr ';'                                                      # exprStmt
    | varDef                                                        # varDefStmt
    | IF '(' expr ')' stmt (ELSE stmt)?                             # ifStmt
    | FOR '(' init=expr? ';' cond=expr? ';' outit=expr? ')' stmt    # forStmt
    | WHILE '(' expr ')' stmt                                       # whileStmt
    | RETURN expr? ';'                                              # returnStmt
    | BREAK ';'                                                     # breakStmt
    | CONTINUE ';'                                                  # continueStmt
    | ';'                                                           # passStmt
    ;

empty: ;

exprList: expr (',' expr)* ;

expr: '(' expr ')'                                                  # subExpr
    | token='this'                                                  # thisExpr
    | token=ID                                                      # varExpr
    | funcCall                                                      # funcCallExpr
    | literal                                                       # literalExpr
    | expr '.' (ID | funcCall)                                      # memberExpr
    | NEW newObject                                                 # newExpr
    | expr '[' expr ']'                                             # arrExpr
    | expr op=('++' | '--')                                         # suffixExpr
    | op=('+' | '-' | '++' | '--') expr                             # prefixExpr
    | op=('~' | '!') expr                                           # prefixExpr
    | expr op=('*' | '/' | '%') expr                                # binaryExpr
    | expr op=('+' | '-') expr                                      # binaryExpr
    | expr op=('>>' | '<<') expr                                    # binaryExpr
    | expr op=('<' | '>' | '>=' | '<=') expr                        # binaryExpr
    | expr op=('==' | '!=') expr                                    # binaryExpr
    | expr op='&' expr                                              # binaryExpr
    | expr op='^' expr                                              # binaryExpr
    | expr op='|' expr                                              # binaryExpr
    | expr op='&&' expr                                             # logicExpr
    | expr op='||' expr                                             # logicExpr 
    | <assoc=right> expr '=' expr                                   # assignExpr
    ;

newObject
    : atomType ('(' ')' | ('[' expr ']')* ('[' empty ']')*);

funcCall: ID '(' exprList? ')' ;

literal: token=CINT
    | token=CSTRING
    | token=BOOL_LITERAL
    | token=NULL_LITERAL
    ;

BOOL_LITERAL: 'true' | 'false' ;
NULL_LITERAL: 'null' ;
INT: 'int' ;
STRING: 'string' ;
VOID: 'void' ;
BOOL: 'bool' ;
IF: 'if' ;
ELSE: 'else';
FOR: 'for' ;
WHILE: 'while' ;
RETURN: 'return' ;
BREAK: 'break' ;
CONTINUE: 'continue' ;
NEW: 'new' ;
CLASS: 'class';


CSTRING: '"' CHARACTER* '"' ;
fragment CHARACTER: ~["\\\n\r] | '\\' ["n\\] ;
ID: [a-zA-Z_][a-zA-Z_0-9]* ;
CINT: [0-9][0-9]* ;
WS: [ \t\n\r]+ -> skip ;
BLOCK_COMMENT: '/*' .*? '*/' -> skip ;
LINE_COMMENT: '//' ~[\r\n]* -> skip ;
