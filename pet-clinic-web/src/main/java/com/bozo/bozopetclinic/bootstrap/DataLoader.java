package com.bozo.bozopetclinic.bootstrap;

import com.bozo.bozopetclinic.model.Owner;
import com.bozo.bozopetclinic.model.Vet;
import com.bozo.bozopetclinic.service.OwnerService;
import com.bozo.bozopetclinic.service.VetService;
import com.bozo.bozopetclinic.service.map.OwnerServiceMap;
import com.bozo.bozopetclinic.service.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("michael");
        owner1.setLastName("weston");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("fiona");
        owner2.setLastName("glananne");
        ownerService.save(owner2);

        System.out.println("loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("sam");
        vet1.setLastName("axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("jessie");
        vet2.setLastName("porter");
        vetService.save(vet2);

        System.out.println("loaded vets...");
    }
}
