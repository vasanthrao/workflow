package com.metaverse.workflow.program.service;

import com.metaverse.workflow.agency.repository.AgencyRepository;
import com.metaverse.workflow.common.fileservice.StorageService;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.location.repository.LocationRepository;
import com.metaverse.workflow.model.*;
import com.metaverse.workflow.participant.service.ParticipantResponse;
import com.metaverse.workflow.program.repository.ProgramRepository;
import com.metaverse.workflow.program.repository.ProgramSessionFileRepository;
import com.metaverse.workflow.program.repository.ProgramSessionRepository;
import com.metaverse.workflow.program.repository.ProgramTypeRepository;
import com.metaverse.workflow.resouce.repository.ResourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProgramServiceAdapter implements ProgramService {

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    ProgramSessionRepository programSessionRepository;

    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    StorageService storageService;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ProgramSessionFileRepository programSessionFileRepository;

    @Autowired
    ProgramTypeRepository programTypeRepository;

    @Override
    public WorkflowResponse createProgram(ProgramRequest request) {
        Optional<Agency> agency = agencyRepository.findById(request.getAgencyId());
        if (!agency.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Agency").build();
        Optional<Location> location = locationRepository.findById(request.getLocationId());
        if (!location.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Location").build();
        Program program = programRepository.save(ProgramRequestMapper.map(request, agency.get(), location.get()));
        return WorkflowResponse.builder().status(200).message("Success").data(ProgramResponseMapper.map(program)).build();
    }

    @Override
    public WorkflowResponse createProgramSession(ProgramSessionRequest request, List<MultipartFile> files) {
        Optional<Resource> resource = resourceRepository.findById(request.getResourceId());
        if (!resource.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Resource").build();
        Optional<Program> program = programRepository.findById(request.getProgramId());
        if (!program.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Program").build();
        ProgramSession session = ProgramRequestMapper.mapSession(request, resource.get(), program.get());
        ProgramSession programSession = programSessionRepository.save(session);
        List<String> filePaths = storageProgramFiles(files, programSession.getProgramSessionId());
        List<ProgramSessionFile> sessionFiles = ProgramRequestMapper.mapProgramFiles(request.getVideoUrls(), filePaths);
        sessionFiles.stream().forEach(file -> file.setProgramSession(session));
        sessionFiles = programSessionFileRepository.saveAll(sessionFiles);
        return WorkflowResponse.builder().status(200).message("Success").data(ProgramResponseMapper.mapSession(programSession, sessionFiles)).build();
    }

    @Override
    public WorkflowResponse getProgramById(Long id) {
        Optional<Program> program = programRepository.findById(id);
        if (!program.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Program").build();
        ProgramResponse response = ProgramResponseMapper.mapProgram(program.get());
        return WorkflowResponse.builder().status(200).message("Success").data(response).build();
    }

    @Override
    public WorkflowResponse getProgramParticipants(Long id) {
        Optional<Program> program = programRepository.findById(id);
        if (!program.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Program Id").build();
        List<ParticipantResponse> response = ProgramResponseMapper.mapProgramParticipants(program.get().getParticipants());
        return WorkflowResponse.builder().status(200).message("Success").data(response).build();
    }

    private List<String> storageProgramFiles(List<MultipartFile> files, Long sessionId) {
        List<String> uploadFilePaths = new ArrayList<>();
        files.stream().forEach(file -> {
            String filePath = storageService.store(file, sessionId);
            uploadFilePaths.add(filePath);
        });
        return uploadFilePaths;
    }

    @Override
    public WorkflowResponse getPrograms() {
        List<Program> programList = programRepository.findAll();
        List<ProgramResponse> response = programList != null ? programList.stream().map(program -> ProgramResponseMapper.map(program)).collect(Collectors.toList()) : null;
        return WorkflowResponse.builder().message("Success").status(200).data(response).build();
    }

    @Override
    @Transactional
    public WorkflowResponse updateProgram(ProgramRequest request) {
        Optional<Program> programOptional = programRepository.findById(request.getProgramId());
        if (!programOptional.isPresent())
            return WorkflowResponse.builder().status(400).message("Invalid Program").build();

        Optional<Agency> agency = agencyRepository.findById(request.getAgencyId());
        if (!agency.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Agency").build();
        Optional<Location> location = locationRepository.findById(request.getLocationId());
        if (!location.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Location").build();

        Program program = programRepository.save(ProgramRequestMapper.mapUpdate(request, agency.get(), location.get(), programOptional.get()));
        return WorkflowResponse.builder().status(200).message("Success").data(ProgramResponseMapper.map(program)).build();
    }

    @Override
    public WorkflowResponse saveProgramType(ProgramTypeRequest programTypeRequest) {
        Optional<Agency> agency = agencyRepository.findById(programTypeRequest.getAgencyId());
        if (!agency.isPresent()) return WorkflowResponse.builder().status(400).message("Agency not found").build();
        ProgramType programType = ProgramType.builder().programType(programTypeRequest.getProgramType())
                .agency(agency.get()).build();
        ProgramType response = programTypeRepository.save(programType);

        return WorkflowResponse.builder().status(200).message("Program type saved successfully").data(response).build();
    }

    @Override
    public WorkflowResponse getAllProgramTypes() {
        List<ProgramType> programTypeList = programTypeRepository.findAll();
        List<ProgramTypeResponse> typeResponses = programTypeList.stream().map(ProgramTypeResponseMapper::map).toList();
        return WorkflowResponse.builder().status(200).message("Success").data(typeResponses).build();

    }

    @Override
    public WorkflowResponse getAllProgramTypeByAgencyId(Long agencyId) {
        List<ProgramType> programTypeList = programTypeRepository.findByAgencyAgencyId(agencyId);
        if (programTypeList.isEmpty())
            return WorkflowResponse.builder().status(400).message("Program types is not found for this agency").build();
        List<ProgramTypeResponse> typeResponses = programTypeList.stream().map(ProgramTypeResponseMapper::map).toList();
        return WorkflowResponse.builder().status(200).message("Success").data(typeResponses).build();

    }
}



