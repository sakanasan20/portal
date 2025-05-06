package com.niqdev.app.converter;

import org.springframework.stereotype.Component;

import com.niqdev.app.dto.authority.CreateAuthorityForm;
import com.niqdev.app.dto.authority.CreateAuthorityRequest;
import com.niqdev.app.dto.authority.DeleteAuthorityForm;
import com.niqdev.app.dto.authority.DeleteAuthorityRequest;
import com.niqdev.app.dto.authority.UpdateAuthorityForm;
import com.niqdev.app.dto.authority.UpdateAuthorityRequest;

@Component
public class AuthorityConverter {

    public CreateAuthorityRequest toCreateAuthorityRequest(CreateAuthorityForm formData) {
        return CreateAuthorityRequest.builder()
                .name(formData.getName())
                .description(formData.getDescription())
                .build();
    }

	public UpdateAuthorityRequest toUpdateAuthorityRequest(UpdateAuthorityForm formData) {
		return UpdateAuthorityRequest.builder()
				.id(Long.parseLong(formData.getId()))
                .name(formData.getName())
                .description(formData.getDescription())
                .build();
	}

	public DeleteAuthorityRequest toDeleteAuthorityRequest(DeleteAuthorityForm formData) {
		return DeleteAuthorityRequest.builder()
				.id(Long.parseLong(formData.getId()))
                .name(formData.getName())
                .build();
	}
}
