package com.niqdev.app.controller;

import java.util.List;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.niqdev.app.dto.PageResponse;
import com.niqdev.app.dto.UserDto;
import com.niqdev.app.dto.UserSearchCriteria;
import com.niqdev.app.service.UserClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final UserClientService userClientService;

    @GetMapping("/admin-page")
    public String adminFragment(Model model, OAuth2AuthenticationToken authentication) {
    	PageResponse<UserDto> usersPage = userClientService.searchUsers(authentication, new UserSearchCriteria(), null);
    	List<UserDto> users = usersPage.getContent();
    	log.info("users.size(): " + users.size());
    	model.addAttribute("users", users);
        return "admin";
    }
	
}
