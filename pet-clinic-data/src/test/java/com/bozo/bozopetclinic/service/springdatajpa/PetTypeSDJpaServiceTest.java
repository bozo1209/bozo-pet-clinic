package com.bozo.bozopetclinic.service.springdatajpa;

import com.bozo.bozopetclinic.model.PetType;
import com.bozo.bozopetclinic.repository.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetTypeSDJpaServiceTest {

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSDJpaService service;

    PetType returnPetType;
    private static final Long petTypeId = 1L;

    @BeforeEach
    void setUp() {
        returnPetType = PetType.builder().id(petTypeId).build();
    }

    @Test
    void findAll() {
        Set<PetType> returnPetTypeSet = Set.of(this.returnPetType);

        when(petTypeRepository.findAll()).thenReturn(returnPetTypeSet);

        Set<PetType> petTypeSet = service.findAll();

        assertNotNull(petTypeSet);
        assertEquals(1, petTypeSet.size());

        verify(petTypeRepository).findAll();
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnPetType));

        PetType petType = service.findById(petTypeId);

        assertNotNull(petType);
        assertEquals(petTypeId, petType.getId());

        verify(petTypeRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.empty());

        PetType petType = service.findById(petTypeId);

        assertNull(petType);

        verify(petTypeRepository).findById(anyLong());
    }

    @Test
    void save() {
        PetType petTypeToSave = PetType.builder().id(2L).build();

        when(petTypeRepository.save(any())).thenReturn(petTypeToSave);

        PetType savedPetType = service.save(petTypeToSave);

        assertNotNull(savedPetType);
        assertEquals(petTypeToSave.getId(), savedPetType.getId());

        verify(petTypeRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(PetType.builder().build());
        verify(petTypeRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(petTypeId);
        verify(petTypeRepository).deleteById(anyLong());
    }
}