package extensionfunction

fun List<Int>.customSum(): Int = this.reduce { total, element -> total + element }

fun main() {
    val sum = listOf(1, 2, 3).customSum()

    println(sum)    // 6
}
