package com.bozo.bozopetclinic.service.springdatajpa;

import com.bozo.bozopetclinic.model.Owner;
import com.bozo.bozopetclinic.repository.OwnerRepository;
import com.bozo.bozopetclinic.repository.PetRepository;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "Smith";
    public static final long OWNER_ID = 1L;
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnersSet = Set.of(returnOwner);

        when(ownerRepository.findAll()).thenReturn(returnOwnersSet);

        Set<Owner> ownerSet = service.findAll();

        assertNotNull(ownerSet);
        assertEquals(1, ownerSet.size());

        verify(ownerRepository).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner smith = service.findById(OWNER_ID);

        assertNotNull(smith);
        assertEquals(OWNER_ID, smith.getId());

        verify(ownerRepository).findById(OWNER_ID);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner smith = service.findById(OWNER_ID);

        assertNull(smith);

        verify(ownerRepository).findById(OWNER_ID);
    }

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(anyString())).thenReturn(returnOwner);

        Owner smith = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(2L).lastName("second").build();

        when(ownerRepository.save(any())).thenReturn(ownerToSave);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);
        assertEquals(2L, savedOwner.getId());
        assertEquals("second", savedOwner.getLastName());

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        ownerRepository.delete(Owner.builder().id(OWNER_ID).lastName(LAST_NAME).build());
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerRepository.deleteById(OWNER_ID);
        verify(ownerRepository).deleteById(anyLong());
    }
}