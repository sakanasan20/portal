package com.niqdev.app.dto;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RegisterLicenseKeyForm {
	
    private String licenseName;
    
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private String expirationTime;
	
    private String maxDevices;
}
