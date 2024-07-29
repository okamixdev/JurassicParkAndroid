package edu.utsa.cs3443.ict939_lab5;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Chasmosaurine is a Java class containing the information, getters and setters of this specific type
 * of dinosaur family. Implements DInosaur as an interface.
 *
 * @author Carlos Osuna (ict939)
 * UTSA CS 3443 - Lab 5
 */

public abstract class Chasmosaurine implements Dinosaur, Serializable {

    public String name; // Stores the dinosaur's name
    public boolean vegetarian; // Stores if carnivore or not
    public String zone; // Stores zone

    /**
     * Main constructor for the class. Instantiates a Chasmosaurine object and depending on user specification
     * it fills the variables.
     *
     * @param name       -> name of the dino
     * @param vegetarian -> if carnivore or not
     */
    public Chasmosaurine(String name, boolean vegetarian, String zone) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.zone = zone;
    }

    /**
     * @return -> returns the name of the dino
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return -> returns the type of dinosaur as a string combined with the getName function
     */
    public String getType() {
        return "* Sauropod: " + getSubType() + " named " + getName();
    }

    ;

    /**
     * @return returns the type everytime you call the dinosaur
     */
    public String toString() {
        return getType();
    }

    public void setZone(DinoActivity activity, String new_zone, Dinosaur dino) {

        AssetManager manager = activity.getAssets();
        Scanner scanner = null;
        String DinosFile = "dinos.csv";


        try {


            if (activity.openFileInput(DinosFile) != null) {
                InputStream in = activity.openFileInput(DinosFile);
                scanner = new Scanner(in);

            } else {
                InputStream dino_file = manager.open(DinosFile);
                scanner = new Scanner(dino_file);
            }

//            InputStream dino_file = manager.open(DinosFile);
//            scanner = new Scanner(dino_file);

            StringBuffer buffer = new StringBuffer();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                buffer.append(line + System.lineSeparator());
            }

            String fileContents = buffer.toString();

            scanner.close();

            String oldLine = dino.getName() + "," + dino.getSubType() + "," + dino.getZone();
            this.zone = new_zone;
            String newLine = dino.getName() + "," + dino.getSubType() + "," + dino.getZone();

            fileContents = fileContents.replaceAll(oldLine, newLine);

            try {
                OutputStream out = activity.openFileOutput(DinosFile, Context.MODE_PRIVATE);
                out.write(fileContents.getBytes(StandardCharsets.UTF_8));
                out.close();

                // Start MainActivity with an Intent to reload data
                Intent intent = new Intent(activity, MainActivity.class);
                intent.setAction("reload");
                intent.putExtra("from", "storage");
                activity.startActivity(intent);
//                MainActivity.reloadData(activity, "storage");
            } catch (Exception error) {
                System.out.println("Writting ERROR");
//            System.out.println(er1);
            }


        } catch (IOException er1) {
            System.out.println("Error Opening FILE");
//            System.out.println(er1);
        }
    }

    public String getZone() {
        return zone;
    }

    ;

    /**
     * @return if vegetarian or not
     */
    public boolean isVegetarian() {
        return vegetarian;
    }

    ;

    /**
     * @return the type of dinosaur inside of the family (implemented later on)
     */
    public abstract String getSubType();
}
