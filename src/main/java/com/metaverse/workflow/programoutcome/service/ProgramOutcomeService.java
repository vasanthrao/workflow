package com.metaverse.workflow.programoutcome.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.outcomes.ProgramOutcomeTable;
import net.minidev.json.parser.ParseException;

import java.util.List;

public interface ProgramOutcomeService {

    List<ProgramOutcomeTable> getProgramOutcomeTables();

    WorkflowResponse getOutcomeDetails(Long participantId, String outcome);

    WorkflowResponse saveOutCome(String outcomeName, String data) throws ParseException;

    WorkflowResponse getOutcomeDetailsByName(String outcome);

}
