package npe

fun main() {
    val nullValue: String?

    npe(value = null)
}

private fun npe(value: String?) = value!!
