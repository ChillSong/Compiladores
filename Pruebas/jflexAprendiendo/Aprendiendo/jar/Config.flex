package fes.aragon.codigo;
%%
%public
%class Lexico
%line
%char
%column
%full
%type Tokens
%{
private boolean hayToken = false;

public boolean getHayToken() {
    return this.hayToken;
}
%}
%type Tokens
%init{
%init}

%eof{
    this.hayToken=false;
%eof}




/* Expresiones regulares */
PUNTOCOMA    = ";"
DIGITO        = [0-9]+
LETRA         = [A-Za-z]*
ESPACIO       = [ \t\r]+
SALTO_LINEA   = \n|\r

PARENTESIS_A  = \(
PARENTESIS_C  = \)
MAS           = \+
MENOS         = -
POR           = \*
IGUAL         = =
POTENCIA      = \^
PORCENTAJE    = %

PUNTO         = \.
EXPONENTE     = [eE][+-]?{DIGITO}+
FLOTANTE      = ({DIGITO}+{PUNTO}{DIGITO}+({EXPONENTE})?)|({DIGITO}+{EXPONENTE})
ID            = {LETRA}({LETRA}|{DIGITO})*

RESERVADAS    = \b(abstract|boolean|break|byte|case|catch|char|class|continue|default|do|double|else|enum|extends|final|finally|float|for|if|implements|import|instanceof|int|interface|long|native|new|null|package|private|protected|public|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while)\b
FUNCIONES     = \b(log|sqr|max|min|PI)\b

INSTRUCCION   = {RESERVADAS}|{ID}|{FLOTANTE}|{MAS}|{MENOS}|{POR}|{POTENCIA}|{IGUAL}|{PARENTESIS_A}|{PARENTESIS_C}|{PUNTO}|{PORCENTAJE}|{EXPONENTE}

/* Reconocimiento de instrucciones completas hasta ';' */
INSTRUCCION_COMPLETA = {INSTRUCCION} + {PUNTOCOMA}

/* Reglas */
%%

{ESPACIO} {/* Ignorar espacios */}
{SALTO_LINEA} {/* Ignorar saltos de línea */}

{PARENTESIS_A} {
    Tokens tok = new Tokens(yytext(), Sym.PAREN_A, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}
{PARENTESIS_C} {
    Tokens tok = new Tokens(yytext(), Sym.PAREN_C, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}
{MAS} {
    Tokens tok = new Tokens(yytext(), Sym.MAS, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}
{MENOS} {
    Tokens tok = new Tokens(yytext(), Sym.MENOS, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}
{POR} {
    Tokens tok = new Tokens(yytext(), Sym.POR, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}
{POTENCIA} {
    Tokens tok = new Tokens(yytext(), Sym.POTENCIA, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}
{PORCENTAJE} {
    Tokens tok = new Tokens(yytext(), Sym.PORCENTAJE, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}
{IGUAL} {
    Tokens tok = new Tokens(yytext(), Sym.IGUAL, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}
{FLOTANTE} {
    Tokens tok = new Tokens(yytext(), Sym.FLOTANTE, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}
{FUNCIONES} {
    Tokens tok = new Tokens(yytext(), Sym.FUNCION, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}
{RESERVADAS} {
    Tokens tok = new Tokens(yytext(), Sym.RESERVADA, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}
{ID} {
    Tokens tok = new Tokens(yytext(), Sym.ID, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}

/* Para reconocer instrucciones completas hasta el punto y coma */
{INSTRUCCION_COMPLETA} {
    Tokens tok = new Tokens(yytext(), Sym.INSTRUCCION_COMPLETA, yyline, yycolumn);
    this.hayToken = true;
    return tok;
}

/* Error léxico */
. {
    System.out.println("Error léxico: '" + yytext() + "' en línea " + (yyline + 1) + ", columna " + (yycolumn + 1));
    this.hayToken = false;
}
