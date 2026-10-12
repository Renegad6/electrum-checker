grammar OclInvariant;

document
    : CONTEXT ID invariantDeclaration+ EOF
    ;

invariantDeclaration
    : INV ID? COLON expression SEMI?
    ;

expression
    : implicationExpression
    ;

implicationExpression
    : orExpression (IMPLIES implicationExpression)?
    ;

orExpression
    : xorExpression (OR xorExpression)*
    ;

xorExpression
    : andExpression (XOR andExpression)*
    ;

andExpression
    : equalityExpression (AND equalityExpression)*
    ;

equalityExpression
    : relationalExpression ((EQUAL | NOT_EQUAL) relationalExpression)*
    ;

relationalExpression
    : additiveExpression ((LESS | LESS_EQUAL | GREATER | GREATER_EQUAL) additiveExpression)*
    ;

additiveExpression
    : multiplicativeExpression ((PLUS | MINUS) multiplicativeExpression)*
    ;

multiplicativeExpression
    : unaryExpression ((STAR | SLASH | PERCENT | DIV | MOD) unaryExpression)*
    ;

unaryExpression
    : (NOT | MINUS) unaryExpression                         # unaryOperation
    | primaryExpression                                     # primaryOperation
    ;

primaryExpression
    : SELF (DOT ID)*                                        # selfNavigation
    | ID (DOT ID)*                                          # nameOrNavigation
    | INTEGER                                                # integerLiteral
    | TRUE                                                   # trueLiteral
    | FALSE                                                  # falseLiteral
    | LPAREN expression RPAREN                              # parenthesizedExpression
    ;

CONTEXT: 'context';
INV: 'inv';
IMPLIES: 'implies';
OR: 'or';
XOR: 'xor';
AND: 'and';
NOT: 'not';
DIV: 'div';
MOD: 'mod';
TRUE: 'true';
FALSE: 'false';
SELF: 'self';
EQUAL: '=';
NOT_EQUAL: '<>' | '!=';
LESS_EQUAL: '<=';
GREATER_EQUAL: '>=';
LESS: '<';
GREATER: '>';
PLUS: '+';
MINUS: '-';
STAR: '*';
SLASH: '/';
PERCENT: '%';
DOT: '.';
COLON: ':';
SEMI: ';';
LPAREN: '(';
RPAREN: ')';
INTEGER: [0-9]+;
ID: [A-Za-z_] [A-Za-z_0-9]*;
LINE_COMMENT: '--' ~[\r\n]* -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;
WS: [ \t\r\n]+ -> skip;