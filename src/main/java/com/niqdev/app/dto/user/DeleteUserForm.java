package com.niqdev.app.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserForm {
    
	@NotBlank
	private String id;
	
	@NotBlank
    private String username;
}
