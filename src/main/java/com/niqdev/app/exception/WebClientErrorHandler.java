package com.niqdev.app.exception;

import org.springframework.web.reactive.function.client.ClientResponse;

import reactor.core.publisher.Mono;

public class WebClientErrorHandler {

	public static <T> Mono<T> handleResponseError(ClientResponse response) {
		return response.bodyToMono(ApiErrorResponse.class)
				.flatMap(apiError -> Mono.error(new ApiErrorException(apiError)));
	}
}
