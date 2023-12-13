package com.raisetech.dogmanagement.service;

import com.raisetech.dogmanagement.entity.Dog;

public interface DogService {

    Dog findById(int id);

    Dog createDog(String name, String dogSex, String age, String dogBreed, String region);

    void updateDog(int id, String name, String dogSex, String age, String dogBreed, String region);

    void deleteById(int id);
}

