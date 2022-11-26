package com.bozo.bozopetclinic.repository;

import com.bozo.bozopetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
