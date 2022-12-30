package com.bozo.bozopetclinic.controllers;

import com.bozo.bozopetclinic.model.Vet;
import com.bozo.bozopetclinic.service.VetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
@AllArgsConstructor
public class VetController {

    private final VetService vetService;

    @GetMapping(path = {"/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(Model model){
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    @GetMapping(path = "/api/vets")
    @ResponseBody
    public Set<Vet> getVetsJson(){
        return vetService.findAll();
    }
}
