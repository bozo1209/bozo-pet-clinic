package com.bozo.bozopetclinic.service.map;

import com.bozo.bozopetclinic.model.Vet;
import com.bozo.bozopetclinic.service.SpecialtyService;
import com.bozo.bozopetclinic.service.VetService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
@Profile({"default", "map"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        if (object.getSpecialities().size() > 0){
            object.getSpecialities().forEach(speciality -> {
                if (speciality.getId() == null){
                    speciality.setId(specialtyService.save(speciality).getId());
                }
            });
        }
        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
