package com.niqdev.app.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteRoleForm {
	
	@NotBlank
	private String id;
	
	@NotBlank
    private String name;
}
