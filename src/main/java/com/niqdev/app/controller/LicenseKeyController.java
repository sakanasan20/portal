package com.niqdev.app.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.niqdev.app.dto.FindLicenseKeyForm;
import com.niqdev.app.dto.LicenseKeyDto;
import com.niqdev.app.dto.RegisterLicenseKeyForm;
import com.niqdev.app.service.LicenseKeyClientService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LicenseKeyController {

	private final LicenseKeyClientService licenseKeyClientService;
	
    @GetMapping("/register-license-key-form")
    public String registerLicenseKeyForm(Model model) {
    	
	    model.addAttribute("formData", RegisterLicenseKeyForm.builder().build());
            
        return "register-license-key";
    }
	
    @PostMapping("/register-license-key")
    public String registerLicenseKey(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute RegisterLicenseKeyForm formData) {

    	LicenseKeyDto result = licenseKeyClientService.registerLicenseKey(authentication, formData);
	
	    model.addAttribute("result", result);
            
        return "api-result";
    }
    
    @GetMapping("/find-license-key-form")
    public String findLicenseKeyForm(Model model) {
    	
	    model.addAttribute("formData", FindLicenseKeyForm.builder().build());
            
        return "find-license-key";
    }
    
    @PostMapping("/find-license-key")
    public String findLicenseKey(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute FindLicenseKeyForm formData) {
    	
    	LicenseKeyDto result = licenseKeyClientService.findLicenseKey(authentication, formData);
	
	    model.addAttribute("result", result);
            
        return "api-result";
    }
}
