package com.niqdev.app.dto.featurekey;

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
public class RegisterFeatureKeyForm {
	
	private String kid;
	
	private String modules;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private String expirationTime;
}
