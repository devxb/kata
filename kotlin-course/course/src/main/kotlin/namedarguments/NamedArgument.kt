package namedarguments

class NamedArgument {

    fun callWithOutNamedArguments() {
        println(
            listOf('a', 'b', 'c', 'd')
                .joinToString(",", "(", ")")
        )
    }

    fun callWithNamedArguments() {
        println(
            listOf('a', 'b', 'c', 'd')
                .joinToString(separator = ",", prefix = "(", postfix = ")")
        )
    }

    fun callWithChoiceNamedArguments() {
        println(
            listOf('a', 'b', 'c', 'd')
                .joinToString(prefix = "(")
        )
    }

}
