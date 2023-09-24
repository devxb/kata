package iterwithindex

fun iter() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    for ((index, element) in list.withIndex()) {
        println("$index-> $element")
    }
}
