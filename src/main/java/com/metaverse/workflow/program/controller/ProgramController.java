package com.metaverse.workflow.program.controller;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.program.service.ProgramRequest;
import com.metaverse.workflow.program.service.ProgramService;
import com.metaverse.workflow.program.service.ProgramSessionRequest;
import com.metaverse.workflow.program.service.ProgramTypeRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class ProgramController {

    @Autowired
    ProgramService programService;

    @Operation(summary = "Create program", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = WorkflowResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PostMapping(value = "/program/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WorkflowResponse> createProgram(@RequestBody ProgramRequest request) {
        log.info("Program controller, title : {}", request.getProgramTitle());
        WorkflowResponse response = programService.createProgram(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create session", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = WorkflowResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PostMapping(value = "/program/session/create",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkflowResponse> createSession(@RequestPart("data") String data, @RequestPart("files") List<MultipartFile> files) throws ParseException {
        log.info("Program controller, title : {}", data);
        JSONParser parser = new JSONParser();
        ProgramSessionRequest request = parser.parse(data, ProgramSessionRequest.class);
        WorkflowResponse response = programService.createProgramSession(request, files);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/program/participants/{programId}")
    public ResponseEntity<WorkflowResponse> getParticipantsByProgramId(@PathVariable("programId") Long programId)
    {
        WorkflowResponse response = programService.getProgramParticipants(programId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/program/{programId}")
    public ResponseEntity<WorkflowResponse> getProgramById(@PathVariable("programId") Long programId)
    {
        WorkflowResponse response = programService.getProgramById(programId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/programs")
    public ResponseEntity<WorkflowResponse> getPrograms()
    {
        WorkflowResponse response = programService.getPrograms();
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/updateProgram", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WorkflowResponse> updateProgram(@RequestBody ProgramRequest request) {
        log.info("Updating Program with ID: {}", request.getProgramId());
        WorkflowResponse response = programService.updateProgram(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save/program/type")
    public ResponseEntity<WorkflowResponse> saveProgramTypes(@RequestBody ProgramTypeRequest request)
    {
        WorkflowResponse response = programService.saveProgramType(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/program/types")
    public ResponseEntity<WorkflowResponse> getAllProgramTypes()
    {
        WorkflowResponse response = programService.getAllProgramTypes();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/program/types/agency/id/{agencyId}")
    public ResponseEntity<WorkflowResponse> getAllProgramTypesByAgencyId(@PathVariable Long agencyId)
    {
        WorkflowResponse response = programService.getAllProgramTypeByAgencyId(agencyId);
        return ResponseEntity.ok(response);
    }

}
