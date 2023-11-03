package com.raisetech.dogmanagement.controller;

import com.raisetech.dogmanagement.entity.Dog;
import com.raisetech.dogmanagement.entity.DogSex;

public class DogResponse {
    private int id;
    private String name;
    private DogSex dogSex;
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

    public String getName() {
        return name;
    }

    public String getDogSex() {
        return dogSex.getDogSex();}

        public String getAge () {
            return age;
        }

        public String getDogBreed () {
            return dogBreed;
        }

        public String getRegion () {
            return region;
        }
    }

