package com.avajl.simulation;
import com.avajl.flyables.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String weather[] = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider(){}

    public static WeatherProvider getProvider(){
        return (weatherProvider);
    }

    public String getCurrentWeather(final Coordinates coordinates) {
        final int random = (coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight())
                + (int) Math.random() * 10;
        return (weather[random % 4]);
    }
}