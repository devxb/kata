package extensionfunction

fun String.firstChar() = last()

fun main() {
    val hello = "hello"

    print(hello.firstChar())
}
