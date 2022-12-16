package com.bozo.bozopetclinic.controllers;

import com.bozo.bozopetclinic.model.Owner;
import com.bozo.bozopetclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(path = "/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if (owner.getLastName() == null){
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

        if (results.isEmpty()){
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }else if(results.size() == 1){
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }else {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping(path = "/{ownerId}")
    public String showOwner(@PathVariable Long ownerId, Model model){
        model.addAttribute(ownerService.findById(ownerId));
        return "owners/ownerDetails";
    }

    @GetMapping(path = "/new")
    public String initCreationForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping(path = "/new")
    public String processCreateForm(@Valid Owner owner, BindingResult result){
        if (result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping(path = "/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model){
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping(path = "/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner,
                                         BindingResult result,
                                         @PathVariable Long ownerId){
        if (result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
