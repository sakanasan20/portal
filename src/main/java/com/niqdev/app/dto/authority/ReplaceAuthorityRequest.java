package com.niqdev.app.dto.authority;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplaceAuthorityRequest {

    @NotBlank
    private String name;

    private String description;
}
