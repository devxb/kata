package rangecheck

fun main() {
    val first = Range(1);
    val second = Range(2);
    val third = Range(3);

    print(second in first..third)
}
