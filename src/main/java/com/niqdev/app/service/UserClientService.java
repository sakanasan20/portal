package com.niqdev.app.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.niqdev.app.converter.UserConverter;
import com.niqdev.app.dto.PageResponse;
import com.niqdev.app.dto.user.CreateUserForm;
import com.niqdev.app.dto.user.CreateUserRequest;
import com.niqdev.app.dto.user.DeleteUserForm;
import com.niqdev.app.dto.user.DeleteUserRequest;
import com.niqdev.app.dto.user.UpdateUserForm;
import com.niqdev.app.dto.user.UpdateUserRequest;
import com.niqdev.app.dto.user.UserDto;
import com.niqdev.app.dto.user.UserSearchCriteria;
import com.niqdev.app.exception.WebClientErrorHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserClientService {

	private final WebClient webClient;
    private final OAuth2AuthorizedClientService authorizedClientService;
	private final UserConverter userConverter;

	public PageResponse<UserDto> searchUsers(OAuth2AuthenticationToken authentication, UserSearchCriteria criteria, Pageable pageable) {
		
    	OAuth2AuthorizedClient client = null;
    	PageResponse<UserDto> result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
//	    	FindLicenseKeyRequest request = licenseKeyConverter.toFindLicenseKeyRequest(form);
	    	ParameterizedTypeReference<PageResponse<UserDto>> typeRef = new ParameterizedTypeReference<>() {};

	    	UriComponentsBuilder uriBuilder = UriComponentsBuilder
	    			.fromUriString("http://localhost:8081/api/admin/users/search");
	    	
	    	if (pageable != null) {
	    		uriBuilder
	    			.queryParam("page", pageable.getPageNumber())
	    			.queryParam("size", pageable.getPageSize());
		    	
		    	for (Sort.Order order : pageable.getSort()) {
		    	    uriBuilder.queryParam("sort", order.getProperty() + "," + order.getDirection());
		    	}
	    	}
	    	
	    	String uri = uriBuilder.toUriString();
	    	
	    	result = webClient.method(HttpMethod.POST)
	                    .uri(uri)
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

	public UserDto createUser(OAuth2AuthenticationToken authentication, CreateUserForm formData) {
    	OAuth2AuthorizedClient client = null;
    	UserDto result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	CreateUserRequest request = userConverter.toCreateUserRequest(formData);

	    	result = webClient.post()
	                    .uri("http://localhost:8081/api/admin/users")
	                    .header("Authorization", "Bearer " + accessToken)
	                    .bodyValue(request)
	                    .retrieve()
	                    .onStatus(
	                            status -> status.is4xxClientError() || status.is5xxServerError(), 
	                            WebClientErrorHandler::handleResponseError
	                        )
	                    .bodyToMono(UserDto.class)
	                    .block();
    	}

        return result;
	}

	public UserDto updateUser(OAuth2AuthenticationToken authentication, UpdateUserForm formData) {
    	OAuth2AuthorizedClient client = null;
    	UserDto result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	UpdateUserRequest request = userConverter.toUpdateUserRequest(formData);

	    	result = webClient.patch()
	                    .uri("http://localhost:8081/api/admin/users/{id}", request.getId())
	                    .header("Authorization", "Bearer " + accessToken)
	                    .bodyValue(request)
	                    .retrieve()
	                    .onStatus(
	                            status -> status.is4xxClientError() || status.is5xxServerError(), 
	                            WebClientErrorHandler::handleResponseError
	                        )
	                    .bodyToMono(UserDto.class)
	                    .block();
    	}

        return result;
	}

	public void deleteUser(OAuth2AuthenticationToken authentication, DeleteUserForm formData) {
    	OAuth2AuthorizedClient client = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	DeleteUserRequest request = userConverter.toDeleteUserRequest(formData);

	    	webClient.delete()
	                    .uri("http://localhost:8081/api/admin/users/{ids}", request.getId())
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
	
	public void deleteUsers(OAuth2AuthenticationToken authentication, DeleteUserForm formData) {
    	OAuth2AuthorizedClient client = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	DeleteUserRequest request = userConverter.toDeleteUserRequest(formData);

	    	webClient.delete()
	                    .uri("http://localhost:8081/api/admin/users/batch/{ids}", request.getIds())
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
