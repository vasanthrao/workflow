package com.metaverse.workflow.login.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.UserDetailsException;

import java.util.List;

public interface LoginService {

    LoginUserResponse getUserById(String id);

    WorkflowResponse createUser(LoginUserRequest request);

    List<LoginUserResponse> getUserByMobileNo(Long mobileNo);

    WorkflowResponse getUsers();
    WorkflowResponse changePassword(String userId,String oldPassword,String newPassword) throws UserDetailsException;
}
