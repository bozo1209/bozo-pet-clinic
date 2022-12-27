package com.bozo.bozopetclinic.service.map;

import com.bozo.bozopetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistId() {
        Long id = 2L;
        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());

    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(lastName);

        assertNotNull(smith);
        assertEquals(lastName, smith.getLastName());
    }

    @Test
    void findByLastNamNoFound() {
        Owner smith = ownerMapService.findByLastName("foo");

        assertNull(smith);
    }

    @Test
    void findByLastNameLike(){
        List<Owner> ownerList = ownerMapService.findAllByLastNameLike(lastName.substring(1,2));

        assertNotNull(ownerList);
        assertNotNull(ownerList.get(0));
        assertEquals(lastName, ownerList.get(0).getLastName());
    }

    @Test
    void findByLastNameLikeManyFound(){
        ownerMapService.save(Owner.builder().id(ownerId + 1).lastName(lastName + 1).build());
        ownerMapService.save(Owner.builder().id(ownerId + 2).lastName(lastName + 2).build());

        List<Owner> ownerList = ownerMapService.findAllByLastNameLike(lastName.substring(1,2));

        assertNotNull(ownerList);

        for (Owner owner : ownerList) {
            assertNotNull(owner);
        }
    }

    @Test
    void findByLastNameLikeNotFound(){
        List<Owner> ownerList = ownerMapService.findAllByLastNameLike("q");

        assertNotNull(ownerList);
        assertTrue(ownerList.isEmpty());
    }
}