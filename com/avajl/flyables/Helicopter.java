package com.avajl.flyables;

import com.avajl.simulation.Simulation;
import com.avajl.simulation.WeatherTower;
import java.util.HashMap;
import java.util.Map;

class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Helicopter(final String name, final Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        final String currentWeather = weatherTower.getWeather(this.coordinates);
        final int longitude = this.coordinates.getLongitude();
        final int latitude = this.coordinates.getLatitude();
        final int height = this.coordinates.getHeight();
        final Map<String, String> pilotReponse = new HashMap<String, String>();

        pilotReponse.put("SUN", "This is hot.");
        pilotReponse.put("SNOW", "My rotor is going to freeze!");
        pilotReponse.put("RAIN", "");
        pilotReponse.put("FOG", "");
        if (currentWeather.matches("SNOW"))
            this.coordinates = new Coordinates(longitude, latitude, height - 12);
        if (currentWeather.matches("RAIN"))
            this.coordinates = new Coordinates(longitude + 5, latitude, height);
        if (currentWeather.matches("FOG"))
            this.coordinates = new Coordinates(longitude + 1, latitude, height);
        if (currentWeather.matches("SUN"))
            this.coordinates = new Coordinates(longitude + 10, latitude, height + 2);
        Simulation.writer.println("Helicopter#"+this.name+"("+this.id+"): "+pilotReponse.get(currentWeather));
        if (this.coordinates.getHeight() == 0) {
            Simulation.writer.println("Helicopter#" + this.name + "(" + this.id + ")" + " landing.");
            Simulation.writer.print("Tower says: " + "Helicopter#" + this.name + "(" + this.id + ") ");
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(final WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        Simulation.writer.print("Tower says: "+"Helicopter#"+this.name+"("+this.id+") ");
        weatherTower.register(this);
    }
}