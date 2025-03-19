package com.metaverse.workflow.ESDPTrainingProgramApplication.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.ESDPTrainingApplication;

public class ESDPTrainingProgramResponseMapper {

    public static ESDPTrainingProgramResponse map(ESDPTrainingApplication esdpTrainingProgram)
    {
        return ESDPTrainingProgramResponse.builder()
                .ESDPTrainingApplicationId(esdpTrainingProgram.getESDPTrainingApplicationId())
                .SHGName(esdpTrainingProgram.getOrganizationId().getNameOfTheSHG())
                .memberId(esdpTrainingProgram.getParticipantId().getMemberId())
                .memberName(esdpTrainingProgram.getParticipantId().getParticipantName())
                .SHGSector(esdpTrainingProgram.getOrganizationId().getSectors())
                .dateOfAwarenessProgram(DateUtil.dateToString(esdpTrainingProgram.getDateOfAwarenessProgram(),"dd-mm-yyyy"))
                .interestedInAttending15Days(esdpTrainingProgram.getInterestedInAttending15Days())
                .dateOfApplicationReceived(DateUtil.dateToString(esdpTrainingProgram.getDateOfApplicationReceived(),"dd-mm-yyyy"))
                .selectedForTraining(esdpTrainingProgram.getSelectedForTraining())
                .build();
    }
}

