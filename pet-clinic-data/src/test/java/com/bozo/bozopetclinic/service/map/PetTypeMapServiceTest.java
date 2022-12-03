package com.bozo.bozopetclinic.service.map;

import com.bozo.bozopetclinic.model.Owner;
import com.bozo.bozopetclinic.model.Pet;
import com.bozo.bozopetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeMapServiceTest {

    PetMapService petMapService;
    PetTypeMapService petTypeMapService;
    OwnerMapService ownerMapService;

    Owner owner;
    final String ownerLastName = "Stef";

    PetType petType;
    final Long petTypeId = 1L;
    final String petTypeName = "cat";

    Pet pet;
    final String petName = "soft kitty";


    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petTypeMapService = new PetTypeMapService();
        ownerMapService = new OwnerMapService(petTypeMapService, petMapService);

        owner = ownerMapService.save(Owner.builder().lastName(ownerLastName).build());
        petType = petTypeMapService.save(PetType.builder().name(petTypeName).build());

        pet = petMapService.save(Pet.builder().name(petName).owner(owner).petType(petType).build());
    }

    @Test
    void findAll() {
        Set<PetType> petTypeSet = petTypeMapService.findAll();

        assertEquals(1, petTypeSet.size());
    }

    @Test
    void findById() {
        PetType petType = petTypeMapService.findById(petTypeId);

        assertNotNull(petType);
        assertEquals(petTypeId, petType.getId());
        assertEquals(petTypeName, petType.getName());
    }

    @Test
    void saveExistId() {
        Long id = 2L;
        PetType petType = PetType.builder().build();
        petType.setId(id);
        PetType savedPetType = petTypeMapService.save(petType);

        assertEquals(id, savedPetType.getId());
    }

    @Test
    void saveNoId() {
        PetType petType = PetType.builder().build();
        PetType savedPetType = petTypeMapService.save(petType);

        assertNotNull(savedPetType);
        assertNotNull(savedPetType.getId());
    }

    @Test
    void delete() {
        petTypeMapService.delete(petTypeMapService.findById(petTypeId));
        Pet findedPet = petMapService.findById(pet.getId());

        assertEquals(0, petTypeMapService.findAll().size());
        assertNotNull(findedPet);
    }

    @Test
    void deleteById() {
        petTypeMapService.deleteById(petTypeId);
        Pet findedPet = petMapService.findById(pet.getId());

        assertEquals(0, petTypeMapService.findAll().size());
        assertNotNull(findedPet);

    }
}