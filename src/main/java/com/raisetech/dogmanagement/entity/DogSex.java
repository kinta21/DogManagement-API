package com.raisetech.dogmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DogSex {
    MALE("オス"), FEMALE("メス");

    private String dogSex;

    public static DogSex from(String dogSex) {
        for (DogSex d : DogSex.values()){
            if (d.getDogSex().equals(dogSex)) {
                return d;
            }
        }

        return null;
    }
}

