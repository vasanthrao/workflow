package com.metaverse.workflow.ESDPTrainingProgramApplication.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.metaverse.workflow.model.Sector;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ESDPTrainingProgramResponse {

    Long ESDPTrainingApplicationId;
    String SHGName;
    String memberId;
    String memberName;
    List<Sector> SHGSector;
    String dateOfAwarenessProgram;
    String interestedInAttending15Days;
    String dateOfApplicationReceived;
    String selectedForTraining;
}
