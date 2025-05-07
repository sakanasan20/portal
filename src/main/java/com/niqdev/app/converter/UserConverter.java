package com.niqdev.app.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.niqdev.app.dto.user.CreateUserForm;
import com.niqdev.app.dto.user.CreateUserRequest;
import com.niqdev.app.dto.user.DeleteUserForm;
import com.niqdev.app.dto.user.DeleteUserRequest;
import com.niqdev.app.dto.user.UpdateUserForm;
import com.niqdev.app.dto.user.UpdateUserRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserConverter {

    public CreateUserRequest toCreateUserRequest(CreateUserForm formData) {
        return CreateUserRequest.builder()
                .username(formData.getUsername())
                .password(formData.getPassword())
                .email(formData.getEmail())
                .roleIds(formData.getRoleIds() != null
                		? formData.getRoleIds().stream()
                				.map(Long::parseLong)
                				.collect(Collectors.toSet())
                		: null)
                .build();
    }
    
    public UpdateUserRequest toUpdateUserRequest(UpdateUserForm formData) {
    	return UpdateUserRequest.builder()
    			.id(Long.parseLong(formData.getId()))
                .username(formData.getUsername())
                .email(formData.getEmail())
                .roleIds(formData.getRoleIds() != null
                		? formData.getRoleIds().stream()
                				.map(Long::parseLong)
                				.collect(Collectors.toSet())
                		: null)
    			.build();
    }

	public DeleteUserRequest toDeleteUserRequest(DeleteUserForm formData) {
    	return DeleteUserRequest.builder()
    			.id(formData.getId() != null 
    					? Long.parseLong(formData.getId())
    					: null)
    			.ids(formData.getIds())
                .username(formData.getUsername())
    			.build();
	}
}
