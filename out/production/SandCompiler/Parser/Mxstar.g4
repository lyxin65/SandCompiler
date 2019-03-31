grammar Mxstar;

program: (var_def | func_def | class_def)* EOF;

var_def: ex_type ID ('=' expr)? ';' ;

func_def: result=ex_type name=ID '(' para_list? ')' block;

para_list: para (',' para)*;
class_def: CLASS name=ID '{' (func_def | construct_def | var_def)* '}';

construct_def: ID '(' para_list? ')' block;

para: ex_type ID;

basic_type: type=(BOOL | INT | STRING | VOID);

ex_type: (ID | basic_type) ('[' ']')* ;

block: '{' stmt* '}';

stmt: block                                                         # block_stmt
    | expr ';'                                                      # expr_stmt
    | var_def                                                       # var_def_stmt
    | IF '(' expr ')' stmt (ELSE stmt)?                             # if_stmt
    | FOR '(' init=expr? ';' cond=expr? ';' outit=expr? ')' stmt    # for_stmt
    | WHILE '(' expr ')' stmt                                       # while_stmt
    | RETURN expr? ';'                                              # return_stmt
    | BREAK ';'                                                     # break_stmt
    | CONTINUE ';'                                                  # continue_stmt
    | ';'                                                           # pass_stmt
    ;

expr_list: expr (',' expr)* ;

expr: '(' expr ')'                                                  # sub_expr
    | 'this'                                                        # this_expr
    | ID                                                            # var_expr
    | func_call                                                     # func_call_expr
    | literal                                                       # literal_expr
    | expr '.' (ID | func_call)                                     # member_expr
    | NEW new_object                                                # new_expr
    | expr '[' expr ']'                                             # arr_expr
    | expr op=('++' | '--')                                         # suffix_expr
    | op=('+' | '-' | '++' | '--') expr                             # prefix_expr
    | op=('~' | '!') expr                                           # prefix_expr
    | expr op=('*' | '/' | '%') expr                                # binary_expr
    | expr op=('+' | '-') expr                                      # binary_expr
    | expr op=('>>' | '<<') expr                                    # binary_expr
    | expr op=('<' | '>' | '>=' | '<=') expr                        # binary_expr
    | expr op=('==' | '!=') expr                                    # binary_expr
    | expr op='&' expr                                              # binary_expr
    | expr op='^' expr                                              # binary_expr
    | expr op='|' expr                                              # binary_expr
    | expr op='&&' expr                                             # logic_expr
    | expr op='||' expr                                             # logic_expr 
    | <assoc=right> expr '=' expr                                   # assign_expr
    ;

new_object
    : (ID | basic_type) ('[' expr ']')+ ('['']')+ ('[' expr ']')+   # error_object
    | (ID | basic_type) ('[' expr ']')+ ('['']')*                   # array_object
    | ID                                                            # single_object
    ;

func_call: ID '(' expr_list? ')' ;

literal: CINT                                                       # const_int
    | CSTRING                                                       # const_string
    | TRUE                                                          # true_v
    | FALSE                                                         # false_v
    | NULL                                                          # null_v
    ;

TRUE: 'true' ;
FALSE: 'false' ;
NULL: 'null' ;
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
