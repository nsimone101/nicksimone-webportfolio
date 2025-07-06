package net.nicksimone.webportfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String displayHome() {
        return "home";
    }

    @GetMapping("/about")
    public String displayAboutMe() {
        return "about-me";
    }

    @GetMapping("/overview")
    public String displayOverview() {
        return "overview";
    }

}
