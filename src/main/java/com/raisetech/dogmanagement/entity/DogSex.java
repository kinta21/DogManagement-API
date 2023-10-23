package com.raisetech.dogmanagement.entity;

public enum DogSex {
    MALE("オス"), FEMALE("メス");

    private String dogSex;

    DogSex(String dogSex) {
        this.dogSex = dogSex;
    }

    public String getDogSex() {
        return dogSex;
    }
}

