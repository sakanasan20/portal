package com.niqdev.app.dto.user;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserForm {
    
    @NotBlank
    private String username;
    
    @NotBlank
    @Email
    private String email;

    @NotNull
    private String password;
    
    private Set<String> roleIds;
}
