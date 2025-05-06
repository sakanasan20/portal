package com.niqdev.app.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteRoleRequest {

	@NotBlank
	private Long id;
    
    @NotBlank
    private String name;
}
