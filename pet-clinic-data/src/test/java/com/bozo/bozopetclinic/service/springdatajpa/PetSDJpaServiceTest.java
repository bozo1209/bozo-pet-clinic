package com.bozo.bozopetclinic.service.springdatajpa;

import com.bozo.bozopetclinic.model.Pet;
import com.bozo.bozopetclinic.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    public static final String PET_NAME = "Rex";
    public static final long PET_ID = 1L;

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService service;

    Pet returnPet;

    @BeforeEach
    void setUp() {
        returnPet = Pet.builder().id(PET_ID).name(PET_NAME).build();
    }

    @Test
    void findAll() {
        Set<Pet> returnPetSet = Set.of(returnPet);

        when(petRepository.findAll()).thenReturn(returnPetSet);

        Set<Pet> petSet = service.findAll();

        assertNotNull(petSet);
        assertEquals(1, petSet.size());

        verify(petRepository).findAll();
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));

        Pet pet = service.findById(PET_ID);

        assertNotNull(pet);
        assertEquals(returnPet, pet);
        assertEquals(returnPet.getId(), pet.getId());

        verify(petRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.empty());

        Pet pet = service.findById(PET_ID);

        assertNull(pet);

        verify(petRepository).findById(anyLong());
    }

    @Test
    void save() {
        Pet petToSave = Pet.builder().name("second").build();

        when(petRepository.save(any())).thenReturn(petToSave);

        Pet savedPet = petRepository.save(petToSave);

        assertNotNull(savedPet);
        assertEquals(petToSave.getName(), savedPet.getName());

        verify(petRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(Pet.builder().build());
        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(returnPet.getId());
        verify(petRepository).deleteById(anyLong());
    }
}