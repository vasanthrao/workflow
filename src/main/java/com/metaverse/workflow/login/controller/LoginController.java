package com.metaverse.workflow.login.controller;

import com.metaverse.workflow.login.service.LoginService;
import com.metaverse.workflow.login.service.LoginUserRequest;
import com.metaverse.workflow.login.service.LoginUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Operation(summary = "Get user by id", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = LoginUserResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @GetMapping(value = "/user", produces = {"application/json"})
    public ResponseEntity<LoginUserResponse> getUserById(@RequestHeader("userId") Long userId) {
        log.info("login controller, userId : {}", userId);
        LoginUserResponse response = loginService.getUserById(userId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user by id", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = LoginUserResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PostMapping(value = "/user/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LoginUserResponse> createUser(@RequestBody LoginUserRequest request) {
        log.info("login controller, userId : {}", request.getMobileNo());
        LoginUserResponse response = loginService.createUser(request);
        return ResponseEntity.ok(response);
    }


}
