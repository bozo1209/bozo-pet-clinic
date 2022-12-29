package com.bozo.bozopetclinic.controllers;

import com.bozo.bozopetclinic.model.Owner;
import com.bozo.bozopetclinic.model.Pet;
import com.bozo.bozopetclinic.model.Visit;
import com.bozo.bozopetclinic.service.PetService;
import com.bozo.bozopetclinic.service.VisitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;


import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    @InjectMocks
    VisitController controller;

    MockMvc mockMvc;

    Owner owner;
    Pet pet;
    Visit visit;

    private final UriTemplate visitTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, Long> uriVariables = new HashMap<>();
    private URI visitURI;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).build();
        pet = Pet.builder().id(1L).build();
        visit = Visit.builder().build();

        uriVariables.put("ownerId", owner.getId());
        uriVariables.put("petId", pet.getId());
        visitURI = visitTemplate.expand(uriVariables);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(get(visitURI))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("visit"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("pets/createOrUpdateVisitForm"));

    }

    @Test
    void processNewVisitForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(post(visitURI))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("visit"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("redirect:/owners/{ownerId}"));

        verify(visitService).save(any());
    }

    @Test
    void processNewVisitFormWithDate() throws Exception {
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(post(visitURI)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("date","2018-11-11"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("visit"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(view().name("redirect:/owners/{ownerId}"));

        verify(visitService).save(any());
    }

}