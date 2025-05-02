package com.niqdev.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAuthorityRequest {

    @NotBlank
    private String name;

    private String description;
}
