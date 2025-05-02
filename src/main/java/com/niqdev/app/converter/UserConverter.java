package com.niqdev.app.converter;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserConverter {

//    private final RoleRepository roleRepository; // 用來查詢角色
//    private final RoleConverter roleConverter;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserDto toDto(User user) {
//        if (user == null) return null;
//        return new UserDto(
//            user.getId(),
//            user.getUsername(),
//            user.getEmail(),
//            user.isEnabled(),
//            user.isAccountNonLocked(),
//            user.isAccountNonExpired(),
//            user.isCredentialsNonExpired(),
//            user.getRoles().stream()
//                .map(roleConverter::toDto)
//                .collect(Collectors.toSet()),
//            user.getCreatedAt(),
//            user.getCreatedBy(),
//            user.getUpdatedAt(),
//            user.getUpdatedBy()
//        );
//    }
//
//    public User toEntity(UserDto dto) {
//        if (dto == null) return null;
//
//        return User.builder()
//                .id(dto.getId())
//                .username(dto.getUsername())
//                .email(dto.getEmail())
//                .enabled(dto.isEnabled())
//                .accountNonLocked(dto.isAccountNonLocked())
//                .accountNonExpired(dto.isAccountNonExpired())
//                .credentialsNonExpired(dto.isCredentialsNonExpired())
//                .roles(dto.getRoles() != null
//                        ? dto.getRoles().stream()
//                            .map(roleConverter::toEntity)
//                            .collect(Collectors.toSet())
//                        : null)
//                .createdAt(dto.getCreatedAt())
//                .createdBy(dto.getCreatedBy())
//                .updatedAt(dto.getUpdatedAt())
//                .updatedBy(dto.getUpdatedBy())
//                .build();
//    }
//    
//    public User fromCreateRequest(CreateUserRequest request) {
//        return User.builder()
//                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .email(request.getEmail())
//                .enabled(true)
//                .accountNonLocked(true)
//                .accountNonExpired(true)
//                .credentialsNonExpired(true)
//                .roles(findRolesByIds(request.getRoleIds()))
//                .build();
//    }
//    
//
//    public User fromUpdateRequest(User user, UpdateUserRequest request) {
//        if (request.getUsername() != null) user.setUsername(request.getUsername());
//        if (request.getEmail() != null) user.setEmail(request.getEmail());
//        if (request.getEnabled() != null) user.setEnabled(request.getEnabled());
//        if (request.getAccountNonLocked() != null) user.setAccountNonLocked(request.getAccountNonLocked());
//        if (request.getAccountNonExpired() != null) user.setAccountNonExpired(request.getAccountNonExpired());
//        if (request.getCredentialsNonExpired() != null) user.setCredentialsNonExpired(request.getCredentialsNonExpired());
//        if (request.getRoleIds() != null) user.setRoles(findRolesByIds(request.getRoleIds()));
//        return user;
//    }
//    
//    public User fromReplaceRequest(ReplaceUserRequest request, User existingUser) {
//        existingUser.setUsername(request.getUsername());
//        existingUser.setEmail(request.getEmail());
//        existingUser.setEnabled(Boolean.TRUE.equals(request.getEnabled()));
//        existingUser.setAccountNonLocked(Boolean.TRUE.equals(request.getAccountNonLocked()));
//        existingUser.setAccountNonExpired(Boolean.TRUE.equals(request.getAccountNonExpired()));
//        existingUser.setCredentialsNonExpired(Boolean.TRUE.equals(request.getCredentialsNonExpired()));
//        existingUser.setRoles(findRolesByIds(request.getRoleIds()));
//        return existingUser;
//    }
//    
//    private Set<Role> findRolesByIds(Set<Long> roleIds) {
//        if (roleIds == null || roleIds.isEmpty()) return Collections.emptySet();
//        return new HashSet<>(roleRepository.findAllById(roleIds));
//    }
}
