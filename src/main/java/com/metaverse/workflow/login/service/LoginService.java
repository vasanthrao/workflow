package com.metaverse.workflow.login.service;

import com.metaverse.workflow.common.response.WorkflowResponse;

import java.util.List;

public interface LoginService {

    LoginUserResponse getUserById(String id);

    WorkflowResponse createUser(LoginUserRequest request);

    List<LoginUserResponse> getUserByMobileNo(Long mobileNo);

}
