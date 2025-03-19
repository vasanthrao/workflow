package com.metaverse.workflow.ESDPTrainingProgramApplication.service;

import com.metaverse.workflow.common.response.WorkflowResponse;

public interface ESDPTrainingProgramService {

    WorkflowResponse saveESDPTrainingProgram(ESDPTrainingProgramRequest esdpTrainingProgramRequest);
    WorkflowResponse getESDPTrainingProgramData();
}
