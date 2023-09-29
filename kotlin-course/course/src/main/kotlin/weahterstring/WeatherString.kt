package weahterstring

fun main() {
    val s1: String? = null
    val s2: String? = ""
    s1.isEmptyOrNull() eq true
    s2.isEmptyOrNull() eq true

    val s3 = "   "
    s3.isEmptyOrNull() eq false
}

private infix fun Boolean.eq(b: Boolean): Unit = check(b == this)

private fun String?.isEmptyOrNull(): Boolean = this.isNullOrEmpty()
