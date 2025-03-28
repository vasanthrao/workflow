package com.metaverse.workflow.programoutcome.controller;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.outcomes.ProgramOutcomeTable;
import com.metaverse.workflow.programoutcome.dto.ProgramOutcomeTableResponse;
import com.metaverse.workflow.programoutcome.service.OutcomeDetails;
import com.metaverse.workflow.programoutcome.service.ProgramOutcomeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
public class ProgramOutcomeController {

    @Autowired
    ProgramOutcomeService programOutcomeService;

    @GetMapping(value = "/program/outcome/tables")
    public ResponseEntity<WorkflowResponse> getProgramOutcomeTables() {
        List<ProgramOutcomeTable> outcomeTableList = programOutcomeService.getProgramOutcomeTables();
        //Map<String, String> outcomeTableMap = outcomeTableList.stream().collect(Collectors.toMap(table -> table.getOutcomeTableDisplayName(), table -> table.getOutcomeTableName()));
        List<ProgramOutcomeTableResponse> response = outcomeTableList.stream().map(table -> ProgramOutcomeTableResponse.builder().outcomeTableId(table.getOutcomeTableId()).outcomeTableDisplayName(table.getOutcomeTableDisplayName()).outcomeTableName(table.getOutcomeTableName()).build()).collect(Collectors.toList());
        return ResponseEntity.ok(WorkflowResponse.builder().status(200).message("Success").data(response).build());
    }

    @GetMapping(value = "/program/outcome/details/{participantId}/{outcome}")
    public ResponseEntity<WorkflowResponse> getProgramOutcomeDetails(@PathVariable("participantId") Long participantId, @PathVariable("outcome") String outcome) {
        WorkflowResponse response = programOutcomeService.getOutcomeDetails(participantId, outcome);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/program/outcome/save/{outcome}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkflowResponse> saveOutcome(@PathVariable("outcome") String outcomeName, @RequestPart("data") String data) throws ParseException {
        log.info("Program outcome : {}", data);
        WorkflowResponse response = programOutcomeService.saveOutCome(outcomeName, data);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/program/getOutcomeByName/{outcomeName}")
    public ResponseEntity<WorkflowResponse> getProgramOutcomes(@PathVariable("outcome") String outcomeName) {
        WorkflowResponse response = programOutcomeService.getOutcomeDetailsByName(outcomeName);
        return ResponseEntity.ok(response);
    }

}
