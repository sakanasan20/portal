package com.niqdev.app.controller;

import java.util.List;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.niqdev.app.dto.PageResponse;
import com.niqdev.app.dto.authority.AuthorityDto;
import com.niqdev.app.dto.authority.AuthoritySearchCriteria;
import com.niqdev.app.dto.role.RoleDto;
import com.niqdev.app.dto.role.RoleSearchCriteria;
import com.niqdev.app.dto.user.UserDto;
import com.niqdev.app.dto.user.UserSearchCriteria;
import com.niqdev.app.service.AuthorityClientService;
import com.niqdev.app.service.RoleClientService;
import com.niqdev.app.service.UserClientService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final UserClientService userClientService;
	private final RoleClientService roleClientService;
	private final AuthorityClientService authorityClientService;

    @GetMapping("/admin-page")
    public String adminFragment(Model model, OAuth2AuthenticationToken authentication) {
    	PageResponse<UserDto> usersPage = userClientService.searchUsers(authentication, new UserSearchCriteria(), null);
    	PageResponse<RoleDto> rolesPage = roleClientService.searchRoles(authentication, new RoleSearchCriteria(), null);
    	PageResponse<AuthorityDto> authoritiesPage = authorityClientService.searchAuthorities(authentication, new AuthoritySearchCriteria(), null);
    	List<UserDto> users = usersPage.getContent();
    	List<RoleDto> roles = rolesPage.getContent();
    	List<AuthorityDto> authorities = authoritiesPage.getContent();
    	model.addAttribute("users", users);
    	model.addAttribute("roles", roles);
    	model.addAttribute("authorities", authorities);
        return "admin";
    }
	
}
