package weathermachine;

public class InJava {

    private String description = "COLD";
    private String color = "BLUE";

    public void updateWeather(int degrees) {
        if (degrees < 20) {
            description = "COLD";
            color = "BLUE";
            return;
        }
        if (degrees > 40) {
            description = "HOT";
            color = "RED";
            return;
        }
        description = "WARM";
        color = "GREEN";
    }

}
