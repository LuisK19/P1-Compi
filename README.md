# P1: Analisis Lexico y Sintactico

**Curso:** Compiladores e Intérpretes - Semestre II 2026  
**Profesor:** Allan Rodríguez Dávila  
**Integrantes:** 
- Luis Trejos Rivera
- Roymar

## Requisitos
- Java JDK 17 o superior
- JFlex `jflex-full-1.9.1.jar`
- Cup `java-cup-11b.jar` + `java-cup-11b-runtime.jar`
Todos los `.jar` van en la carpeta `tools/`.
 
## Comandos manuales
 
### 1. Generar el Lexer
```
java -jar tools\jflex-full-1.9.1.jar -d generated src\scanner.flex
```
 
### 2. Generar el Parser
```
java -jar tools\java-cup-11b.jar -destdir generated -parser Parser src\parser.cup
```
 
### 3. Compilar
```
javac -cp tools\java-cup-11b-runtime.jar -d out generated\Lexer.java generated\sym.java generated\Parser.java src\Main.java
```
 
### 4. Ejecutar
Cambiar el nombre del archivo por el del documento que quiera procesar.
```
java -cp out;tools\java-cup-11b-runtime.jar Main tests\archivo.txt
```

---
 
## Referencia del lenguaje
 
### Símbolos especiales
 
| Función | Símbolo | Equivalente típico |
|---|---|---|
| Abrir bloque | `¿:` | `{` |
| Cerrar bloque | `:?` | `}` |
| Abrir índice (arreglo) | `ʃ:` | `[` |
| Cerrar índice (arreglo) | `:ʅ` | `]` |
| Abrir paréntesis | `є:` | `(` |
| Cerrar paréntesis | `:э` | `)` |
| Asignación | `Ͱ` | `=` |
| Fin de sentencia | `»` | `;` |
| Comentario de línea | `\|` | `//` |
| Abrir comentario multilínea | `¡` | `/*` |
| Cerrar comentario multilínea | `!` | `*/` |
 
### Operadores lógicos
 
| Operador | Símbolo |
|---|---|
| AND | `λ` |
| OR | `θ` |
| NOT | `Σ` |
 
### Tipos de datos
 
| Tipo | Descripción |
|---|---|
| `int` | Entero |
| `float` | Flotante |
| `bool` | Booleano |
| `char` | Carácter |
| `str` | Cadena de texto |
 
### Ejemplo de programa válido
 
```
void principal ¿:
  val int x Ͱ 5 »
  val float y Ͱ 3.14 »
  write є: x :э »
:?
```