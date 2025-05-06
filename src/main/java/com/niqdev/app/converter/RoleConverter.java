package com.niqdev.app.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.niqdev.app.dto.role.CreateRoleForm;
import com.niqdev.app.dto.role.CreateRoleRequest;
import com.niqdev.app.dto.role.DeleteRoleForm;
import com.niqdev.app.dto.role.DeleteRoleRequest;
import com.niqdev.app.dto.role.UpdateRoleForm;
import com.niqdev.app.dto.role.UpdateRoleRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleConverter {

    public CreateRoleRequest toCreateRoleRequest(CreateRoleForm formData) {
        return CreateRoleRequest.builder()
                .name(formData.getName())
                .description(formData.getDescription())
                .authorityIds(formData.getAuthorityIds() != null
		        		? formData.getAuthorityIds().stream()
		        				.map(Long::parseLong)
		        				.collect(Collectors.toSet())
		        		: null)
                .build();
    }

    public UpdateRoleRequest toUpdateRoleRequest(UpdateRoleForm formData) {
        return UpdateRoleRequest.builder()
        		.id(Long.parseLong(formData.getId()))
                .name(formData.getName())
                .description(formData.getDescription())
                .authorityIds(formData.getAuthorityIds() != null
		        		? formData.getAuthorityIds().stream()
		        				.map(Long::parseLong)
		        				.collect(Collectors.toSet())
		        		: null)
                .build();
    }
    
	public DeleteRoleRequest toDeleteRoleRequest(DeleteRoleForm formData) {
    	return DeleteRoleRequest.builder()
    			.id(Long.parseLong(formData.getId()))
                .name(formData.getName())
    			.build();
	}
}
