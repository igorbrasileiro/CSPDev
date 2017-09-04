grammar Csp;

spec
	: definition*
	;

definition
	: channelDecl
	| simpleDefinition
	| comment
	;
	
channelDecl
	: CHANNEL channelNames (channelColonType)? 
	;
	
channelNames											//it should return a  list (check this in the antlr book)
	: ID(COMMA ID)* 									//stores the IDs in a list (field)
	;
	
channelColonType										//it should return the type
	: COLLON  type	
	;
	
simpleDefinition
	: definitionLeft EQUAL any 	
	;

definitionLeft
	: defnCallLeft
	| defnCallLeft LPAREN any* RPAREN	
	;
	
defnCallLeft
	: ID
 	;

any 
	: proc
	| boolExp
	| expr
	;
	
type
	: simple
	| type DOT type
	| set
	;

simple		//TODO: expand this rule
	: ID
	;
	
set										//TODO: expand this rule
	: LBRACE any* RBRACE
	;
	
proc									//TODO: came from rule _proc (try to make union of rules _proc and amb)
	: Skip								//
	| Stop
	| ID ARROW proc	//TODO: replace with event
	| proc ECHOICE proc
	| proc ICHOICE proc
	| proc INTL proc
	| IF boolExp THEN proc ELSE proc	//TODO: revise
	| boolExp GUARD proc
	| proc BACKSLASH set 
	| proc LSYNC set RSYNC proc
	| proc TIMEOUT proc						//timeout operator
	| proc INTR proc						//interrupt operator
	| proc SEMICOL proc						//sequential composition
	| ID				//TODO: revise
	;
		

boolExp										//boolean expressions
	: NOT boolOrAmb
	| expr (LT | GT | LTEQ | GTEQ) expr
	| expr (EQ | NEQ) expr 
	| boolExp  (AND | OR) boolExp
	| TRUE
	| FALSE
	| ID
	| number
	;

boolOrAmb
	: boolExp
	| simple
	;
	
expr										//arith expressions
	: MINUS expr
	| expr MOD expr
	| expr (TIMES | DIV) expr
	| expr (PLUS | MINUS) expr
	| LPAREN expr RPAREN
	| number
	| ID	
	;
	
number
   : DIGIT+
   ;

comment	
	: LINECOMMENT	//TODO: include multiline comment and return the text of comment
	;
	
AND : 'and';
OR  : 'or' ;
EQ	: '==';
NEQ : '!=';
LT	: '<';
GT	: '>';
LTEQ	: '<=';
GTEQ	: '>=';
NOT	: 'not';
PLUS	: '+';
TIMES	: '*';	
MINUS	: '-';
DIV		: '/';
MOD	: '%';
IF : 'if' ;
THEN : 'then' ;
ELSE : 'else' ;
Skip : 'SKIP' ;
Stop : 'STOP' ;
DIVERGE : 'div' ;
TRUE : 'true' ;
FALSE : 'false' ;
GUARD : '&' ;
LPAREN : '(' ;
RPAREN : ')' ;
LSYNC : '[|' ;
RSYNC : '|]' ;
INTL :	'|||';
ECHOICE :	'[]';
ICHOICE :	'|~|';
COMMA	:	',';
COLLON	:	':';
SEMICOL	:	';';
ARROW	:	'->';
QUERY	:	'?';
PLING	:	'!';
CHANNEL	:	'channel';
DOT	:	'.';
LBRACE	: '{';
RBRACE	: '}';
EQUAL	: '=';
BACKSLASH : '\\';
TIMEOUT	: '[>';
INTR	: '/\\';


DIGIT
   : ('0' .. '9')
   ;
ID : [a-zA-Z]+					//TODO: revise - ids do not allow numbers or special chars
	;

LINECOMMENT
    : ('--') ~('\r'|'\n')* -> channel (HIDDEN)
    ;
	
WS
   : [ \r\n\t] + -> channel (HIDDEN)
   ;