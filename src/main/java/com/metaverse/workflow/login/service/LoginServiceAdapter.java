package com.metaverse.workflow.login.service;

import com.metaverse.workflow.login.repository.LoginRepository;
import com.metaverse.workflow.model.User;
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

    private String defaultPassword = "Password@123";

    @Override
    public LoginUserResponse getUserById(Long id) {
        log.info("login service, userId : {}", id);
        Optional<User> user = loginRepository.findById(id);
        return user.isPresent() ? LoginUserResponseMapper.map(user.get()) : LoginUserResponse.builder().build();
    }

    @Override
    public LoginUserResponse createUser(LoginUserRequest request) {
        User user = loginRepository.save(User.builder().userId(request.getMobileNo()).email(request.getEmail()).firstName(request.getFirstName()).lastName(request.getLastName()).mobileNo(request.getMobileNo()).userRole(request.getUserRole().name()).gender(request.getGender()).createdOn(new Date()).password(defaultPassword).build());
        return LoginUserResponseMapper.map(user);
    }

    @Override
    public List<LoginUserResponse> getUserByMobileNo(Long mobileNo) {
        return null;
    }

}
