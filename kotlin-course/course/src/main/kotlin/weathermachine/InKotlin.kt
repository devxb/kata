package weathermachine

class InKotlin(private var description: String = "COLD", private var color: String = "BLUE") {

    fun updateWeather(degrees: Int) {
        val (updatedDescription, updatedColor) = when {
            degrees < 20 -> "COLD" to "BLUE"
            degrees > 40 -> "HOT" to "RED"
            else -> "WARM" to "GREEN"
        }

        description = updatedDescription
        color = updatedColor
    }

}
