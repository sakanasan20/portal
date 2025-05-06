package com.niqdev.app.controller;

import java.util.List;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.niqdev.app.dto.PageResponse;
import com.niqdev.app.dto.authority.AuthorityDto;
import com.niqdev.app.dto.authority.AuthoritySearchCriteria;
import com.niqdev.app.dto.authority.CreateAuthorityForm;
import com.niqdev.app.dto.authority.DeleteAuthorityForm;
import com.niqdev.app.dto.authority.UpdateAuthorityForm;
import com.niqdev.app.dto.role.CreateRoleForm;
import com.niqdev.app.dto.role.DeleteRoleForm;
import com.niqdev.app.dto.role.RoleDto;
import com.niqdev.app.dto.role.RoleSearchCriteria;
import com.niqdev.app.dto.role.UpdateRoleForm;
import com.niqdev.app.dto.user.CreateUserForm;
import com.niqdev.app.dto.user.DeleteUserForm;
import com.niqdev.app.dto.user.UpdateUserForm;
import com.niqdev.app.dto.user.UserDto;
import com.niqdev.app.dto.user.UserSearchCriteria;
import com.niqdev.app.service.AuthorityClientService;
import com.niqdev.app.service.RoleClientService;
import com.niqdev.app.service.UserClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final UserClientService userClientService;
	private final RoleClientService roleClientService;
	private final AuthorityClientService authorityClientService;

    @GetMapping("/admin-page")
    public String adminPage(Model model, OAuth2AuthenticationToken authentication) {
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
    
    @PostMapping("/admin/users/create")
    public String createUser(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute CreateUserForm formData) {
        UserDto user = userClientService.createUser(authentication, formData);
        log.info("Created User: " + user.getUsername());
        return "redirect:/";
    }
    
    @PostMapping("/admin/users/update")
    public String updateUser(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute UpdateUserForm formData) {
        UserDto user = userClientService.updateUser(authentication, formData);
        log.info("Updated User: " + user.getUsername());
        return "redirect:/";
    }
    
    @PostMapping("/admin/users/delete")
    public String deleteUser(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute DeleteUserForm formData) {
        userClientService.deleteUser(authentication, formData);
        log.info("Deleted User: " + formData.getId());
        return "redirect:/";
    }
    
    @PostMapping("/admin/roles/create")
    public String createRole(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute CreateRoleForm formData) {
        RoleDto role = roleClientService.createRole(authentication, formData);
        log.info("Created Role: " + role.getName());
        return "redirect:/";
    }
    
    @PostMapping("/admin/roles/update")
    public String updateRole(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute UpdateRoleForm formData) {
    	RoleDto role = roleClientService.updateRole(authentication, formData);
        log.info("Updated Role: " + role.getName());
        return "redirect:/";
    }
    
    @PostMapping("/admin/roles/delete")
    public String deleteRole(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute DeleteRoleForm formData) {
    	roleClientService.deleteRole(authentication, formData);
        log.info("Deleted Role: " + formData.getId());
        return "redirect:/";
    }
    
    @PostMapping("/admin/authorities/create")
    public String createAuthority(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute CreateAuthorityForm formData) {
        AuthorityDto authority = authorityClientService.createAuthority(authentication, formData);
        log.info("Created Authority: " + authority.getName());
        return "redirect:/";
    }
    
    @PostMapping("/admin/authorities/update")
    public String updateAuthority(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute UpdateAuthorityForm formData) {
    	AuthorityDto authority = authorityClientService.updateAuthority(authentication, formData);
        log.info("Updated Authority: " + authority.getName());
        return "redirect:/";
    }
    
    @PostMapping("/admin/authorities/delete")
    public String deleteAuthority(Model model, OAuth2AuthenticationToken authentication, @ModelAttribute DeleteAuthorityForm formData) {
    	authorityClientService.deleteAuthority(authentication, formData);
        log.info("Deleted Authority: " + formData.getId());
        return "redirect:/";
    }
}
