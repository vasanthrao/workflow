package com.metaverse.workflow.participant.service;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class ParticipantResponseForESDPTraining {

    private String participantName;
    private String organizationName;
    private Long participantId;
    private Long organizationId;
    private List<String> programDates;
}
