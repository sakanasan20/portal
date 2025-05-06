package com.niqdev.app.dto.role;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRoleForm {
	
	@NotBlank
	private String id;

    @NotBlank
    private String name;

    private String description;
    
    private Set<String> authorityIds;
}
