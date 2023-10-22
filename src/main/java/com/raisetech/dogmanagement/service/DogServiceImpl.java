package com.raisetech.dogmanagement.service;

import com.raisetech.dogmanagement.entity.Dog;
import com.raisetech.dogmanagement.exception.NotDogFoundException;
import com.raisetech.dogmanagement.mapper.DogMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    public final DogMapper dogMapper;

    public DogServiceImpl(DogMapper dogMapper) {
        this.dogMapper = dogMapper;
    }

    @Override
    public Dog findById(int id) {
        Optional<Dog> dog =this.dogMapper.findById(id);
        return dog.orElseThrow(() -> new NotDogFoundException("resource not found"));
    }

    @Override
    public Dog createDog(String name, String sex, String age, String dogBreed, String region) {
        Dog dogData = new Dog(name, sex, age, dogBreed, region);
        dogMapper.createDog(dogData);
        return dogData;
    }
}

