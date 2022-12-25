package com.bozo.bozopetclinic.service.map;

import com.bozo.bozopetclinic.model.Owner;
import com.bozo.bozopetclinic.model.Pet;
import com.bozo.bozopetclinic.model.Visit;
import com.bozo.bozopetclinic.service.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapServiceTest {

    VisitService visitService;

    Owner owner;
    Pet pet;

    final Long visitId = 1L;

    @BeforeEach
    void setUp() {
        visitService = new VisitMapService();

        owner = Owner.builder().id(1L).build();
        pet = Pet.builder().owner(owner).id(1L).build();

        visitService.save(Visit.builder().pet(pet).id(visitId).build());
    }

    @Test
    void findAll() {
        Set<Visit> visitSet = visitService.findAll();

        assertEquals(1, visitSet.size());
    }

    @Test
    void findById() {
        Visit foundVisit = visitService.findById(visitId);

        assertNotNull(foundVisit);
        assertEquals(visitId, foundVisit.getId());
    }

    @Test
    void saveExistId() {
        Long id = 2L;
        Visit savedVisit = visitService.save(Visit.builder().id(id).pet(pet).build());

        assertNotNull(savedVisit);
        assertEquals(id, savedVisit.getId());
        assertEquals(pet, savedVisit.getPet());
    }

    @Test
    void saveNoId() {
        Visit savedVisit = visitService.save(Visit.builder().pet(pet).build());

        assertNotNull(savedVisit);
        assertNotNull(savedVisit.getId());
        assertEquals(pet, savedVisit.getPet());
    }

    @Test
    void deleteById() {
        visitService.deleteById(visitId);

        assertEquals(0, visitService.findAll().size());
    }

    @Test
    void delete() {
        visitService.delete(visitService.findById(visitId));

        assertEquals(0, visitService.findAll().size());

    }
}