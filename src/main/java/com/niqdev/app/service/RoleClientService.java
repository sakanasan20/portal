package com.niqdev.app.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.niqdev.app.converter.RoleConverter;
import com.niqdev.app.dto.PageResponse;
import com.niqdev.app.dto.role.CreateRoleForm;
import com.niqdev.app.dto.role.CreateRoleRequest;
import com.niqdev.app.dto.role.DeleteRoleForm;
import com.niqdev.app.dto.role.DeleteRoleRequest;
import com.niqdev.app.dto.role.RoleDto;
import com.niqdev.app.dto.role.RoleSearchCriteria;
import com.niqdev.app.dto.role.UpdateRoleForm;
import com.niqdev.app.dto.role.UpdateRoleRequest;
import com.niqdev.app.dto.user.UserDto;
import com.niqdev.app.exception.WebClientErrorHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleClientService {

	private final WebClient webClient;
    private final OAuth2AuthorizedClientService authorizedClientService;
	private final RoleConverter roleConverter;

	public PageResponse<RoleDto> searchRoles(OAuth2AuthenticationToken authentication, RoleSearchCriteria criteria, Pageable pageable) {
		
    	OAuth2AuthorizedClient client = null;
    	PageResponse<RoleDto> result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
//	    	FindLicenseKeyRequest request = licenseKeyConverter.toFindLicenseKeyRequest(form);
	    	ParameterizedTypeReference<PageResponse<RoleDto>> typeRef = new ParameterizedTypeReference<>() {};

	    	result = webClient.method(HttpMethod.GET)
	                    .uri("http://localhost:8081/api/admin/roles/search")
	                    .header("Authorization", "Bearer " + accessToken)
	                    .bodyValue(criteria)
	                    .retrieve()
	                    .onStatus(
	                            status -> status.is4xxClientError() || status.is5xxServerError(), 
	                            WebClientErrorHandler::handleResponseError
	                        )
	                    .bodyToMono(typeRef)
	                    .block();
    	}

        return result;
	}

	public RoleDto createRole(OAuth2AuthenticationToken authentication, CreateRoleForm formData) {
    	OAuth2AuthorizedClient client = null;
    	RoleDto result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	CreateRoleRequest request = roleConverter.toCreateRoleRequest(formData);

	    	result = webClient.post()
	                    .uri("http://localhost:8081/api/admin/roles")
	                    .header("Authorization", "Bearer " + accessToken)
	                    .bodyValue(request)
	                    .retrieve()
	                    .onStatus(
	                            status -> status.is4xxClientError() || status.is5xxServerError(), 
	                            WebClientErrorHandler::handleResponseError
	                        )
	                    .bodyToMono(RoleDto.class)
	                    .block();
    	}

        return result;
	}

	public RoleDto updateRole(OAuth2AuthenticationToken authentication, UpdateRoleForm formData) {
    	OAuth2AuthorizedClient client = null;
    	RoleDto result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	UpdateRoleRequest request = roleConverter.toUpdateRoleRequest(formData);

	    	result = webClient.patch()
	                    .uri("http://localhost:8081/api/admin/roles/{id}", request.getId())
	                    .header("Authorization", "Bearer " + accessToken)
	                    .bodyValue(request)
	                    .retrieve()
	                    .onStatus(
	                            status -> status.is4xxClientError() || status.is5xxServerError(), 
	                            WebClientErrorHandler::handleResponseError
	                        )
	                    .bodyToMono(RoleDto.class)
	                    .block();
    	}

        return result;
	}

	public void deleteRole(OAuth2AuthenticationToken authentication, DeleteRoleForm formData) {
		OAuth2AuthorizedClient client = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	DeleteRoleRequest request = roleConverter.toDeleteRoleRequest(formData);

	    	webClient.delete()
	                    .uri("http://localhost:8081/api/admin/roles/{id}", request.getId())
	                    .header("Authorization", "Bearer " + accessToken)
	                    .retrieve()
	                    .onStatus(
	                            status -> status.is4xxClientError() || status.is5xxServerError(), 
	                            WebClientErrorHandler::handleResponseError
	                        )
	                    .bodyToMono(UserDto.class)
	                    .block();
    	}
	}
}
