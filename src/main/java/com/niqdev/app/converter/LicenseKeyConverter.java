package com.niqdev.app.converter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.niqdev.app.dto.FindLicenseKeyForm;
import com.niqdev.app.dto.FindLicenseKeyRequest;
import com.niqdev.app.dto.RegisterLicenseKeyForm;
import com.niqdev.app.dto.RegisterLicenseKeyRequest;

@Component
public class LicenseKeyConverter {

	public RegisterLicenseKeyRequest toRegisterLicenseKeyRequest(RegisterLicenseKeyForm form) {
		return RegisterLicenseKeyRequest.builder()
				.licenseName(form.getLicenseName())
				.expirationTime(LocalDateTime.parse(form.getExpirationTime()))
				.maxDevices(Integer.parseInt(form.getMaxDevices()))
				.build();
	}

	public FindLicenseKeyRequest toFindLicenseKeyRequest(FindLicenseKeyForm form) {
		return FindLicenseKeyRequest.builder()
				.licenseKey(form.getLicenseKey())
				.build();
	}

}
