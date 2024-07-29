package edu.utsa.cs3443.ict939_lab5;

import java.io.Serializable;
import java.util.ArrayList;

public class Zone implements Serializable {
    private String name;
    private String riskLevel;
    private String acronym;
    private int guests;

    private ArrayList<Dinosaur> dinosaurs = new ArrayList<>();

    public Zone(String name, String riskLevel, String acronym, int guests) {
        this.name = name;
        this.riskLevel = riskLevel;
        this.acronym = acronym;
        this.guests = guests;
    }

    public int getTotalDinos() {
        return dinosaurs.size();
    }

    public String getName() {
        return this.name;
    }

    public String getRiskLevel() {
        return this.riskLevel;
    }

    public String getAcronym() {
        return this.acronym;
    }

    public int getGuests() {
        return this.guests;
    }

    public void addDynos(Dinosaur dino) {
        dinosaurs.add(dino);
    }

    public ArrayList<Dinosaur> getDinoArray() {
        return dinosaurs;
    }

    public void removeDino(Dinosaur dino) {
        dinosaurs.remove(dino);
    }

    public String toString() {


        dinosaurs.forEach((dino) -> {
//            System.out.println(dino + "\n");
            System.out.println(dino + " (" + (dino.isVegetarian() ? "not carnivore" : "carnivore") + ")\n");
        });

        for (Dinosaur dinosaur : dinosaurs) {
            if (dinosaur == null) break;
            System.out.println(dinosaur + " (" + (dinosaur.isVegetarian() ? "not carnivore" : "carnivore") + ")\n");
        }
        ;
        return "";
    }

}
