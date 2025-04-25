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
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class HomeController {
	
	@Autowired
	private WebClient webClient;
	
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
    
    @GetMapping("/getApiJwt")
    public String getApiJwt(Model model, OAuth2AuthenticationToken authentication) {
    	
    	OAuth2AuthorizedClient client = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
            String accessToken = client.getAccessToken().getTokenValue();
            
            String result = webClient.get()
                    .uri("http://localhost:8082/api/jwt")
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            model.addAttribute("result", result);
      	}

        return "api-result";
    }
    
    @GetMapping("/getApiUser")
    public String getApiUser(Model model, OAuth2AuthenticationToken authentication) {
    	
    	OAuth2AuthorizedClient client = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
            String accessToken = client.getAccessToken().getTokenValue();
            
            String result = webClient.get()
                    .uri("http://localhost:8082/api/user")
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            model.addAttribute("result", result);
      	}

        return "api-result";
    }
    
    @GetMapping("/getApiRoles")
    public String getApiRoles(Model model, OAuth2AuthenticationToken authentication) {
    	
    	OAuth2AuthorizedClient client = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
            String accessToken = client.getAccessToken().getTokenValue();
            
            String result = webClient.get()
                    .uri("http://localhost:8082/api/roles")
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            model.addAttribute("result", result);
      	}

        return "api-result";
    }
}
