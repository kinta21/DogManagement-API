package com.raisetech.dogmanagement.service;

import com.raisetech.dogmanagement.entity.Dog;

public interface DogService {

    Dog findById(int id);

    Dog createDog(String name, String sex, String age, String dogBreed, String region);
}

