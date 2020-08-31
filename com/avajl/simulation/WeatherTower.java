package com.avajl.simulation;

import com.avajl.flyables.Coordinates;

public class WeatherTower extends Tower {

    public String getWeather(final Coordinates coordinates){
        return (WeatherProvider.getProvider().getCurrentWeather(coordinates));
    }

    public void changeWeather(){
        this.conditionsChanged(); //USE THIS TO LOOP IN SIMULATION
    } 
}