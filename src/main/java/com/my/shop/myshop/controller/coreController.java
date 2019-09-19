package com.my.shop.myshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class coreController {

    @GetMapping(value = "/home")
    public String Home(){
        return "Welcome Home";
    }
}
