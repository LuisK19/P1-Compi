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

