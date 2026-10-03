# P1: Análisis Léxico y Sintáctico

**Curso:** Compiladores e Intérpretes - Semestre II, 2026  
**Profesor:** Allan Rodríguez Dávila  
**Integrantes:**
- Luis Trejos Rivera, 2022437816 
- Roymar Castillo Carvajal, 2022045111 

**Fecha de entrega:** 3-10-2026

**Estatus de la entrega:** Buena

## Descripción

Proyecto de análisis léxico y sintáctico para un lenguaje con símbolos personalizados.

## Requisitos

- **Java JDK 17** o superior
- **JFlex:** `jflex-full-1.9.1.jar`
- **CUP:** `java-cup-11b.jar` + `java-cup-11b-runtime.jar`

Todos los `.jar` deben colocarse en la carpeta `tools/`.


## Estructura del proyecto

```
├── tools/                  # .jar de JFlex y CUP
├── src/
│   ├── scanner.flex        # Definición del lexer (JFlex)
│   ├── parser.cup          # Definición del parser (CUP)
│   └── Main.java           # Punto de entrada
├── generated/              # Archivos generados por JFlex y CUP
├── out/                    # Clases compiladas (.class)
└── tests/                  # Archivos de prueba
```

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

Cambia el nombre del archivo por el del documento que quieras procesar.

```
java -cp out;tools\java-cup-11b-runtime.jar Main tests\archivo.txt
```

---

## Referencia del lenguaje

### Símbolos especiales

| Función                    | Símbolo | Equivalente típico |
|----------------------------|---------|--------------------|
| Abrir bloque               | `¿:`    | `{`                |
| Cerrar bloque              | `:?`    | `}`                |
| Abrir índice (arreglo)     | `ʃ:`    | `[`                |
| Cerrar índice (arreglo)    | `:ʅ`    | `]`                |
| Abrir paréntesis           | `є:`    | `(`                |
| Cerrar paréntesis          | `:э`    | `)`                |
| Asignación                 | `Ͱ`     | `=`                |
| Fin de sentencia           | `»`     | `;`                |
| Comentario de línea        | `\|`    | `//`               |
| Abrir comentario multilínea| `¡`     | `/*`               |
| Cerrar comentario multilínea| `!`    | `*/`               |

### Operadores lógicos

| Operador | Símbolo |
|----------|---------|
| AND      | `λ`     |
| OR       | `θ`     |
| NOT      | `Σ`     |

### Tipos de datos

| Tipo    | Descripción      |
|---------|------------------|
| `int`   | Entero           |
| `float` | Flotante         |
| `bool`  | Booleano         |
| `char`  | Carácter         |
| `str`   | Cadena de texto  |

### Ejemplo de programa válido

```
void principal ¿:
  val int x Ͱ 5 »
  val float y Ͱ 3.14 »
  write є: x :э »
:?
```

## Notas

- Los archivos generados (`Lexer.java`, `sym.java`, `Parser.java`) **no** se editan a mano;
  siempre se regeneran desde `scanner.flex` y `parser.cup`.

