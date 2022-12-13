package com.bozo.bozopetclinic.controllers;

import com.bozo.bozopetclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping(path = {"", "/", "/index", "/index.html"})
    public String listOwners(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @GetMapping(path = "/find")
    public String findOwners(){
        return "notimplemented";
    }

    @GetMapping(path = "/{ownerId}")
    public String showOwner(@PathVariable("ownerId") Long ownerId, Model model){
        model.addAttribute(ownerService.findById(ownerId));
        return "owners/ownerDetails";
    }
}
