package com.bozo.bozopetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/owners")
@Controller
public class OwnerController {

    @GetMapping(path = {"", "/", "/index", "/index.html"})
    public String listOwners(){
        return "owners/index";
    }
}
