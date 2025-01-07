package com.metaverse.workflow.login.service;

import com.metaverse.workflow.common.enums.UserRole;
import com.metaverse.workflow.login.repository.LoginRepository;
import com.metaverse.workflow.login.repository.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LoginServiceAdapter implements LoginService{

    @Autowired
    LoginRepository loginRepository;

    @Override
    public LoginUserResponse getUserById(Long id) {
        log.info("login service, userId : {}", id);
        Optional<UserEntity> user = loginRepository.findById(id);
        return user.isPresent() ? LoginUserResponseMapper.map(user.get()) : LoginUserResponse.builder().build();
    }

    @Override
    public LoginUserResponse createUser(LoginUserRequest request) {
        UserEntity user = loginRepository.save(UserEntity.builder().userId(request.getMobileNo()).email(request.getEmail()).firstName(request.getFirstName()).lastName(request.getLastName()).mobileNo(request.getMobileNo()).userRole(request.getUserRole().name()).gender(request.getGender()).createdOn(new Date()).build());
        return LoginUserResponseMapper.map(user);
    }

    @Override
    public List<LoginUserResponse> getUserByMobileNo(Long mobileNo) {
        return null;
    }

}
