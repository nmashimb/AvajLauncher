package com.avajl.simulation;
import com.avajl.flyables.*;
import java.io.*;
import java.util.Scanner;

public class Simulation {
    public static PrintWriter writer;

    public static void main(final String[] args) {
        if (args.length < 1){
            return;
        }
       try {
            int runSimulationTimes = 0;
            File fileToRead = new File(args[0]); //How to read arg??
            File simulationFile = new File("simulation.txt");
            Scanner textFileContent = new Scanner(fileToRead); 
            AircraftFactory aircraftFactory = new AircraftFactory();
            WeatherTower weatherTower = new WeatherTower();

            try {
                writer = new PrintWriter(simulationFile);
            } catch (FileNotFoundException fne) {
                System.out.println("Error: " + fne.getMessage());
                return;
            }
            if (simulationFile.exists() && !simulationFile.isDirectory())
                writer.print("");
            while (textFileContent.hasNextLine()){
                String line = textFileContent.nextLine();

                if (line.split(" ").length == 1){
                    if (Integer.parseInt(line) >= 0){
                    runSimulationTimes = Integer.parseInt(line.trim());
                    }
                    else{
                        System.out.println("Error: Invalid number!");
                        return;
                    }
                }
                else if (line.split(" ").length == 5){
                    String[] aircraftInfo = line.split(" ");

                    try {
                        aircraftFactory.newAircraft(aircraftInfo[0], 
                        aircraftInfo[1], 
                        Integer.parseInt(aircraftInfo[2]), 
                        Integer.parseInt(aircraftInfo[3]), 
                        Integer.parseInt(aircraftInfo[4])).registerTower(weatherTower);
                    }
                    catch (Exception e) {
                        System.out.println("Error: Aircraft information incorrect!");
                        return;
                    }
                }
                else {
                    System.out.println("Error: Aircraft info extra!");
                    return;
                } 
            }
            textFileContent.close(); 
            for (int indx = 0; indx < runSimulationTimes; indx++){
                weatherTower.changeWeather();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}