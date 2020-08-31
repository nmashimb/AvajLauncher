package com.avajl.flyables;
import com.avajl.simulation.Simulation;
import com.avajl.simulation.WeatherTower;
import java.util.HashMap;
import java.util.Map;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    public Baloon(final String name, final Coordinates coordinates){
        super(name, coordinates);
    }

    public void updateConditions(){
        final String currentWeather = this.weatherTower.getWeather(this.coordinates);
        final int longitude = this.coordinates.getLongitude();
        final int latitude = this.coordinates.getLatitude();
        final int height = this.coordinates.getHeight();
        final Map<String, String> pilotReponse = new HashMap<String, String>();

        pilotReponse.put("SUN", "Let's enjoy the good weather and take some pics.");
        pilotReponse.put("SNOW", "It's snowing. We're gonna crash.");
        pilotReponse.put("RAIN", "Damn you rain! You messed up my baloon.");
        pilotReponse.put("FOG", "");
        if (currentWeather.matches("SNOW"))
            this.coordinates = new Coordinates(longitude, latitude, height - 15);
        if (currentWeather.matches("RAIN"))
            this.coordinates = new Coordinates(longitude, latitude, height - 5);
        if (currentWeather.matches("FOG"))
            this.coordinates = new Coordinates(longitude, latitude, height - 3);
        if (currentWeather.matches("SUN"))
            this.coordinates = new Coordinates(longitude + 2, latitude, height + 4);
            Simulation.writer.println("Baloon#"+this.name+"("+this.id+"): "+pilotReponse.get(currentWeather));
        if (this.coordinates.getHeight() == 0){
            Simulation.writer.println("Baloon#"+this.name+"("+this.id+")"+" landing.");
            Simulation.writer.print("Tower says: "+"Baloon#"+this.name+"("+this.id+") ");
            this.weatherTower.unregister(this);
        }
    }

    public void registerTower(final WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        Simulation.writer.print("Tower says: "+"Baloon#"+this.name+"("+this.id+") ");
        this.weatherTower.register(this);
    }
}