package com.avajl.flyables;

public class AircraftFactory {
    public Flyable newAircraft(final String type, final String name, final int longitude, final int latitude, final int height){
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        if (type.matches("Baloon"))
            return (new Baloon(name, coordinates));
        if (type.matches("JetPlane"))
            return (new JetPlane(name, coordinates));
        if (type.matches("Helicopter"))
            return (new Helicopter(name, coordinates));
        return (null);
    }
}