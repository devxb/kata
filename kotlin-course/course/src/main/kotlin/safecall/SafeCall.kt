package safecall

fun main() {
    val nonNullable = "Hello";
    val nullable: String? = "Hello";

    println(nonNullable.length)
//  println(nullable.length) X
    println(nullable?.length) // It's ok

    val nullableInt: Int? = nullable?.length
    val nonNullableInt: Int = nullable?.length ?: 0 // Elvis operator

    println(nullableInt)
    println(nonNullableInt)
}
