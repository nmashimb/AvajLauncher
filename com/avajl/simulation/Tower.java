package com.avajl.simulation;
import java.util.ArrayList;
import java.util.List;
import com.avajl.flyables.Flyable;

public abstract class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable){
        if (!this.observers.contains(flyable)){
            this.observers.add(flyable);
            Simulation.writer.println("registered to weather tower.");
        }
    }
    
    public void unregister(Flyable flyable){
        this.observers.remove(flyable);
        Simulation.writer.println("unregistered from weather tower.");
    }

    protected void conditionsChanged(){
        int i = 0;
        while (i < observers.size()){
            this.observers.get(i++).updateConditions();
        }
    }
}