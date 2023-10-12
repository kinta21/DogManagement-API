package com.raisetech.dogmanagement.controller;

import com.raisetech.dogmanagement.entity.Dog;
import com.raisetech.dogmanagement.service.DogServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogController {


    private final DogServiceImpl dogService;

    public DogController(DogServiceImpl dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/dogs/{id}")
    public DogResponse getDogById(@PathVariable("id") int id){
        Dog dog = dogService.findById(id);
        return  new DogResponse(dog);
    }
}
