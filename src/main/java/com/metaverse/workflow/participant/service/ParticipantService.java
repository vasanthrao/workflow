package com.metaverse.workflow.participant.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.Participant;

import java.util.Optional;

public interface ParticipantService {
    public WorkflowResponse saveParticipant(ParticipantRequest request);

    public WorkflowResponse getParticipants();
    WorkflowResponse getParticipantsByMobileNo(Long mobileNo);

    Optional<Participant> getParticipantsById(Long Id);

    public WorkflowResponse getOrganizationByParticipant(Long participantId);

    public  WorkflowResponse getParticipantByTypeOfProgram(String typeOfProgram);

}
