package com.metaverse.workflow.participant.service;

import com.metaverse.workflow.agency.service.AgencyResponse;
import com.metaverse.workflow.agency.service.AgencyResponseMapper;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.model.Program;
import com.metaverse.workflow.organization.service.OrganizationResponse;
import com.metaverse.workflow.organization.service.OrganizationResponseMapper;
import com.metaverse.workflow.program.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metaverse.workflow.model.Organization;
import com.metaverse.workflow.model.Participant;
import com.metaverse.workflow.organization.service.OrganizationService;
import com.metaverse.workflow.participant.repository.ParticipantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceAdapter implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private ProgramRepository programRepository;

    @Override
    public WorkflowResponse saveParticipant(ParticipantRequest request) {
        List<Program> programList = new ArrayList<>();
        Set<Long> programIds = request.getProgramIds();
        for (Long id : programIds) {
            Optional<Program> program = programRepository.findById(id);
            if (program.isPresent()) {
                programList.add(program.get());
            }
        }
        if (programList.size() == 0)
            return WorkflowResponse.builder().status(400).message("Programs not found").build();
        Optional<Organization> organization = organizationService.getOrganizationById(request.getOrganizationId());
        if (!organization.isPresent())
            return WorkflowResponse.builder().status(400).message("Organization not found").build();
        Participant participant = ParticipantRequestMapper.map(request, organization.get(), programList);
        Participant savedParticipant = participantRepository.save(participant);
        ParticipantResponse participantResponse = ParticipantResponseMapper.map(savedParticipant);
        return WorkflowResponse.builder().status(200).message("Success").data(participantResponse).build();
    }

    @Override
    public WorkflowResponse getParticipants() {
        List<Participant> participantList = participantRepository.findAll();
        List<ParticipantResponse> response = participantList != null ? participantList.stream().map(participant -> ParticipantResponseMapper.map(participant)).collect(Collectors.toList()) : null;
        return WorkflowResponse.builder().message("Success").status(200).data(response).build();
    }

    @Override
    public WorkflowResponse getParticipantsByMobileNo(Long mobileNo) {

        Participant participant = participantRepository.findByMobileNo(mobileNo);
        ParticipantResponseForESDPTraining response = ParticipantResponseMapper.map1(participant);
        return WorkflowResponse.builder().message("Success").status(200).data(response).build();
    }


    @Override
    public Optional<Participant> getParticipantsById(Long id) {
        Optional<Participant> participant = participantRepository.findById(id);
        return participant;
    }


    @Override
    public WorkflowResponse getOrganizationByParticipant(Long participantId) {

        Optional<Participant> participants = participantRepository.findById(participantId);
        if (!participants.isPresent()) return WorkflowResponse.builder().message("Not Found").status(400).build();
        OrganizationResponse organizationResponse = OrganizationResponseMapper.map(participants.get().getOrganization());
        return WorkflowResponse.builder()
                .message("Organization Details").
                status(200)
                .data(organizationResponse)
                .build();


    }

    public WorkflowResponse getParticipantByTypeOfProgram(String typeOfProgram) {
        List<Participant> participantList = participantRepository.findByProgramType(typeOfProgram);
        if (participantList.isEmpty()) return WorkflowResponse.builder()
                .message("Not found such type of program.")
                .status(400).build();
        List<ParticipantResponse> response = participantList.stream().map(participant -> ParticipantResponseMapper.map(participant)).collect(Collectors.toList());
        return WorkflowResponse.builder().message("Success")
                .status(200).data(response).build();
    }

}
