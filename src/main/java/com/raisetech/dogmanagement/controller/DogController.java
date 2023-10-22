package com.raisetech.dogmanagement.controller;

import com.raisetech.dogmanagement.entity.Dog;
import com.raisetech.dogmanagement.form.DogCreateForm;
import com.raisetech.dogmanagement.service.DogServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    @PostMapping("/dogs")
    public ResponseEntity<Dog> createDog(@RequestBody @Valid DogCreateForm dogCreateForm, UriComponentsBuilder uriBuilder) {
        Dog dog = dogService.createDog(dogCreateForm.getName(), dogCreateForm.getSex(), dogCreateForm.getAge(), dogCreateForm.getDogBreed(), dogCreateForm.getRegion());
        URI url = uriBuilder
                .path("/dogs/" + dog.getId())
                .build()
                .toUri();
        return ResponseEntity.created(url).body(dog);
    }
}

