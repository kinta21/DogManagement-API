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
    public Dog createDog(String name, String dogSex, String age, String dogBreed, String region) {
        Dog dogData = new Dog(name, dogSex, age, dogBreed, region);
        dogMapper.createDog(dogData);
        return dogData;
    }

    @Override
    public void updateDog(int id, String name, String dogSex, String age, String dogBreed, String region) {
        Dog dog = dogMapper.findById(id).orElseThrow(() -> new NotDogFoundException("resource not found"));
        dog.setName(name);
        dog.setDogSex(dogSex);
        dog.setAge(age);
        dog.setDogBreed(dogBreed);
        dog.setRegion(region);
        dogMapper.updateDog(dog);
    }


    @Override
    public void deleteById(int id) {
        dogMapper.findById(id).orElseThrow(() -> new NotDogFoundException("resource not found"));
        dogMapper.deleteById(id);
    }
}

