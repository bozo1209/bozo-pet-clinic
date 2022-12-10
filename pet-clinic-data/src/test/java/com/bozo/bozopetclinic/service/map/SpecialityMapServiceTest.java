package com.bozo.bozopetclinic.service.map;

import com.bozo.bozopetclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SpecialityMapServiceTest {

    SpecialityMapService specialityMapService;

    final String dentist = "dentist";
    Speciality savedSpeciality;
    final Long specialityId = 1L;

    @BeforeEach
    void setUp() {
        specialityMapService = new SpecialityMapService();

        savedSpeciality = specialityMapService.save(Speciality.builder().description(dentist).build());
    }

    @Test
    void findAll() {
        Set<Speciality> specialitySet = specialityMapService.findAll();

        assertEquals(1, specialitySet.size());
    }

    @Test
    void findById() {
        Speciality speciality = specialityMapService.findById(specialityId);

        assertNotNull(speciality);
        assertEquals(specialityId, speciality.getId());
        assertEquals(dentist, speciality.getDescription());
    }

    @Test
    void saveExistId() {
        Long id = 2L;
        Speciality speciality = Speciality.builder().build();
        speciality.setId(id);
        Speciality savedSpeciality = specialityMapService.save(speciality);

        assertEquals(id, savedSpeciality.getId());
    }

    @Test
    void saveNoId() {
        Speciality speciality = Speciality.builder().build();
        Speciality savedSpeciality = specialityMapService.save(speciality);

        assertNotNull(savedSpeciality);
        assertNotNull(savedSpeciality.getId());
    }

    @Test
    void delete() {
        specialityMapService.delete(specialityMapService.findById(specialityId));

        assertEquals(0, specialityMapService.findAll().size());
    }

    @Test
    void deleteById() {
        specialityMapService.deleteById(specialityId);

        assertEquals(0, specialityMapService.findAll().size());
    }
}