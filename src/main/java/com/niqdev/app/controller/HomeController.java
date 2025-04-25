package com.niqdev.app.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, OAuth2AuthenticationToken authentication) {
    	if (authentication != null) model.addAttribute("userName", authentication.getPrincipal().getName());
        return "home"; // resources/templates/home.html
    }
}
