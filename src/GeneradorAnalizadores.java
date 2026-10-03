package src;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class GeneradorAnalizadores {

    public void generarLexerYParser(String rutaLexer, String rutaParser) throws Exception {
        File carpetaGenerada = new File("generated");
        if (!carpetaGenerada.exists() && !carpetaGenerada.mkdirs()) {
            throw new IOException("No se pudo crear la carpeta generated.");
        }

        eliminarArchivosAnteriores(carpetaGenerada);
        generarLexer(rutaLexer, carpetaGenerada.getPath());
        generarParser(rutaParser, carpetaGenerada.getPath());
    }

    private void eliminarArchivosAnteriores(File carpetaGenerada) throws IOException {
        Files.deleteIfExists(new File(carpetaGenerada, "Lexer.java").toPath());
        Files.deleteIfExists(new File(carpetaGenerada, "Parser.java").toPath());
        Files.deleteIfExists(new File(carpetaGenerada, "sym.java").toPath());
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