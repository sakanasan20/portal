package com.niqdev.app.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.niqdev.app.converter.AuthorityConverter;
import com.niqdev.app.dto.PageResponse;
import com.niqdev.app.dto.authority.AuthorityDto;
import com.niqdev.app.dto.authority.AuthoritySearchCriteria;
import com.niqdev.app.dto.authority.CreateAuthorityForm;
import com.niqdev.app.dto.authority.CreateAuthorityRequest;
import com.niqdev.app.dto.authority.DeleteAuthorityForm;
import com.niqdev.app.dto.authority.DeleteAuthorityRequest;
import com.niqdev.app.dto.authority.UpdateAuthorityForm;
import com.niqdev.app.dto.authority.UpdateAuthorityRequest;
import com.niqdev.app.dto.user.UserDto;
import com.niqdev.app.exception.WebClientErrorHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorityClientService {

	private final WebClient webClient;
    private final OAuth2AuthorizedClientService authorizedClientService;
	private final AuthorityConverter authorityConverter;

	public PageResponse<AuthorityDto> searchAuthorities(OAuth2AuthenticationToken authentication, AuthoritySearchCriteria criteria, Pageable pageable) {
		
    	OAuth2AuthorizedClient client = null;
    	PageResponse<AuthorityDto> result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
//	    	FindLicenseKeyRequest request = licenseKeyConverter.toFindLicenseKeyRequest(form);
	    	ParameterizedTypeReference<PageResponse<AuthorityDto>> typeRef = new ParameterizedTypeReference<>() {};

	    	result = webClient.method(HttpMethod.GET)
	                    .uri("http://localhost:8081/api/admin/authorities/search")
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

	public AuthorityDto createAuthority(OAuth2AuthenticationToken authentication, CreateAuthorityForm formData) {
    	OAuth2AuthorizedClient client = null;
    	AuthorityDto result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	CreateAuthorityRequest request = authorityConverter.toCreateAuthorityRequest(formData);

	    	result = webClient.post()
	                    .uri("http://localhost:8081/api/admin/authorities")
	                    .header("Authorization", "Bearer " + accessToken)
	                    .bodyValue(request)
	                    .retrieve()
	                    .onStatus(
	                            status -> status.is4xxClientError() || status.is5xxServerError(), 
	                            WebClientErrorHandler::handleResponseError
	                        )
	                    .bodyToMono(AuthorityDto.class)
	                    .block();
    	}

        return result;
	}

	public AuthorityDto updateAuthority(OAuth2AuthenticationToken authentication, UpdateAuthorityForm formData) {
    	OAuth2AuthorizedClient client = null;
    	AuthorityDto result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	UpdateAuthorityRequest request = authorityConverter.toUpdateAuthorityRequest(formData);

	    	result = webClient.patch()
	                    .uri("http://localhost:8081/api/admin/authorities/{id}", request.getId())
	                    .header("Authorization", "Bearer " + accessToken)
	                    .bodyValue(request)
	                    .retrieve()
	                    .onStatus(
	                            status -> status.is4xxClientError() || status.is5xxServerError(), 
	                            WebClientErrorHandler::handleResponseError
	                        )
	                    .bodyToMono(AuthorityDto.class)
	                    .block();
    	}

        return result;
	}

	public void deleteAuthority(OAuth2AuthenticationToken authentication, DeleteAuthorityForm formData) {
		OAuth2AuthorizedClient client = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	DeleteAuthorityRequest request = authorityConverter.toDeleteAuthorityRequest(formData);

	    	webClient.delete()
	                    .uri("http://localhost:8081/api/admin/authorities/{id}", request.getId())
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
