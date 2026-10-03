package src;

import generated.Lexer;
import generated.Parser;
import generated.sym;
import java_cup.runtime.Symbol;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Analizador {

    public static void main(String[] args) {
        String archivoEntrada = args.length > 0 ? args[0] : "tests\\error_lexico.txt";
        String archivoSalidaTokens = args.length > 1 ? args[1] : "tokens_encontrados.csv";

        if (!new File(archivoEntrada).exists()) {
            System.err.println("No se encontro el archivo de entrada: " + archivoEntrada);
            return;
        }

        System.out.println("--- INICIANDO COMPILADOR ---");
        System.out.println("Archivo de entrada: " + archivoEntrada);

        if (!generarArchivoDeTokens(archivoEntrada, archivoSalidaTokens)) {
            System.out.println("\nRESULTADO: No se pudo leer el archivo o generar el reporte CSV.");
            return;
        }

        ejecutarAnalisisSintactico(archivoEntrada);
    }

    public static boolean generarArchivoDeTokens(String rutaEntrada, String rutaSalida) {
        try (BufferedReader lector = Files.newBufferedReader(Path.of(rutaEntrada), StandardCharsets.UTF_8);
                BufferedWriter escritor = Files.newBufferedWriter(Path.of(rutaSalida), StandardCharsets.UTF_8)) {

            Lexer analizadorLexico = new Lexer(lector);
            Symbol tokenActual;

            escritor.write("id_token,lexema,categoria,tabla");
            escritor.newLine();

            while ((tokenActual = analizadorLexico.next_token()).sym != 0) {
                String lexema = tokenActual.value == null ? "" : tokenActual.value.toString();
                String categoria = clasificarToken(tokenActual.sym);

                escritor.write(tokenActual.sym + ","
                        + escaparCampoCsv(lexema) + ","
                        + escaparCampoCsv(categoria) + ","
                        + escaparCampoCsv(tablaToken(tokenActual.sym)));
                escritor.newLine();
            }

            if (analizadorLexico.huboErroresLexicos()) {
                System.err.print(analizadorLexico.obtenerErroresLexicos());
                System.out.println("ERROR LEXICO: Se encontraron caracteres no reconocidos. Revisa -> " + rutaSalida);
            } else {
                System.out.println("EXITO: Archivo de tokens generado en -> " + rutaSalida);
            }
            return true;
        } catch (Exception e) {
            System.out.println("ERROR LEXICO/ARCHIVO: No se pudo generar los tokens.");
            e.printStackTrace();
            return false;
        }
    }

    private static String clasificarToken(int idToken) {
        switch (idToken) {
            case sym.VAL:
            case sym.IF:
            case sym.ELIF:
            case sym.ELSE:
            case sym.WHILE:
            case sym.FOR:
            case sym.RETURN:
            case sym.BREAK:
            case sym.WRITE:
            case sym.READ:
            case sym.VOID:
            case sym.PRINCIPAL:
                return "Palabra reservada";

            case sym.INT:
            case sym.FLOAT:
            case sym.BOOL:
            case sym.CHAR:
            case sym.STR:
                return "Tipo de dato";

            case sym.ID:
                return "Identificador";

            case sym.ENTERO:
                return "Literal int";
            case sym.FLOTANTE:
                return "Literal float";
            case sym.CADENA:
                return "Literal de string";
            case sym.CARACTER:
                return "Literal de char";
            case sym.TRUE:
            case sym.FALSE:
                return "Literal boolean";

            case sym.OP_SUMA:
            case sym.OP_RESTA:
            case sym.OP_MULT:
            case sym.OP_DIV:
            case sym.OP_DIV_ENT:
            case sym.OP_MOD:
            case sym.OP_POT:
            case sym.OP_INC:
            case sym.OP_DEC:
            case sym.OP_MENOR:
            case sym.OP_MAYOR:
            case sym.OP_MENIG:
            case sym.OP_MAYIG:
            case sym.OP_IGUAL:
            case sym.OP_DIFER:
            case sym.OP_AND:
            case sym.OP_OR:
            case sym.OP_NOT:
            case sym.ASIGNACION:
                return "Operador";

            case sym.BLOQUE_ABRE:
            case sym.BLOQUE_CIERRA:
            case sym.INDICE_ABRE:
            case sym.INDICE_CIERRA:
            case sym.PAREN_ABRE:
            case sym.PAREN_CIERRA:
            case sym.FIN_SENT:
            case sym.COMA:
                return "Simbolo";

            default:
                return "Token no clasificado";
        }
    }

    private static String tablaToken(int idToken) {
        switch (idToken) {
            case sym.ID:
                return "Tabla de identificadores";
            case sym.ENTERO:
            case sym.FLOTANTE:
            case sym.CADENA:
            case sym.CARACTER:
            case sym.TRUE:
            case sym.FALSE:
                return "Tabla de literales";
            default:
                return "No aplica";
        }
    }

    private static String escaparCampoCsv(String valor) {
        return "\"" + valor.replace("\"", "\"\"") + "\"";
    }

    public static void ejecutarAnalisisSintactico(String rutaEntrada) {
        try (BufferedReader lector = Files.newBufferedReader(Path.of(rutaEntrada), StandardCharsets.UTF_8)) {
            Lexer analizadorLexico = new Lexer(lector);
            Parser analizadorSintactico = new Parser(analizadorLexico);

            System.out.println("\nIniciando analisis sintactico...");
            analizadorSintactico.parse();

            if (analizadorLexico.huboErroresLexicos()
                    || analizadorSintactico.huboErroresSintacticos()) {
                System.out.println(
                        "\nRESULTADO: El archivo fuente NO puede ser generado por la gramatica (Errores encontrados).");
                return;
            }

            System.out.println("\nRESULTADO: El archivo fuente SI puede ser generado por la gramatica.");
        } catch (Exception e) {
            System.err.println("Error durante el analisis: " + e.getMessage());
            System.out.println(
                    "\nRESULTADO: El archivo fuente NO puede ser generado por la gramatica (Errores encontrados).");
        }
    }
}