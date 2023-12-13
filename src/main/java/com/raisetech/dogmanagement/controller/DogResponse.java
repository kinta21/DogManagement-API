package com.raisetech.dogmanagement.controller;

import com.raisetech.dogmanagement.entity.Dog;

public class DogResponse {
    private int id;
    private String name;
    private String dogSex;
    private String age;
    private String dogBreed;
    private String region;

    public DogResponse(Dog dog) {
        this.id = dog.getId();
        this.name = dog.getName();
        this.dogSex = dog.getDogSex();
        this.age = dog.getAge();
        this.dogBreed = dog.getDogBreed();
        this.region = dog.getRegion();
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

    public String getDogSex() {
        return dogSex;
    }

    public void setDogSex(String dogSex) {
        this.dogSex = dogSex;
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

