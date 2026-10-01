/* Sección 1: Imports y opciones */
package generated;

import java_cup.runtime.*;

%%

/* Sección 2: Configuración del Lexer */
%class Lexer
%unicode
%cup
%line
%column
%public

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline + 1, yycolumn + 1);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline + 1, yycolumn + 1, value);
    }
%}

/* Macros */
DIGITO    = [0-9]
LETRA     = [a-zA-Z_]
ENTERO    = {DIGITO}+
FLOTANTE  = {DIGITO}+\.{DIGITO}+
ID        = {LETRA}({LETRA}|{DIGITO})*
CADENA    = \"[^\"]*\"
CARACTER  = \'[^\']\'
ESPACIOS  = [ \t\r\n]+

%%

/* Sección 3: Reglas léxicas */

/* Espacios en blanco */
{ESPACIOS}              { /* ignorar */ }

/* Comentarios de linea: | texto hasta fin de linea */
"|"[^\n]*               { /* ignorar */ }

/* Comentarios multilinea: ! texto ! */
"¡"[^!]*"!"             { /* ignorar */ }

/* Palabras reservadas */
"val"           { return symbol(sym.VAL); }
"int"           { return symbol(sym.INT); }
"float"         { return symbol(sym.FLOAT); }
"bool"          { return symbol(sym.BOOL); }
"char"          { return symbol(sym.CHAR); }
"str"           { return symbol(sym.STR); }
"true"          { return symbol(sym.TRUE); }
"false"         { return symbol(sym.FALSE); }
"if"            { return symbol(sym.IF); }
"elif"          { return symbol(sym.ELIF); }
"else"          { return symbol(sym.ELSE); }
"while"         { return symbol(sym.WHILE); }
"for"           { return symbol(sym.FOR); }
"return"        { return symbol(sym.RETURN); }
"break"         { return symbol(sym.BREAK); }
"write"         { return symbol(sym.WRITE); }
"read"          { return symbol(sym.READ); }
"void"          { return symbol(sym.VOID); }
"principal"     { return symbol(sym.PRINCIPAL); }

/* Operadores aritméticos */
"++"            { return symbol(sym.OP_INC); }
"--"            { return symbol(sym.OP_DEC); }
"+"             { return symbol(sym.OP_SUMA); }
"-"             { return symbol(sym.OP_RESTA); }
"*"             { return symbol(sym.OP_MULT); }
"//"            { return symbol(sym.OP_DIV_ENT); }
"/"             { return symbol(sym.OP_DIV); }
"%"             { return symbol(sym.OP_MOD); }
"^"             { return symbol(sym.OP_POT); }

/* Operadores relacionales */
"<="            { return symbol(sym.OP_MENIG); }
">="            { return symbol(sym.OP_MAYIG); }
"=="            { return symbol(sym.OP_IGUAL); }
"!="            { return symbol(sym.OP_DIFER); }
"<"             { return symbol(sym.OP_MENOR); }
">"             { return symbol(sym.OP_MAYOR); }

/* Operadores logicos */
"λ"             { return symbol(sym.OP_AND); }
"θ"             { return symbol(sym.OP_OR); }
"Σ"             { return symbol(sym.OP_NOT); }