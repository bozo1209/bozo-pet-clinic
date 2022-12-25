package com.bozo.bozopetclinic.service.map;

import com.bozo.bozopetclinic.model.Speciality;
import com.bozo.bozopetclinic.model.Vet;
import com.bozo.bozopetclinic.service.SpecialtyService;
import com.bozo.bozopetclinic.service.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VetMapServiceTest {

    VetService vetService;
    SpecialtyService specialtyService;

    final Long vetId = 1L;
    final String vetLastName = "Smith";

    @BeforeEach
    void setUp() {
        specialtyService = new SpecialityMapService();
        vetService = new VetMapService(specialtyService);

        specialtyService.save(Speciality.builder().build());

        vetService.save(Vet.builder().id(vetId).lastName(vetLastName).build());

    }

    @Test
    void findAll() {
        Set<Vet> vetSet = vetService.findAll();

        assertEquals(1, vetSet.size());
    }

    @Test
    void findById() {
        Vet foundVet = vetService.findById(vetId);

        assertNotNull(foundVet);
        assertEquals(vetId, foundVet.getId());
        assertEquals(vetLastName, foundVet.getLastName());
    }

    @Test
    void saveExistId() {
        Long id = 2L;
        Vet savedVet = vetService.save(Vet.builder().id(id).build());

        assertNotNull(savedVet);
        assertEquals(id, savedVet.getId());
    }

    @Test
    void saveNoId() {
        Vet savedVet = vetService.save(Vet.builder().build());

        assertNotNull(savedVet);
        assertNotNull(savedVet.getId());
    }

    @Test
    void deleteById() {
        vetService.deleteById(vetId);

        assertEquals(0, vetService.findAll().size());
    }

    @Test
    void delete() {
        vetService.delete(vetService.findById(vetId));

        assertEquals(0, vetService.findAll().size());
    }
}