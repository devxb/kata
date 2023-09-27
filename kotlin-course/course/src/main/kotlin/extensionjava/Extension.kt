@file:JvmName("Extension")
package extensionjava

fun ExtensionTarget.bye() = "bye";

fun main() {
    val extensionTarget = ExtensionTarget()

    println(extensionTarget.bye())
    println(extensionTarget.hello())
}
