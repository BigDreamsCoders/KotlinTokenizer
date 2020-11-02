import java.util.Scanner

fun main(args: Array<String>){

    // Lista de tokens que se van a evaluar
    val tokens = listOf(
        Pair("string", "\"(.*?)\"".toRegex()),
        Pair("character", "'(\\'|[^']){1}'".toRegex()),
        Pair("data_type", "(?<![a-zA-Zd._])(int|char|bool)(?![a-zA-Z0-9._])".toRegex()),
        Pair("access_modifier", "(?<![a-zA-Z0-9._])(public|private|protected)(?![a-zA-Z0-9._])".toRegex()),
        Pair("statement", "(?<![a-zA-Z0-9._])(if|else|do|while)(?![a-zA-Z0-9._])".toRegex()),
        Pair("indentifier", "[A-Za-z][A-Za-z0-9_]*".toRegex()),
        Pair("number", "(0-9)+(.(0-9)+)?".toRegex()),
        Pair("arithmetic_operator", """(\+|-|\*|/|%|\+\+|--)""".toRegex()),
        Pair("logic_operator", """(\|\||&&|(!(?!=)))""".toRegex()),
        Pair("bits_operator", """(>>|<<|&|\||~|\^)""".toRegex()),
        Pair("relation_operator", """(==|!=|>=|<=|<|>)""".toRegex()),
        Pair("assign", """=""".toRegex()),
        Pair("EOI", """;""".toRegex()),
        Pair("separator", """,""".toRegex()),
        Pair("access", """\.""".toRegex()),
        Pair("block_start", """;""".toRegex()),
        Pair("block_end", """;""".toRegex()),
        Pair("expression_start", """\(""".toRegex()),
        Pair("expression_end", """\)""".toRegex()),
        Pair("array_start", """\[""".toRegex()),
        Pair("array_end", """\]""".toRegex()),
        Pair("arguments", "(\\((?:\\s*[^\\s,]+\\s+[^\\s,]+\\s*,)*\\s*[^\\s,]+\\s+[^\\s,]+\\s*\\))".toRegex())
    )

    // Expresiones regulares para identificar comentarios de una y multiples líneas
    val comments_regex = listOf(
        Pair("oneline", "\"//.*|(\\\"(?:\\\\\\\\[^\\\"]|\\\\\\\\\\\"|.)*?\\\")|(?s)/\\\\*.*?\\\\*/\"".toRegex()),
        Pair("multiline", "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)".toRegex())
    )



    // Lista mutable donde vamos a guardar los tokens que encontramos
    val TokensFound = mutableListOf<Pair<String, String>>()

    // Entrada de datos
    println("Escriba el código fuente, solo es posible leer una linea")

    val reader = Scanner(System.`in`)
    val c_line = reader.nextLine()

    // Primera parte de preparación de los datos ingresados, eliminamos los comentarios de una o multiples líneas
    val new_c_line = c_line.replace(comments_regex[0].second, "").replace(comments_regex[1].second, "")

    // Última parte de preparación de los datos ingresados, hacemos una lista delimitada por espacios
    val blob = new_c_line.replace('\n', ' ').split(" ")

    println("\n\n Lexemas encontrados: ")

    // Vamos a recorrer la primera lista llamada "blob" que contiene todas las palabras que pudo separar con espacios
    // El "outer@" solo es una etiqueta que vamos a usar para poder hacer un break del loop de la siguiente función
    // Estamos utilizando el método "forEach" de la clase List, le pasamos una lambda (función que va a ejecutar con cada elemento de la lista)
    blob.forEach outer@{ w ->
        // Vamos a recorrer por cada elemento de "blob" la lista de tokens que definimos al principio del programa
        // Es la misma dinámica con el método "forEach" que usamos antes
        tokens.forEach { token ->
            // Como nuestra lista de tokens es de Parejas, le asignamos un nombre de variable a el primer y segundo elemento de cada posición de la lista de tokens
            val tokenIdentifier = token.first;
            val pattern = token.second;
            // Realizamos la comprobación la expresión regular correspondiente al token que se está evaluando
            if (pattern.matches(w)) {
                // Si coincide lo guardamos en la lista "TokensFound" y mostramos en consola que tipo de token es
                TokensFound.add(Pair(tokenIdentifier,w))
                println("$w es $tokenIdentifier")
                // Como estamos trabajando con un método (forEach) de la clase List y no un una estructura de iteración común, teneoms que usar esta notación especial, para simular un "break"
                // Así evitamos que ciertas palabras sean clasificadas como dos tokens
                return@outer
            }
        }
    }

    // Imprimimos una manera más legible de los tokens encontrados
    println("\nCódigo fuente original\n$new_c_line \n\nLexemas resultantes");
    TokensFound.forEach{
        print(it.first+" ")
    }
}