package com.bozo.bozopetclinic.controllers;

import com.bozo.bozopetclinic.model.Pet;
import com.bozo.bozopetclinic.model.Visit;
import com.bozo.bozopetclinic.service.PetService;
import com.bozo.bozopetclinic.service.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/owners/{ownerId}")
@AllArgsConstructor
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model){
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        Visit visit = Visit.builder()
//                .pet(pet)
                .build();
        pet.getVisits().add(visit);
        return visit;
    }

    @GetMapping(path = "/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable Long petId, Model model){
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping(path = "/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result){
        if (result.hasErrors()){
            return "pets/createOrUpdateVisitForm";
        }else {
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }
}
