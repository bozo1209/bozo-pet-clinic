package com.bozo.bozopetclinic.service;

import com.bozo.bozopetclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById();
    Vet save(Vet vet);
    Set<Vet> findAll();
}
