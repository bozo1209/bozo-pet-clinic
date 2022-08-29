package com.bozo.bozopetclinic.service;

import com.bozo.bozopetclinic.model.Owner;

import java.util.Set;

public interface OwnerService {

    Owner findByLastName(String lastName);
    Owner findById();
    Owner save(Owner owner);
    Set<Owner> findAll();
}
