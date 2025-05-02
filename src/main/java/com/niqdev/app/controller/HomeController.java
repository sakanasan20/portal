package com.niqdev.app.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niqdev.app.dto.UserInfoDto;

@Controller
public class HomeController {
	
//	@Autowired
//    private OAuth2AuthorizedClientService authorizedClientService;
	
    @GetMapping("/")
    public String home(
//    		Model model, OAuth2AuthenticationToken authentication
    		) {
    	
//    	OAuth2AuthorizedClient client = null;
//    	
//    	if (authentication != null) {
//    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
//    		
//            OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
//            String username = oidcUser.getFullName(); // or getPreferredUsername()
//            List<String> roles = oidcUser.getClaimAsStringList("roles"); // 自訂 roles claim
//
//            model.addAttribute("username", username);
//            model.addAttribute("roles", roles);
//
//    	}
//    	
//    	if (client != null) {
//          String accessToken = client.getAccessToken().getTokenValue();
//          Instant expiresAt = client.getAccessToken().getExpiresAt();
//          
//          model.addAttribute("accessToken", accessToken);
//          model.addAttribute("expiresAt", expiresAt);
//    	}
    	
        return "home";
    }
    
    @GetMapping("/profile")
    public String profileFragment(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
    	ObjectMapper mapper = new ObjectMapper();
    	UserInfoDto user = mapper.convertValue(oidcUser.getClaim("user"), UserInfoDto.class);
    	List<String> roles = oidcUser.getClaimAsStringList("roles");
    	if (user != null) {
        	model.addAttribute("email", user.getEmail());
        	model.addAttribute("createAt", user.getCreatedAt());
    	}
    	model.addAttribute("roles", roles.stream().filter(role -> role.startsWith("ROLE_")).map(role -> role.replace("ROLE_", "")).toList());
    	model.addAttribute("authorities", roles.stream().filter(role -> !role.startsWith("ROLE_")).toList());
        return "profile";
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
