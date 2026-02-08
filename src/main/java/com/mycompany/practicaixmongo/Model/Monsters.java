package com.mycompany.practicaixmongo.Model;

import java.util.List;

public class Monsters {
    private int id;
    private String name;
    private String species;
    private String type;
    private String description;
    private List<String> elements;


    public Monsters() {
    }

    public Monsters(int id, String name, String species, String type, String description,
                   List<String> elements) {

        this.id = id;
        this.name = name;
        this.species = species;
        this.type = type;
        this.description = description;
        this.elements = elements;
    }

    // Getters y Setters

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getElements() { return elements; }
    public void setElements(List<String> elements) { this.elements = elements; }
}
