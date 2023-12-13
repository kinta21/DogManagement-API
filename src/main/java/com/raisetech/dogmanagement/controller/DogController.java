package com.raisetech.dogmanagement.controller;

import com.raisetech.dogmanagement.entity.Dog;
import com.raisetech.dogmanagement.form.DogCreateForm;
import com.raisetech.dogmanagement.form.DogUpdateForm;
import com.raisetech.dogmanagement.service.DogServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

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
        Dog dog = dogService.createDog(dogCreateForm.getName(),dogCreateForm.getDogSex(), dogCreateForm.getAge(), dogCreateForm.getDogBreed(), dogCreateForm.getRegion());
        URI url = uriBuilder
                .path("/dogs/" + dog.getId())
                .build()
                .toUri();
        return ResponseEntity.created(url).body(dog);
    }

    @PatchMapping("/dogs/{id}")
    public ResponseEntity<Map<String, String >> updateDog(@PathVariable int id, @RequestBody DogUpdateForm dogUpdateForm) throws Exception {
        dogService.updateDog(id, dogUpdateForm.getName(), dogUpdateForm.getDogSex(), dogUpdateForm.getAge(), dogUpdateForm.getDogBreed(), dogUpdateForm.getRegion());
        return ResponseEntity.ok(Map.of("message", "dog successfully updated"));
    }

    @DeleteMapping("/dogs/{id}")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable int id) {
        dogService.deleteById(id);
        return ResponseEntity.ok(Map.of("message", "dog successfully delete"));
    }
}

