package com.niqdev.app.dto.licensekey;

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
public class FindLicenseKeyForm {
	
	private String licenseKey;
	
    private String licenseName;
    
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private String expirationTime;
	
    private String maxDevices;
}
