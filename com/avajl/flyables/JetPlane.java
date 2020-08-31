package com.avajl.flyables;

import com.avajl.simulation.Simulation;
import com.avajl.simulation.WeatherTower;
import java.util.Map;
import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public JetPlane(final String name, final Coordinates coordinates){
        super(name, coordinates);
    }

    public void updateConditions(){
        final String currentWeather = weatherTower.getWeather(this.coordinates);
        final int longitude = this.coordinates.getLongitude();
        final int latitude = this.coordinates.getLatitude();
        final int height = this.coordinates.getHeight();
        final Map<String, String> pilotReponse = new HashMap<String, String>();

        pilotReponse.put("SUN", "");
        pilotReponse.put("SNOW", "");
        pilotReponse.put("RAIN", "It's raining. Better watch out for lightings.");
        pilotReponse.put("FOG", "OMG! Winter is coming!");
        if (currentWeather.matches("SNOW"))
            this.coordinates = new Coordinates(longitude, latitude, height - 12);
        if (currentWeather.matches("RAIN"))
            this.coordinates = new Coordinates(longitude, latitude + 5, height);
        if (currentWeather.matches("FOG"))
            this.coordinates = new Coordinates(longitude, latitude + 1, height);
        if (currentWeather.matches("SUN"))
            this.coordinates = new Coordinates(longitude, latitude + 10, height + 2);
        Simulation.writer.println("JetPlane#"+this.name+"("+this.id+"): "+pilotReponse.get(currentWeather));
        if (this.coordinates.getHeight() == 0) {
            Simulation.writer.println("JetPlane#" + this.name + "(" + this.id + ")" + " landing.");
            Simulation.writer.print("Tower says: " + "JetPlane#" + this.name + "(" + this.id + ") ");
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(final WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        Simulation.writer.print("Tower says: "+"JetPlane#"+this.name+"("+this.id+") ");
        this.weatherTower.register(this);
    }
}