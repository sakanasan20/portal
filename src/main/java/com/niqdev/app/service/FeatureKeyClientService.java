package com.niqdev.app.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.niqdev.app.converter.FeatureKeyConverter;
import com.niqdev.app.dto.featurekey.FeatureKeyDto;
import com.niqdev.app.dto.featurekey.FindFeatureKeyForm;
import com.niqdev.app.dto.featurekey.FindFeatureKeyRequest;
import com.niqdev.app.dto.featurekey.RegisterFeatureKeyForm;
import com.niqdev.app.dto.featurekey.RegisterFeatureKeyRequest;
import com.niqdev.app.exception.WebClientErrorHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureKeyClientService {

	private final WebClient webClient;
    private final OAuth2AuthorizedClientService authorizedClientService;
	private final FeatureKeyConverter featureKeyConverter;

    public FeatureKeyDto registerFeatureKey(OAuth2AuthenticationToken authentication, RegisterFeatureKeyForm form) {
    	
    	OAuth2AuthorizedClient client = null;
    	FeatureKeyDto result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	RegisterFeatureKeyRequest request = featureKeyConverter.toRegisterFeatureKeyRequest(form);

	    	result = webClient.post()
	                    .uri("http://localhost:8083/api/feature-key/register")
	                    .header("Authorization", "Bearer " + accessToken)
	                    .bodyValue(request)
	                    .retrieve()
	                    .onStatus(
	                            status -> status.is4xxClientError() || status.is5xxServerError(), 
	                            WebClientErrorHandler::handleResponseError
	                        )
	                    .bodyToMono(FeatureKeyDto.class)
	                    .block();
    	}

        return result;
    }

	public FeatureKeyDto findFeatureKey(OAuth2AuthenticationToken authentication, FindFeatureKeyForm form) {
		
    	OAuth2AuthorizedClient client = null;
    	FeatureKeyDto result = null;
    	
    	if (authentication != null) {
    		client = authorizedClientService.loadAuthorizedClient("my-client", authentication.getName());
    	}
    	
    	if (client != null) {
	    	String accessToken = client.getAccessToken().getTokenValue();
	    	FindFeatureKeyRequest request = featureKeyConverter.toFindFeatureKeyRequest(form);

	    	result = webClient.get()
	                    .uri("http://localhost:8083/api/feature-key/" + request.getKid())
	                    .header("Authorization", "Bearer " + accessToken)
	                    .retrieve()
	                    .onStatus(
	                            status -> status.is4xxClientError() || status.is5xxServerError(), 
	                            WebClientErrorHandler::handleResponseError
	                        )
	                    .bodyToMono(FeatureKeyDto.class)
	                    .block();
    	}

        return result;
	}
	
}
