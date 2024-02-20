package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;
%%

%{
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}
	
%}

%cup
%line //brojanje linija
%column //brojanje kolona

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}


%%
//kad god naidje beli znak, preskacemo ga
" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program" { return new_symbol(sym.PROGRAM, yytext());}
"break"   	{ return new_symbol(sym.BREAK, yytext());}
"class"   	{ return new_symbol(sym.CLASS, yytext());}
"else"   	{ return new_symbol(sym.ELSE, yytext());}
"const"   	{ return new_symbol(sym.CONST, yytext());}
"if"   		{ return new_symbol(sym.IF, yytext());}
"new"   	{ return new_symbol(sym.NEW, yytext());}
"print"   	{ return new_symbol(sym.PRINT, yytext());}
"read"   	{ return new_symbol(sym.READ, yytext());}
"return"   	{ return new_symbol(sym.RETURN, yytext());}
"void"   	{ return new_symbol(sym.VOID, yytext());}
"extends"   { return new_symbol(sym.EXTENDS, yytext());}
"continue"  { return new_symbol(sym.CONTINUE, yytext());}
"max"       { return new_symbol(sym.MAX, yytext());}

//"this"   	{ return new_symbol(sym.THIS, yytext());}
"for"   	{ return new_symbol(sym.FOR, yytext());}
"foreach"   { return new_symbol(sym.FOREACH, yytext());}
"while"     { return new_symbol(sym.WHILE, yytext());}
"do"		{ return new_symbol(sym.DO, yytext());}
"static"   	{ return new_symbol(sym.STATIC, yytext());}
"namespace"   { return new_symbol(sym.NAMESPACE, yytext());}

"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIV, yytext()); }
"%" 		{ return new_symbol(sym.PERCENT, yytext()); }
"==" 		{ return new_symbol(sym.EQUALS_EQUALS, yytext()); }
"!=" 		{ return new_symbol(sym.NOT_EQUALS, yytext()); }
">" 		{ return new_symbol(sym.GT, yytext()); }
">=" 		{ return new_symbol(sym.GTE, yytext()); }
"<" 		{ return new_symbol(sym.LT, yytext()); }
"<=" 		{ return new_symbol(sym.LTE, yytext()); }
"&&" 		{ return new_symbol(sym.AND, yytext()); }
"||" 		{ return new_symbol(sym.OR, yytext()); }
"=" 		{ return new_symbol(sym.EQUALS, yytext()); }
"++" 		{ return new_symbol(sym.INCREMENT, yytext()); }
"--" 		{ return new_symbol(sym.DECREMENT, yytext()); }
";" 		{ return new_symbol(sym.SEMICOLON, yytext()); }
":" 		{ return new_symbol(sym.COLON, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"." 		{ return new_symbol(sym.DOT, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"[" 		{ return new_symbol(sym.LSQUARE, yytext()); }
"]" 		{ return new_symbol(sym.RSQUARE, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}" 		{ return new_symbol(sym.RBRACE, yytext()); }
"=>" 		{ return new_symbol(sym.ARROW, yytext()); }


"//" 				{yybegin(COMMENT);}
<COMMENT> . 		{yybegin(COMMENT);} //ako dodje bilo koji znak tokom citanja komentara, ostajemo u stanju COMMENT
<COMMENT> "\r\n" 	{ yybegin(YYINITIAL); }

//moguce da je ovde greska jer mogu da se prave omotacke klase
[0-9]+  						{ return new_symbol(sym.NUMCONST, Integer.parseInt(yytext())); }
("true"|"false") 					{return new_symbol(sym.BOOLCONST, Boolean.parseBoolean(yytext())); }
'[\x20-\x7e]' 					{return new_symbol(sym.CHARCONST, yytext().charAt(1)); } 
([a-z]|[A-Z])[a-zA-Z0-9_]* 	{return new_symbol (sym.IDENT, yytext()); }


. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1) + " na poziciji " + yycolumn); }

