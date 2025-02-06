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
                .mobileNo(user.getMobileNo())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .userRole(UserRole.valueOf(user.getUserRole()))
                .agencyId(user.getAgency() != null ? user.getAgency().getAgencyId() : null)
                .build();
    }

    public List<LoginUserResponse> map(List<User> user) {
        return user.stream().map(LoginUserResponseMapper::map).collect(Collectors.toList());
    }

    public static LoginUserResponse mapUser(User user) {
        return LoginUserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .mobileNo(user.getMobileNo())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userRole(UserRole.valueOf(user.getUserRole()))
                .agencyName(user.getAgency() != null ? user.getAgency().getAgencyName() : null)
                .build();
    }

}
