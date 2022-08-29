package com.bozo.bozopetclinic.service;

import com.bozo.bozopetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById();
    Pet save(Pet pet);
    Set<Pet> findAll();
}
