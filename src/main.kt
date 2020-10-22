import com.sun.xml.internal.ws.model.ParameterImpl

fun main(args: Array<String>){
    val tokens = listOf(
        Pair("string", """"(.*?)"""".toRegex()),
        Pair("character", """'(\\'|[^']){1}'""".toRegex()),
        Pair("data_type", """(?<![a-zA-Z\d._])(int|char|bool)(?![a-zA-Z\d._])""".toRegex()),
        Pair("access_modifier", """(?<![a-zA-Z\d._])(public|private)(?![a-zA-Z\d._])""".toRegex()),
        Pair("statement", """(?<![a-zA-Z\d._])(if|else|do|while)(?![a-zA-Z\d._])""".toRegex()),
        Pair("indentifier", """[_a-zA-Z][a-zA-Z\d_]*""".toRegex()),
        Pair("number", """(\d)+(.(\d)+)?""".toRegex()),
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
        Pair("whitespace", """\s+""".toRegex()),
    )

    val TokensFound = mutableListOf<Pair<String, String>>()

    val c_line = """"hola" 'a' 7.7 ++ int ( ) for"""

    val blob = c_line.replace('\n', ' ').split(" ")

    blob.forEach outer@{ w ->
        tokens.forEach { token ->
            val tokenIdentifier = token.first;
            val pattern = token.second;
            if (pattern.matches(w)) {
                TokensFound.add(Pair(tokenIdentifier,w))
                println("$w is $tokenIdentifier")
                return@outer
            }
        }
    }
}