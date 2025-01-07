package com.metaverse.workflow.login.service;

import com.metaverse.workflow.common.enums.UserRole;
import com.metaverse.workflow.login.repository.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginUserResponseMapper {

    public static LoginUserResponse map(UserEntity userEntity) {
        return LoginUserResponse.builder()
                .userId(userEntity.getUserId())
                .email(userEntity.getEmail())
                .address(userEntity.getAddress())
                .mobileNo(userEntity.getMobileNo())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .gender(userEntity.getGender())
                .status(userEntity.getStatus())
                .address(userEntity.getAddress())
                .attempts(userEntity.getAttempts())
                .userRole(UserRole.valueOf(userEntity.getUserRole()))
                .createdOn(userEntity.getCreatedOn())
                .updatedOn(userEntity.getUpdatedOn())
                .build();
    }

    public List<LoginUserResponse> map(List<UserEntity> userEntity) {
        return userEntity.stream().map(LoginUserResponseMapper::map).collect(Collectors.toList());
    }

}
