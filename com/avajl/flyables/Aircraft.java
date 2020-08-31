package com.avajl.flyables;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter;

   protected Aircraft(final String name, final Coordinates coordinates){
    this.name = name;
    this.coordinates = coordinates;
    this.id = nextId();
   }
   private long nextId(){
    Aircraft.idCounter++;  
    return Aircraft.idCounter;
   }
}