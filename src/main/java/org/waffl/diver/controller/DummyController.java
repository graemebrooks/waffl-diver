package org.waffl.diver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @GetMapping("/dummy")
    public String dummyEndpoint() {
        System.out.println("Splash! I'm diving!");
        return "Splash! I'm diving!";
    }
}