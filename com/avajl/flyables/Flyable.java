package com.avajl.flyables;
import com.avajl.simulation.WeatherTower;

public interface Flyable {
    public void updateConditions();
    public void registerTower(WeatherTower weatherTower);
}