package com.bozo.bozopetclinic.repository;

import com.bozo.bozopetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
