package com.metaverse.workflow.participant.service;

import com.metaverse.workflow.common.response.WorkflowResponse;

public interface ParticipantService {
    public WorkflowResponse saveParticipant(ParticipantRequest request);

    public WorkflowResponse getParticipants();

}
