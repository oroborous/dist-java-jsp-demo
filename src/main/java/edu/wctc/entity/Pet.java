package edu.wctc.entity;

public class Pet {
    private int id;
    private String name;
    private int age;
    private String species;
    private PetDetail detail;

    public Pet() {
    }

    public PetDetail getDetail() {
        return detail;
    }

    public void setDetail(PetDetail detail) {
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
