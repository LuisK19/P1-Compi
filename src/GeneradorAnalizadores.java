package src;

import java.io.File;
import java.io.IOException;

public class GeneradorAnalizadores {

    public void generarLexerYParser(String rutaLexer, String rutaParser) throws Exception {
        File carpetaGenerada = new File("generated");
        if (!carpetaGenerada.exists() && !carpetaGenerada.mkdirs()) {
            throw new IOException("No se pudo crear la carpeta generated.");
        }

        generarLexer(rutaLexer, carpetaGenerada.getPath());
        generarParser(rutaParser, carpetaGenerada.getPath());
    }

    private void generarLexer(String ruta, String destino) throws Exception {
        String[] parametros = {"-d", destino, ruta};
        jflex.Main.main(parametros);
    }

    private void generarParser(String ruta, String destino) throws Exception {
        String[] parametros = {"-destdir", destino, "-parser", "Parser", ruta};
        java_cup.Main.main(parametros);
    }
}