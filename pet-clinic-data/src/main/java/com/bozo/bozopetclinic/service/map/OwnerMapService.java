package com.bozo.bozopetclinic.service.map;

import com.bozo.bozopetclinic.model.Owner;
import com.bozo.bozopetclinic.service.OwnerService;
import com.bozo.bozopetclinic.service.PetService;
import com.bozo.bozopetclinic.service.PetTypeService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {
        if (object != null){
            if (object.getPets() != null){
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null){
                        if (pet.getPetType().getId() == null){
                            pet.setPetType(petTypeService.save(pet.getPetType()));
                        }
                        if (pet.getId() == null){
                            pet.setId(petService.save(pet).getId());
                        }
                    }else {
                        throw new RuntimeException("pet type is required");
                    }
                });
            }
            return super.save(object);
        }else {
            return null;
        }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
//        todo - impl
        return null;
    }
}
