package com.bozo.bozopetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/vets")
@Controller
public class VetController {

    @GetMapping(path = {"", "/", "/index", "/index.html"})
    public String listVets(){
        return "vets/index";
    }
}
