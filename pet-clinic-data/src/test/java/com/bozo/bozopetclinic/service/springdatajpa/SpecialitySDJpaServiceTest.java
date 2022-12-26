package com.bozo.bozopetclinic.service.springdatajpa;

import com.bozo.bozopetclinic.model.Speciality;
import com.bozo.bozopetclinic.repository.SpecialityRepository;
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
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    Speciality returnSpeciality;

    @BeforeEach
    void setUp() {
        returnSpeciality = Speciality.builder().build();
    }

    @Test
    void findAll() {
        Set<Speciality> returnSpecialitySet = Set.of(returnSpeciality);

        when(specialityRepository.findAll()).thenReturn(returnSpecialitySet);

        Set<Speciality> specialitySet = service.findAll();

        assertNotNull(specialitySet);
        assertEquals(1, specialitySet.size());

        verify(specialityRepository).findAll();
    }

    @Test
    void findById() {
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.of(returnSpeciality));

        Speciality speciality = service.findById(anyLong());

        assertNotNull(speciality);

        verify(specialityRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.empty());

        Speciality speciality = service.findById(anyLong());

        assertNull(speciality);

        verify(specialityRepository).findById(anyLong());
    }

    @Test
    void save() {
        Speciality specialityToSave = Speciality.builder().build();

        when(specialityRepository.save(any())).thenReturn(specialityToSave);

        Speciality savedSpeciality = service.save(specialityToSave);

        assertNotNull(savedSpeciality);

        verify(specialityRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(any());
        verify(specialityRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());
        verify(specialityRepository).deleteById(anyLong());
    }
}