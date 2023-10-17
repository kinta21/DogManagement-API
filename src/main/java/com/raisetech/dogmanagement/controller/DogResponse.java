package com.raisetech.dogmanagement.controller;

import com.raisetech.dogmanagement.entity.Dog;

public class DogResponse {
    private int id;
    private  String name;
    private  String sex;
    private  String age;
    private  String dogBreed;
    private  String region;

    public DogResponse(Dog dog) {
        this.id = dog.getId();
        this.name = dog.getName();
        this.sex = dog.getSex();
        this.age = dog.getAge();
        this.dogBreed = dog.getDogBreed();
        this.region = dog.getRegion();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public String getRegion() {
        return region;
    }
}
