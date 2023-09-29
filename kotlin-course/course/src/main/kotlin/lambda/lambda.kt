package lambda


fun main() {
    val isEven: (Int) -> Boolean = { it % 2 == 0 }
    val isEvenSame = { i: Int -> i % 2 == 0 }
    println(isEven)
    println(run {isEven(2)})
    println(isEven(2))
}
