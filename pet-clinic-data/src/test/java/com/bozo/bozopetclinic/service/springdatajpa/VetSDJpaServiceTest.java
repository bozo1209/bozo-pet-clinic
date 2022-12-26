package com.bozo.bozopetclinic.service.springdatajpa;

import com.bozo.bozopetclinic.model.Vet;
import com.bozo.bozopetclinic.repository.VetRepository;
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
class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService service;

    Vet returnVet;
    private final static Long VET_ID = 1L;

    @BeforeEach
    void setUp() {
        returnVet = Vet.builder().id(VET_ID).build();
    }

    @Test
    void findAll() {
        Set<Vet> returnVetSet = Set.of(this.returnVet);

        when(vetRepository.findAll()).thenReturn(returnVetSet);

        Set<Vet> vetSet = service.findAll();

        assertNotNull(vetSet);
        assertEquals(1, returnVetSet.size());

        verify(vetRepository).findAll();
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));

        Vet vet = service.findById(VET_ID);

        assertNotNull(vet);
        assertEquals(VET_ID, vet.getId());

        verify(vetRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());

        Vet vet = service.findById(VET_ID);

        assertNull(vet);

        verify(vetRepository).findById(anyLong());
    }

    @Test
    void save() {
        Vet vetToSave = Vet.builder().id(2L).build();

        when(vetRepository.save(any())).thenReturn(vetToSave);

        Vet savedVet = service.save(vetToSave);

        assertNotNull(savedVet);
        assertEquals(vetToSave.getId(), savedVet.getId());

        verify(vetRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(any());
        verify(vetRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());
        verify(vetRepository).deleteById(anyLong());
    }
}