package com.bozo.bozopetclinic.service;

import com.bozo.bozopetclinic.model.Owner;

import java.util.List;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);
}
