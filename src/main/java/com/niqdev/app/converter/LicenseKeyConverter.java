package com.niqdev.app.converter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.niqdev.app.dto.licensekey.FindLicenseKeyForm;
import com.niqdev.app.dto.licensekey.FindLicenseKeyRequest;
import com.niqdev.app.dto.licensekey.RegisterLicenseKeyForm;
import com.niqdev.app.dto.licensekey.RegisterLicenseKeyRequest;

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
