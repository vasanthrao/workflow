package com.metaverse.workflow.ESDPTrainingProgramApplication.service;

import com.metaverse.workflow.ESDPTrainingProgramApplication.repository.ESDPTrainingProgramRepository;
import com.metaverse.workflow.agency.service.AgencyService;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.*;
import com.metaverse.workflow.organization.service.OrganizationService;
import com.metaverse.workflow.participant.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ESDPTrainingProgramServiceAdepter implements ESDPTrainingProgramService{

    @Autowired
    ESDPTrainingProgramRepository esdpTrainingProgramRepository;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    ParticipantService participantService;
    @Autowired
    AgencyService agencyService;

    @Override
    public WorkflowResponse saveESDPTrainingProgram(ESDPTrainingProgramRequest esdpTrainingProgramRequest){
        Agency agency= agencyService.getAgencyById(esdpTrainingProgramRequest.getAgencyId());
        Optional<Organization> organization = organizationService.getOrganizationById(esdpTrainingProgramRequest.getOrganizationId());
        Optional<Participant>   participant =  participantService.getParticipantsById(esdpTrainingProgramRequest.getParticipantId());
        if(!participant.isPresent() || !organization.isPresent())
        {
            return  WorkflowResponse.builder().status(400).message("Participant Or Organization Not  found").build();
        }
        ESDPTrainingApplication esdpTrainingProgram= ESDPTrainingProgramRequestMapper.map(esdpTrainingProgramRequest,organization.get(),participant.get(),agency);
        ESDPTrainingApplication response= esdpTrainingProgramRepository.save(esdpTrainingProgram);
        return WorkflowResponse.builder()
                .message("Training Program saved successfully")
                .status(200)
                .data(response)
                .build();
    }


    @Override
   public WorkflowResponse getESDPTrainingProgramData(){
       List<ESDPTrainingApplication>  esdpTrainingProgramList=esdpTrainingProgramRepository.findAll();
       List<ESDPTrainingProgramResponse> esdpTrainingProgramResponseList =esdpTrainingProgramList.stream().map(esdpTrainingProgram -> ESDPTrainingProgramResponseMapper.map(esdpTrainingProgram)).collect(Collectors.toList());
        if(esdpTrainingProgramList.isEmpty())return WorkflowResponse.builder().status(400).message("Data is not available").build();
        return WorkflowResponse.builder().status(200).message("success").data(esdpTrainingProgramResponseList).build();
    }
}
