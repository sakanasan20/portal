package com.niqdev.app.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niqdev.app.dto.PageResponse;
import com.niqdev.app.dto.user.UserDto;
import com.niqdev.app.dto.user.UserSearchCriteria;
import com.niqdev.app.service.UserClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminRestController {
	
	private final UserClientService userClientService;
    
    @GetMapping("/admin/users/search")
    public PageResponse<UserDto> iamPage(Model model, OAuth2AuthenticationToken authentication, 
    		@RequestParam(value = "offset", defaultValue = "0") Integer offset, 
			@RequestParam(value = "limit", defaultValue = "10") Integer limit, 
			@RequestParam(value = "sort", defaultValue = "id") String sort, 
			@RequestParam(value = "order", defaultValue = "asc") String order) {
    	
    	Integer page = offset / limit;
    	Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
		PageRequest pageRequest = PageRequest.of(page, limit, Sort.by(direction, sort));
    	PageResponse<UserDto> usersPage = userClientService.searchUsers(authentication, new UserSearchCriteria(), pageRequest);
    	log.info("usersPage: " + usersPage);
        return usersPage;
    }
}
