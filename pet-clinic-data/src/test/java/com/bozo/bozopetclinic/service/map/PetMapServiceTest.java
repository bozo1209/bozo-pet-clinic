package com.bozo.bozopetclinic.service.map;

import com.bozo.bozopetclinic.model.Owner;
import com.bozo.bozopetclinic.model.Pet;
import com.bozo.bozopetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

    PetMapService petMapService;
    PetTypeMapService petTypeMapService;
    OwnerMapService ownerMapService;

    Owner owner;
    final String ownerLastName = "Stef";

    PetType petType;
    final String petTypeName = "cat";

    final Long petId = 1L;
    final String petName = "soft kitty";

    Pet savedPet;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petTypeMapService = new PetTypeMapService();
        ownerMapService = new OwnerMapService(petTypeMapService, petMapService);

        owner = ownerMapService.save(Owner.builder().lastName(ownerLastName).build());
        petType = petTypeMapService.save(PetType.builder().name(petTypeName).build());

        savedPet = petMapService.save(Pet.builder().id(petId).name(petName).owner(owner).petType(petType).build());
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();

        assertEquals(1, petSet.size());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(petId);

        assertNotNull(pet);
        assertEquals(petId, pet.getId());
        assertEquals(petName, pet.getName());
    }

    @Test
    void saveExistId() {
        Long id = 2L;
        Pet pet = Pet.builder().id(id).build();
        Pet savedPet = petMapService.save(pet);

        assertEquals(id, savedPet.getId());
    }

    @Test
    void saveNoId() {
        Pet pet = Pet.builder().build();
        Pet savedPet = petMapService.save(pet);

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(petId);
        Owner findedOwner = ownerMapService.findByLastName(ownerLastName);
        PetType findedPetType = petTypeMapService.findById(petType.getId());

        assertEquals(0, petMapService.findAll().size());

        assertNotNull(findedOwner);
        assertEquals(ownerLastName, findedOwner.getLastName());

        assertNotNull(findedPetType);
        assertEquals(petTypeName, findedPetType.getName());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(petId));
        Owner findedOwner = ownerMapService.findByLastName(ownerLastName);
        PetType findedPetType = petTypeMapService.findById(petType.getId());

        assertEquals(0, petMapService.findAll().size());

        assertNotNull(findedOwner);
        assertEquals(ownerLastName, findedOwner.getLastName());

        assertNotNull(findedPetType);
        assertEquals(petTypeName, findedPetType.getName());
    }

}