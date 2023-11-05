package com.raisetech.dogmanagement.service;

import com.raisetech.dogmanagement.entity.Dog;
import com.raisetech.dogmanagement.entity.DogSex;

public interface DogService {

    Dog findById(int id);

    Dog createDog(String name, DogSex dogSex, String age, String dogBreed, String region);

    void updateDog(int id, String name, DogSex dogSex, String age, String dogBreed, String region);

    void deleteById(int id);
}

