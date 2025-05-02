package com.niqdev.app.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.niqdev.app.dto.featurekey.FeatureKeyDto;
import com.niqdev.app.dto.featurekey.FindFeatureKeyForm;
import com.niqdev.app.dto.featurekey.RegisterFeatureKeyForm;
import com.niqdev.app.service.FeatureKeyClientService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FeatureKeyController {

	private final FeatureKeyClientService featureKeyClientService;
	
    @GetMapping("/register-feature-key-form")
    public String registerFeatureKeyForm(Model model) {
    	
	    model.addAttribute("formData", RegisterFeatureKeyForm.builder().build());
            
        return "register-feature-key";
    }
	
    @PostMapping("/register-feature-key")
    public String registerFeatureKey(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute RegisterFeatureKeyForm formData) {

    	FeatureKeyDto result = featureKeyClientService.registerFeatureKey(authentication, formData);
	
	    model.addAttribute("result", result);
            
        return "api-result";
    }
    
    @GetMapping("/find-feature-key-form")
    public String findFeatureKeyForm(Model model) {
    	
	    model.addAttribute("request", RegisterFeatureKeyForm.builder().build());
            
        return "find-feature-key";
    }
    
    @PostMapping("/find-feature-key")
    public String findFeatureKey(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute FindFeatureKeyForm form) {
    	
    	FeatureKeyDto result = featureKeyClientService.findFeatureKey(authentication, form);
	
	    model.addAttribute("result", result);
            
        return "api-result";
    }
}
