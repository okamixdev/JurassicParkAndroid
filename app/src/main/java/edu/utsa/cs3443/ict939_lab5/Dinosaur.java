package edu.utsa.cs3443.ict939_lab5;


/**
 * Dinosaur is a Java class containing the interface that is implemented across all of the classes.
 * Here we have these methods :
 * <p>
 * public boolean isVegetarian();
 * public String getName();
 * public String getType();
 * public String toString();
 *
 * @author Carlos Osuna (ict939)
 * UTSA CS 3443 - Lab 5
 */
public interface Dinosaur {

    /**
     * @return -> returns if dinosaur is carnivore or not
     */
    public boolean isVegetarian();

    /**
     * @return -> returns the name of the dinosaur
     */
    public String getName();

    /**
     * @return -> returns the type of dinosaur
     */
    public String getType();

    public String getSubType();

    /**
     * @return -> returns the toString override
     */
    public String toString();

    public void setZone(DinoActivity activity,String zone, Dinosaur dino);

    public String getZone();
}

