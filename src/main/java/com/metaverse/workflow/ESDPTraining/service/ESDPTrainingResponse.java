package com.metaverse.workflow.ESDPTraining.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.metaverse.workflow.model.Sector;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ESDPTrainingResponse {
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
