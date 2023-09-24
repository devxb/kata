package checkingidentifier

fun isValidIdentifier(str: String): Boolean =
    str.isNotBlank() && (str[0] in 'a'..'z' || str[0] in 'A'..'Z' || str[0] == '_')
            && str.all { char ->
        char == '_' || char in '0'..'9' || char in 'a'..'z' || char in 'A'..'Z'
    }

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}
