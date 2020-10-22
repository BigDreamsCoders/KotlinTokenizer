
fun main(args: Array<String>){
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
        Pair("arguments", "(\\((?:\\s*[^\\s,]+\\s+[^\\s,]+\\s*,)*\\s*[^\\s,]+\\s+[^\\s,]+\\s*\\))".toRegex())
    )

    val comments_regex = listOf(
        Pair("oneline", "\"//.*|(\\\"(?:\\\\\\\\[^\\\"]|\\\\\\\\\\\"|.)*?\\\")|(?s)/\\\\*.*?\\\\*/\"".toRegex()),
        Pair("multiline", "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)".toRegex())
    )

    val TokensFound = mutableListOf<Pair<String, String>>()

    //val stringInput = readLine()

    val c_line = """
        |int main99 ( char [] args ) {
        |   /* esta mierda no deberia de salir
        |   ni esto
        |   */
        |   //a ber
        |   int a = 0;
        |   return 0 ;
        |}
    """.trimMargin()

    val new_c_line = c_line.replace(comments_regex[0].second, "").replace(comments_regex[1].second, "")
    println(new_c_line);
    val blob = new_c_line.replace('\n', ' ').split(" ")

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

    TokensFound.forEach{
        //print(it.first+" ")
    }
}