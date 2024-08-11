package com.fin.bancs.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/")
    public String showHome() {

        return "homePage";
    }

    // add a request mapping for /leaders

    @GetMapping("/leaders")
    public String showLeaders() {

        return "leaders";
    }

    // add request mapping for /systems

    @GetMapping("/systems")
    public String showSystems() {

        return "systems";
    }

}








