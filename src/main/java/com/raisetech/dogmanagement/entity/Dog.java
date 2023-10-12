package com.raisetech.dogmanagement.entity;

public class Dog {
    private int id;
    private  String name;
    private  String age;
    private  String dogBreed;
    private  String region;

    public Dog(int id, String name, String age, String dogBreed, String region) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dogBreed = dogBreed;
        this.region = region;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
