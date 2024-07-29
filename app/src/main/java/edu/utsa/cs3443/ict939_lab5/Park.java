package edu.utsa.cs3443.ict939_lab5;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Park is a Java class containing the main functionality of the project and implementing the
 * Dinosaur interface. Here we instantiate variables and get all the methods together to
 * then show the user what we want at the end.
 *
 * @author Carlos Osuna (ict939)
 * UTSA CS 3443 - Lab 5
 */

public class Park implements Dinosaur, Serializable {

    public int arrayCount; // array used to check where are we at the time we loop over the array
    // stores the park name
    private String name;
    // stores the capacity of the park
    private int capacity;
    // creates an array of dinosaurs
    private Dinosaur[] dinosaurs;
    // creates an array of zones
    private ArrayList<Zone> zones = new ArrayList<>();

    /**
     * This is the constructor for the Park class
     *
     * @param name     -> name of the park
     * @param capacity -> capacity of the park
     */
    public Park(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;

        // assigns the size of the array according to the capacity provided
        dinosaurs = new Dinosaur[capacity];
    }

    public void addZone(Zone zone) {
        zones.add(zone);
    }

    /**
     * This method is to get the dinosaur object from the user and then store it
     * where it corresponds in the dinosaurs array.
     *
     * @param dinosaur -> dinosaur object already instantiated by user
     */
    public void addDino(Dinosaur dinosaur) {
        dinosaurs[arrayCount] = dinosaur;
        arrayCount++;
    }


    /**
     * @return -> returns the park name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the name in case of change
     *
     * @param name -> change name of the park
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return -> returns capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Set capacity of the park
     *
     * @param capacity -> capacity of park
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Zone> getZonesArray() {
        return zones;
    }


    /**
     * @return -> returns the override method of toString with the content we want to show to the
     * user. In this case we loop over the dinosaur array and then display all the information requested.
     */
    public String toString() {

        System.out.println("Welcome to " + getName() + "!\n\n" + "- - - - - - - - - - - - -\n");

        zones.forEach((zone) -> {
            System.out.println(zone + "\n");
        });

        return "";
    }

    public void deleteDB() {
        zones.clear();
    }

    @Override
    public void setZone(DinoActivity activity, String zone, Dinosaur dino) {

    }

    @Override
    public String getZone() {
        return null;
    }

    public void loadDinosData(MainActivity activity, String from) {


        AssetManager manager = activity.getAssets();
        Scanner scanner = null;
        Scanner scanner2 = null;
        Scanner scanner3 = null;
        String ZonesFile = "zones.csv";
        String DinosFile = "dinos.csv";
        String TypesFile = "types.csv";


        try {
            InputStream zone_file = manager.open(ZonesFile);
            scanner = new Scanner(zone_file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");


                Zone zone = new Zone(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]));


                try {

                    if (from.equals("assets")) {
                        InputStream dino_file = manager.open(DinosFile);
                        scanner2 = new Scanner(dino_file);
                        System.out.println("INSIDE ASSETS!!!!!!!!\n");
                    } else if (from.equals("storage")) {
                        InputStream in = activity.openFileInput(DinosFile);
                        scanner2 = new Scanner(in);
                        System.out.println("INSIDE STORAGE!!!!!!!!\n");
                    }


                    InputStream types_file = manager.open(TypesFile);
                    scanner3 = new Scanner(types_file);

                    HashMap<String, String> dinoInfo = new HashMap<String, String>();

                    while (scanner3.hasNextLine()) {
                        String line3 = scanner3.nextLine();
                        String[] tokens3 = line3.split(",");
                        dinoInfo.put(tokens3[0], tokens3[1]);
                    }
                    scanner3.close();


                    while (scanner2.hasNextLine()) {

                        String line2 = scanner2.nextLine();
//                        System.out.println("\n\n" + line2 + "\n\n");
                        String[] tokens2 = line2.split(",");

                        if (tokens2[2].equals(tokens[2])) {


                            switch (tokens2[1]) {
                                case ("Apatosaurus"):
                                    Sauropod apa_dino = new Apatosaurus(tokens2[0], Boolean.parseBoolean(dinoInfo.get("Sauropod")), tokens2[2]);
                                    zone.addDynos(apa_dino);
                                    break;
                                case ("Brachiosaurus"):
                                    Sauropod brach_dino = new Brachiosaurus(tokens2[0], Boolean.parseBoolean(dinoInfo.get("Sauropod")), tokens2[2]);
                                    zone.addDynos(brach_dino);
                                    break;
                                case ("Dilophosaurus"):
                                    Theropod dilo_dino = new Dilophosaurus(tokens2[0], Boolean.parseBoolean(dinoInfo.get("Theropod")), tokens2[2]);
                                    zone.addDynos(dilo_dino);
                                    break;
                                case ("Gallimimus"):
                                    Theropod gali_dino = new Gallimimus(tokens2[0], Boolean.parseBoolean(dinoInfo.get("Theropod")), tokens2[2]);
                                    zone.addDynos(gali_dino);
                                    break;
                                case ("Indominous"):
                                    Theropod indo_dino = new Indominous(tokens2[0], Boolean.parseBoolean(dinoInfo.get("Theropod")), tokens2[2]);
                                    zone.addDynos(indo_dino);
                                    break;
                                case ("Stegosaurus"):
                                    Stegosaur stego_dino = new Stegosaurus(tokens2[0], Boolean.parseBoolean(dinoInfo.get("Stegosaur")), tokens2[2]);
                                    zone.addDynos(stego_dino);
                                    System.out.println("Stegosaur");
                                    System.out.println("SUCCESS");
                                    break;
                                case ("Triceratops"):
                                    Chasmosaurine trice_dino = new Triceratops(tokens2[0], Boolean.parseBoolean(dinoInfo.get("Chasmosaurine")), tokens2[2]);
                                    zone.addDynos(trice_dino);
                                    System.out.println("Chasmosaurine");
                                    System.out.println("SUCCESS");
                                    break;
                                case ("Tyrannosaurus"):
                                    Theropod tyrano_dino = new Tyrannosaurus(tokens2[0], Boolean.parseBoolean(dinoInfo.get("Theropod")), tokens2[2]);
                                    zone.addDynos(tyrano_dino);
                                    break;
                                case ("Velociraptor"):
                                    Theropod veloci_dino = new Velociraptor(tokens2[0], Boolean.parseBoolean(dinoInfo.get("Theropod")), tokens2[2]);
                                    zone.addDynos(veloci_dino);
                                    break;
                            }

                        }
                    }
                    System.out.println("\n\n" + "BEFORE ADD ZONE" + "\n\n");
                    addZone(zone);
                    scanner2.close();
                } catch (Exception er2) {
                    System.out.println("Error Opening FILE STORAGE");
//                    System.out.println(er2);
                }

            }
            scanner.close();

        } catch (IOException er1) {
            System.out.println("Error Opening FILE");
//            System.out.println(er1);
        }
    }


    @Override
    public boolean isVegetarian() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSubType() {
        return null;
    }


}
