package com.niqdev.app.dto.user;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserForm {
	
	@NotBlank
	private String id;
    
    @NotBlank
    private String username;
    
    @NotBlank
    @Email
    private String email;
    
    private Set<String> roleIds;
}
