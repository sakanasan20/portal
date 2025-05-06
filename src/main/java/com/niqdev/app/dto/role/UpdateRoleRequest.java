package com.niqdev.app.dto.role;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRoleRequest {
	private Long id;
    private String name;
    private String description;
    private Set<Long> authorityIds;
}
