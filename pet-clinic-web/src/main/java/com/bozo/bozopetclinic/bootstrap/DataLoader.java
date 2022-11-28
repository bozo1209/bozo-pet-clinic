package com.bozo.bozopetclinic.bootstrap;

import com.bozo.bozopetclinic.model.*;
import com.bozo.bozopetclinic.service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0){
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("surgery");
        Speciality savedSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("dentistry");
        Speciality savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("michael");
        owner1.setLastName("weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("123123");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("rosco");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("fiona");
        owner2.setLastName("glananne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("123123");

        Pet fionaPet = new Pet();
        fionaPet.setPetType(savedCatPetType);
        fionaPet.setOwner(owner2);
        fionaPet.setBirthDate(LocalDate.now());
        fionaPet.setName("just cat");
        owner2.getPets().add(fionaPet);

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(fionaPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("sneezy kitty");

        visitService.save(catVisit);

        System.out.println("loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("sam");
        vet1.setLastName("axe");
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("jessie");
        vet2.setLastName("porter");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("loaded vets...");
    }
}
