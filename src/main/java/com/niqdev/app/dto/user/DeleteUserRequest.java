package com.niqdev.app.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteUserRequest {

	@NotBlank
	private Long id;
    
    @NotBlank
    private String username;
}
