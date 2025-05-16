package com.Usuarios.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/licencas")
public class testeController {

    @GetMapping
    public String testesSecurity(){
        return "deu bom";
    }

}
