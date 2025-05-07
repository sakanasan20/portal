package com.niqdev.app.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserForm {

	private String id;
	
	private String ids;
	
    private String username;
}
