package com.bozo.bozopetclinic.service;

import com.bozo.bozopetclinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
