package com.raisetech.dogmanagement.form;

import lombok.Data;

@Data
public class DogCreateForm {
    private String name;
    private String sex;
    private String age;
    private String dogBreed;
    private String region;

    public DogCreateForm(String name, String sex, String age, String dogBreed, String region) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.dogBreed = dogBreed;
        this.region = region;
    }
}

