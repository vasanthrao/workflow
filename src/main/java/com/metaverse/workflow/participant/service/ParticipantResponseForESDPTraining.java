package com.metaverse.workflow.participant.service;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class ParticipantResponseForESDPTraining {

    private String participantName;
    private String organizationName;
    private List<String> programDates;
}
