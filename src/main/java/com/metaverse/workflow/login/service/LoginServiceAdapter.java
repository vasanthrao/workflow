package com.metaverse.workflow.login.service;

import com.metaverse.workflow.agency.repository.AgencyRepository;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.SubActivityDetailsException;
import com.metaverse.workflow.exceptions.UserDetailsException;
import com.metaverse.workflow.login.repository.LoginRepository;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LoginServiceAdapter implements LoginService {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    AgencyRepository agencyRepository;

    private String defaultPassword = "Password@123";

    @Override
    public LoginUserResponse getUserById(String id) {
        log.info("login service, userId : {}", id);
        Optional<User> user = loginRepository.findById(id);
        return user.isPresent() ? LoginUserResponseMapper.map(user.get()) : LoginUserResponse.builder().build();
    }

    @Override
    public WorkflowResponse createUser(LoginUserRequest request) {
        User user;
        if (request.getAgencyId() != null) {
            Optional<Agency> agency = agencyRepository.findById(request.getAgencyId());
            if (!agency.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Agency").build();
            user = loginRepository.save(User.builder().userId(request.getEmail()).email(request.getEmail()).firstName(request.getFirstName()).lastName(request.getLastName()).mobileNo(request.getMobileNo()).userRole(request.getUserRole().name()).gender(request.getGender()).password(defaultPassword).agency(agency.get()).build());
            return WorkflowResponse.builder().status(200).message("Success").data(LoginUserResponseMapper.map(user)).build();
        } else {
            user = loginRepository.save(User.builder().userId(request.getEmail()).email(request.getEmail()).firstName(request.getFirstName()).lastName(request.getLastName()).mobileNo(request.getMobileNo()).userRole(request.getUserRole().name()).gender(request.getGender()).password(defaultPassword).build());
            return WorkflowResponse.builder().status(200).message("Success").data(LoginUserResponseMapper.map(user)).build();
        }
    }

    @Override
    public List<LoginUserResponse> getUserByMobileNo(Long mobileNo) {
        return null;
    }

    @Override
    public WorkflowResponse getUsers() {
        log.info("users requested entered in service");
        List<User> userList = loginRepository.findAll();
        log.info("users list : " + userList.size());
        List<LoginUserResponse> response = userList != null ? userList.stream().map(user -> LoginUserResponseMapper.mapUser(user)).collect(Collectors.toList()) : null;
        return WorkflowResponse.builder().status(200).message("Success").data(response).build();
    }

    @Override
    public WorkflowResponse changePassword(String userId, String oldPassword, String newPassword) throws UserDetailsException {
        Optional<User> user = loginRepository.findById(userId);
        if (user.isPresent()) {
            User user1 = user.get();
            if (user1 != null && user1.getPassword().equals(oldPassword)) {
                user1.setPassword(newPassword);
                User updatedUser = loginRepository.save(user1);
                return WorkflowResponse.builder().status(200).message("Success").data(LoginUserResponseMapper.map(updatedUser)).build();

            }
        }
        return WorkflowResponse.builder()
                .status(400)
                .message("Invalid User Name or Old Password")
                .build();
    }

}
