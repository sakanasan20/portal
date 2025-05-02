package com.niqdev.app.converter;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.niqdev.app.dto.featurekey.FindFeatureKeyForm;
import com.niqdev.app.dto.featurekey.FindFeatureKeyRequest;
import com.niqdev.app.dto.featurekey.RegisterFeatureKeyForm;
import com.niqdev.app.dto.featurekey.RegisterFeatureKeyRequest;

@Component
public class FeatureKeyConverter {

	public RegisterFeatureKeyRequest toRegisterFeatureKeyRequest(RegisterFeatureKeyForm form) {
		Set<String> modulesSet = Set.of(form.getModules().split(","));
		return RegisterFeatureKeyRequest.builder()
				.kid(form.getKid())
				.modules(modulesSet)
				.expirationTime(LocalDateTime.parse(form.getExpirationTime()))
				.build();
	}

	public FindFeatureKeyRequest toFindFeatureKeyRequest(FindFeatureKeyForm form) {
		return FindFeatureKeyRequest.builder()
				.kid(form.getKid())
				.build();
	}

}
