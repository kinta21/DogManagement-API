package com.raisetech.dogmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
 public enum DogSex {
    MALE("オス"), FEMALE("メス");

    private String value;

}


