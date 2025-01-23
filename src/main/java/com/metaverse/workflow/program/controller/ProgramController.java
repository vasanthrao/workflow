package com.metaverse.workflow.program.controller;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.login.service.LoginUserRequest;
import com.metaverse.workflow.login.service.LoginUserResponse;
import com.metaverse.workflow.program.service.ProgramRequest;
import com.metaverse.workflow.program.service.ProgramResponse;
import com.metaverse.workflow.program.service.ProgramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ProgramController {

    @Autowired
    ProgramService programService;

    @Operation(summary = "Create program", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = LoginUserResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PostMapping(value = "/program/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WorkflowResponse> createProgram(@RequestBody ProgramRequest request) {
        log.info("Program controller, title : {}", request.getProgramTitle());
        ProgramResponse response = programService.createProgram(request);
        return ResponseEntity.ok(WorkflowResponse.builder().status(200).message("Success").data(response).build());
    }

}
