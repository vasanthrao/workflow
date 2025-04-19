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
    public ResponseEntity<WorkflowResponse> createSession(@RequestPart("data") String data, @RequestPart(value="files", required = false) List<MultipartFile> files) throws ParseException {
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

    @GetMapping("/program/participant-verification/{programId}")
    public ResponseEntity<WorkflowResponse> getParticipantAndVerificationByProgramId(@PathVariable("programId") Long programId)
    {
        WorkflowResponse response = programService.getProgramParticipantAndVerifications(programId);
        return ResponseEntity.ok(response);
    }



    @PostMapping(value = "/program/session/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkflowResponse> editProgramSession(@RequestPart("data") String data, @RequestPart(value = "files", required = false) List<MultipartFile> files) throws ParseException {
        log.info("Program controller, title : {}", data);
        JSONParser parser = new JSONParser();
        ProgramSessionRequest request = parser.parse(data, ProgramSessionRequest.class);
        WorkflowResponse response = programService.editProgramSession(request, files);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/program/execution/images", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkflowResponse> saveSessionImages(@RequestPart("sessionId") Long sessionId,
                                                              @RequestPart(value = "sessionStreamingUrl", required = false) String sessionStreamingUrl,
                                                              @RequestPart(value = "image1") MultipartFile image1,
                                                              @RequestPart(value = "image2") MultipartFile image2,
                                                              @RequestPart(value = "image3") MultipartFile image3,
                                                              @RequestPart(value = "image4", required = false) MultipartFile image4,
                                                              @RequestPart(value = "image5", required = false) MultipartFile image5) throws ParseException {
        log.info("Program controller save session images, sessionId : {}", sessionId);
        WorkflowResponse response = programService.saveSessionImages(sessionId, sessionStreamingUrl, image1, image2, image3, image4, image5);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/program/execution/media-coverage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkflowResponse> saveMediaCoverage(@RequestPart("programId") Long programId,
                                                             @RequestPart(value = "mediaCoverageId", required = false) Long mediaCoverageId,
                                                             @RequestPart(value = "mediaCoverageUrl", required = false) String mediaCoverageUrl,
                                                             @RequestPart("mediaCoverageType") String mediaCoverageType,
                                                             @RequestPart("date") String date,
                                                             @RequestPart(value = "image1") MultipartFile image1,
                                                             @RequestPart(value = "image2", required = false) MultipartFile image2,
                                                             @RequestPart(value = "image3", required = false) MultipartFile image3) throws ParseException {
        log.info("Program controller save program media, programId : {}", programId);
        WorkflowResponse response = programService.saveMediaCoverage(programId, mediaCoverageId, mediaCoverageUrl, mediaCoverageType, date, image1, image2, image3);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/program/file/download/{fileId}")
    public ResponseEntity<MultipartFile> getProgramFile(@PathVariable("fileId") Long fileId)
    {
        MultipartFile response = programService.getProgramFile(fileId);
        return ResponseEntity.ok(response);
    }


}
