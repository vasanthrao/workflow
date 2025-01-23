package com.metaverse.workflow.login.service;

import com.metaverse.workflow.common.enums.UserRole;
import com.metaverse.workflow.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginUserResponseMapper {

    public static LoginUserResponse map(User user) {
        return LoginUserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .address(user.getAddress())
                .mobileNo(user.getMobileNo())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .status(user.getStatus())
                .address(user.getAddress())
                .attempts(user.getAttempts())
                .userRole(UserRole.valueOf(user.getUserRole()))
                .createdOn(user.getCreatedOn())
                .updatedOn(user.getUpdatedOn())
                .password(user.getPassword())
                .build();
    }

    public List<LoginUserResponse> map(List<User> user) {
        return user.stream().map(LoginUserResponseMapper::map).collect(Collectors.toList());
    }

}
