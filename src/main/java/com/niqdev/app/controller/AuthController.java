package com.niqdev.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class AuthController {
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    
    @GetMapping("/get-api-jwt")
    public String getApiJwt(Model model, OAuth2AuthenticationToken authentication) {
    	
    	OAuth2AuthorizedClient client = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
            String accessToken = client.getAccessToken().getTokenValue();
            
            String result = webClient.get()
                    .uri("http://localhost:8081/api/jwt")
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            model.addAttribute("result", result);
      	}

        return "api-result";
    }
    
    @GetMapping("/get-api-user")
    public String getApiUser(Model model, OAuth2AuthenticationToken authentication) {
    	
    	OAuth2AuthorizedClient client = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
            String accessToken = client.getAccessToken().getTokenValue();
            
            String result = webClient.get()
                    .uri("http://localhost:8081/api/user")
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            model.addAttribute("result", result);
      	}

        return "api-result";
    }
    
    @GetMapping("/get-api-roles")
    public String getApiRoles(Model model, OAuth2AuthenticationToken authentication) {
    	
    	OAuth2AuthorizedClient client = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
            String accessToken = client.getAccessToken().getTokenValue();
            
            String result = webClient.get()
                    .uri("http://localhost:8081/api/roles")
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            model.addAttribute("result", result);
      	}

        return "api-result";
    }
}
