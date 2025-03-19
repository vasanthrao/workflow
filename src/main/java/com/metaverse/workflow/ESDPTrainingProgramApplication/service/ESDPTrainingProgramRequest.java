package com.metaverse.workflow.ESDPTrainingProgramApplication.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter

public class ESDPTrainingProgramRequest {

    private Long participantId;
    private Long organizationId;
    private Long agencyId;
    private String dateOfAwarenessProgram;
    private String interestedInAttending15Days;
    private String dateOfApplicationReceived;
    private String selectedForTraining;
}
