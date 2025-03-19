package com.metaverse.workflow.ESDPTrainingProgramApplication.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.model.ESDPTrainingApplication;
import com.metaverse.workflow.model.Organization;
import com.metaverse.workflow.model.Participant;

public class ESDPTrainingProgramRequestMapper {


    public static ESDPTrainingApplication map(ESDPTrainingProgramRequest request, Organization organization,
                                              Participant participant, Agency agency)
    {
        return ESDPTrainingApplication.builder()
                .dateOfAwarenessProgram(DateUtil.stringToDate(request.getDateOfAwarenessProgram(),"dd-mm-yyyy"))
                .selectedForTraining(request.getSelectedForTraining())
                .interestedInAttending15Days(request.getInterestedInAttending15Days())
                .dateOfApplicationReceived(DateUtil.stringToDate(request.getDateOfApplicationReceived(),"dd-mm-yyyy"))
                .participantId(participant)
                .organizationId(organization)
                .agency(agency)
                .build();
    }
}
