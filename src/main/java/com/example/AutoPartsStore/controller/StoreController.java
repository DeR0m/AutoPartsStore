package com.example.AutoPartsStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {
    @GetMapping("/information")
    public String storeInformation(){
        return "storeInformation";
    }
}
