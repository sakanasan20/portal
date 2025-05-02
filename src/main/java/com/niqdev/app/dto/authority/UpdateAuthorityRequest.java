package com.niqdev.app.dto.authority;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAuthorityRequest {

    @NotBlank
    private String name;

    private String description;
}
