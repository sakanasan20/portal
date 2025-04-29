package com.niqdev.app.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.niqdev.app.converter.LicenseKeyConverter;
import com.niqdev.app.dto.FindLicenseKeyForm;
import com.niqdev.app.dto.FindLicenseKeyRequest;
import com.niqdev.app.dto.LicenseKeyDto;
import com.niqdev.app.dto.RegisterLicenseKeyForm;
import com.niqdev.app.dto.RegisterLicenseKeyRequest;
import com.niqdev.app.exception.WebClientErrorHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LicenseKeyClientService {

	private final WebClient webClient;
    private final OAuth2AuthorizedClientService authorizedClientService;
	private final LicenseKeyConverter licenseKeyConverter;

    public LicenseKeyDto registerLicenseKey(OAuth2AuthenticationToken authentication, RegisterLicenseKeyForm form) {
    	
    	OAuth2AuthorizedClient client = null;
    	LicenseKeyDto result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	RegisterLicenseKeyRequest request = licenseKeyConverter.toRegisterLicenseKeyRequest(form);

	    	result = webClient.post()
	                    .uri("http://localhost:8083/api/license-key/register")
	                    .header("Authorization", "Bearer " + accessToken)
	                    .bodyValue(request)
	                    .retrieve()
	                    .onStatus(
	                            status -> status.is4xxClientError() || status.is5xxServerError(), 
	                            WebClientErrorHandler::handleResponseError
	                        )
	                    .bodyToMono(LicenseKeyDto.class)
	                    .block();
    	}

        return result;
    }

	public LicenseKeyDto findLicenseKey(OAuth2AuthenticationToken authentication, FindLicenseKeyForm form) {
		
    	OAuth2AuthorizedClient client = null;
    	LicenseKeyDto result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	FindLicenseKeyRequest request = licenseKeyConverter.toFindLicenseKeyRequest(form);

	    	result = webClient.get()
	                    .uri("http://localhost:8083/api/license-key/" + request.getLicenseKey())
	                    .header("Authorization", "Bearer " + accessToken)
	                    .retrieve()
	                    .onStatus(
	                            status -> status.is4xxClientError() || status.is5xxServerError(), 
	                            WebClientErrorHandler::handleResponseError
	                        )
	                    .bodyToMono(LicenseKeyDto.class)
	                    .block();
    	}

        return result;
	}
	
}
