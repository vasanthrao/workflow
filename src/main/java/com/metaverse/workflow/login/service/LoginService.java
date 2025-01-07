package com.metaverse.workflow.login.service;

import java.util.List;

public interface LoginService {

    LoginUserResponse getUserById(Long id);

    LoginUserResponse createUser(LoginUserRequest request);

    List<LoginUserResponse> getUserByMobileNo(Long mobileNo);

}
