package com.bozo.bozopetclinic.service.springdatajpa;

import com.bozo.bozopetclinic.model.Pet;
import com.bozo.bozopetclinic.repository.PetRepository;
import com.bozo.bozopetclinic.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {

    private final PetRepository petRepository;

    @Override
    public Set<Pet> findAll() {
        return StreamSupport
                .stream(petRepository.findAll().spliterator(), false)
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }
}
