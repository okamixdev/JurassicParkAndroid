package edu.utsa.cs3443.ict939_lab5;

/**
 * Tyrannosaurus is a Java class containing the information of the dinosaur and overrides
 * of the super class.
 *
 * @author Carlos Osuna (ict939)
 * UTSA CS 3443 - Lab 1
 */

public class Tyrannosaurus extends Theropod {

    /**
     * Main constructor for the class.
     *
     * @param name       -> goes directly to the parent class and sets the name there.
     * @param vegetarian ->-> goes directly to the parent class and sets if T or F there.
     */
    public Tyrannosaurus(String name, boolean vegetarian, String zone) {
        super(name, vegetarian, zone);
    }

    @Override
    public boolean isVegetarian() {
        // TODO Auto-generated method stub
        return super.vegetarian;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.name;
    }

    @Override
    public String getSubType() {
        // TODO Auto-generated method stub
        return "Tyrannosaurus";
    }
}

