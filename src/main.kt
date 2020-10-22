fun main(args: Array<String>){
    val tokens = listOf(
        Pair("t_string", """"(.*?)"""".toRegex()),
        Pair("t_character", """'(\\'|[^']){1}'""".toRegex()),
        Pair("t_number", """(\d)+(.(\d)+)?""".toRegex()),
        Pair("t_arithmetic_operator", """(\+|-|\*|/|%|\+\+|--)""".toRegex()),
    )

    val c_line = """hola 'a' 7.7 ++ int"""

    val blob = c_line.replace('\n', ' ').split(" ")

    blob.forEach { w ->
        tokens.forEach { token ->
            val tokenIdentifier = token.first;
            val pattern = token.second;
            if (pattern.matches(w)) println("$w is $tokenIdentifier")
        }
    }
}