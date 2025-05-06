package com.niqdev.app.dto.authority;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAuthorityRequest {
	
	@NotBlank
	private Long id;

    @NotBlank
    private String name;

    private String description;
}
