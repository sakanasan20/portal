package com.niqdev.app.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
	
    @GetMapping("/")
    public String home(Model model, OAuth2AuthenticationToken authentication) {
    	
    	OAuth2AuthorizedClient client = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    		
            OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
            String username = oidcUser.getFullName(); // or getPreferredUsername()
            List<String> roles = oidcUser.getClaimAsStringList("roles"); // 自訂 roles claim

            model.addAttribute("username", username);
            model.addAttribute("roles", roles);

    	}
    	
    	if (client != null) {
          String accessToken = client.getAccessToken().getTokenValue();
          Instant expiresAt = client.getAccessToken().getExpiresAt();
          
          model.addAttribute("accessToken", accessToken);
          model.addAttribute("expiresAt", expiresAt);
    	}
    	
        return "home";
    }
    
    @GetMapping("/profile")
    public String profileFragment(Model model) {
        // 加入你要的資料
        return "profile";
    }
    
    @GetMapping("/admin-page")
    public String adminFragment() {
        return "admin";
    }

    @GetMapping("/api-test")
    public String apiTestFragment() {
        return "api-test";
    }

    @GetMapping("/license")
    public String licenseFragment() {
        return "license";
    }
}
