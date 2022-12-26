package com.bozo.bozopetclinic.service.springdatajpa;

import com.bozo.bozopetclinic.model.Visit;
import com.bozo.bozopetclinic.repository.VisitRepository;
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
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    Visit returnVisit;

    @BeforeEach
    void setUp() {
        returnVisit = Visit.builder().build();
    }

    @Test
    void findAll() {
        Set<Visit> returnVisitSet = Set.of(returnVisit);

        when(visitRepository.findAll()).thenReturn(returnVisitSet);

        Set<Visit> visitSet = service.findAll();

        assertNotNull(visitSet);
        assertEquals(1, visitSet.size());

        verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(returnVisit));

        Visit visit = service.findById(anyLong());

        assertNotNull(visit);

        verify(visitRepository).findById(anyLong());
    }

    @Test
    void findByIdNoFound() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());

        Visit visit = service.findById(anyLong());

        assertNull(visit);

        verify(visitRepository).findById(anyLong());
    }

    @Test
    void save() {
        Visit visitToSave = Visit.builder().id(2L).build();

        when(visitRepository.save(any())).thenReturn(visitToSave);

        Visit savedVisit = service.save(visitToSave);

        assertNotNull(savedVisit);
        assertEquals(visitToSave.getId(), savedVisit.getId());

        verify(visitRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(any());
        verify(visitRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());
        verify(visitRepository).deleteById(anyLong());
    }
}