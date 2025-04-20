package com.metaverse.workflow.program.service;

import com.metaverse.workflow.agency.repository.AgencyRepository;
import com.metaverse.workflow.callcenter.repository.CallCenterVerificationRepository;
import com.metaverse.workflow.common.fileservice.StorageService;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.location.repository.LocationRepository;
import com.metaverse.workflow.model.*;
import com.metaverse.workflow.participant.service.ParticipantResponse;
import com.metaverse.workflow.program.repository.*;
import com.metaverse.workflow.resouce.repository.ResourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
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

    @Autowired
    CallCenterVerificationRepository callCenterVerificationRepository;

    @Autowired
    MediaCoverageRepository mediaCoverageRepository;

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
        if(files != null && files.size() > 0) {
            List<String> filePaths = storageProgramFiles(files, program.get().getProgramId(), "files");
            List<ProgramSessionFile> sessionFiles = ProgramRequestMapper.mapProgramFiles(filePaths);
            sessionFiles.stream().forEach(file -> file.setProgramSession(session));
            sessionFiles = programSessionFileRepository.saveAll(sessionFiles);
            return WorkflowResponse.builder().status(200).message("Success").data(ProgramResponseMapper.mapSession(programSession, sessionFiles)).build();
        }else {
            return WorkflowResponse.builder().status(200).message("Success").data(ProgramResponseMapper.mapSession(programSession, null)).build();
        }
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

    private List<String> storageProgramFiles(List<MultipartFile> files, Long programId, String folderName) {
        List<String> uploadFilePaths = new ArrayList<>();
        files.stream().forEach(file -> {
            String filePath = storageService.store(file, programId, folderName);
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

    @Override
    public WorkflowResponse getProgramParticipantAndVerifications(Long id) {
        Optional<Program> program = programRepository.findById(id);
        if (!program.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Program Id").build();
        List<ParticipantResponse> response = ProgramResponseMapper.mapProgramParticipants(program.get().getParticipants());
        List<Participant> participantList = program.get().getParticipants();
        if (participantList == null || participantList.size() ==0) return WorkflowResponse.builder().status(400).message("Invalid Program Id").build();
        List<CallCenterVerification> callCenterVerificationList = callCenterVerificationRepository.findByProgramId(id);
        List<ParticipantVerificationResponse> responseList = ProgramResponseMapper.mapProgramParticipantVerification(participantList, callCenterVerificationList);
        return WorkflowResponse.builder().status(200).message("Success").data(responseList).build();
    }

    @Override
    public WorkflowResponse editProgramSession(ProgramSessionRequest request, List<MultipartFile> files) {
        Optional<ProgramSession> session = programSessionRepository.findById(request.getProgramSessionId());
        if (!session.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Program Session").build();
        if(session.get().getResource().getResourceId() != request.getResourceId()) {
            Optional<Resource> resource = resourceRepository.findById(request.getResourceId());
            if (!resource.isPresent())
                return WorkflowResponse.builder().status(400).message("Invalid Resource").build();
            else
                session.get().setResource(resource.get());
        }
        if(files != null && files.size() > 0) {
            List<String> filePaths = storageProgramFiles(files, session.get().getProgram().getProgramId(), "files");
            List<ProgramSessionFile> sessionFiles = ProgramRequestMapper.mapProgramFiles(filePaths);
            sessionFiles.stream().forEach(file -> file.setProgramSession(session.get()));
            sessionFiles = programSessionFileRepository.saveAll(sessionFiles);
            session.get().getProgramSessionFileList().addAll(sessionFiles);

        }
        ProgramSession response = programSessionRepository.save(ProgramRequestMapper.mapSession(request, session.get()));
        return WorkflowResponse.builder().status(200).message("Success").data(ProgramResponseMapper.mapSession(response, response.getProgramSessionFileList())).build();
    }

    @Override
    public WorkflowResponse saveSessionImages(ProgramSessionRequest request, MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4, MultipartFile image5) {
        Optional<ProgramSession> session = programSessionRepository.findById(request.getProgramSessionId());
        if (!session.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Program Session").build();
        session.get().setSessionStreamingUrl(request.getSessionStreamingUrl());
        if(image1 != null) {
            String filePath1 = storageService.store(image1, session.get().getProgram().getProgramId(), "photos");
            ProgramSessionFile file1 = programSessionFileRepository.save(ProgramSessionFile.builder().fileType("PHOTO").filePath(filePath1).programSessionFileId(request.getImage1()).build());
            session.get().setImage1(file1.getProgramSessionFileId());
        }
        if(image2 != null) {
            String filePath2 = storageService.store(image2, session.get().getProgram().getProgramId(), "photos");
            ProgramSessionFile file2 = programSessionFileRepository.save(ProgramSessionFile.builder().fileType("PHOTO").filePath(filePath2).programSessionFileId(request.getImage2()).build());
            session.get().setImage2(file2.getProgramSessionFileId());
        }
        if(image3 != null) {
            String filePath3 = storageService.store(image3, session.get().getProgram().getProgramId(), "photos");
            ProgramSessionFile file3 = programSessionFileRepository.save(ProgramSessionFile.builder().fileType("PHOTO").filePath(filePath3).programSessionFileId(request.getImage3()).build());
            session.get().setImage3(file3.getProgramSessionFileId());
        }
        if(image4 != null) {
            String filePath4 = storageService.store(image4, session.get().getProgram().getProgramId(), "photos");
            ProgramSessionFile file4 = programSessionFileRepository.save(ProgramSessionFile.builder().fileType("PHOTO").filePath(filePath4).programSessionFileId(request.getImage4()).build());
            session.get().setImage4(file4.getProgramSessionFileId());
        }
        if(image5 != null) {
            String filePath5 = storageService.store(image5, session.get().getProgram().getProgramId(), "photos");
            ProgramSessionFile file5 = programSessionFileRepository.save(ProgramSessionFile.builder().fileType("PHOTO").filePath(filePath5).programSessionFileId(request.getImage5()).build());
            session.get().setImage5(file5.getProgramSessionFileId());
        }
        ProgramSession response = programSessionRepository.save(session.get());
        return WorkflowResponse.builder().status(200).message("Success").data(ProgramResponseMapper.mapSession(response, response.getProgramSessionFileList())).build();
    }

    @Override
    public WorkflowResponse saveMediaCoverage(MediaCoverageRequest request, MultipartFile image1, MultipartFile image2, MultipartFile image3) {
        MediaCoverage coverage;
        if(request.getMediaCoverageId() != null) {
            Optional<MediaCoverage> mediaCoverage = mediaCoverageRepository.findById(request.getMediaCoverageId());
            if (!mediaCoverage.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Program MediaCoverage").build();
            mediaCoverage.get().setMediaCoverageUrl(request.getMediaCoverageUrl());
            mediaCoverage.get().setMediaCoverageType(request.getMediaCoverageType());
            mediaCoverage.get().setDate(DateUtil.stringToDate(request.getDate(), "dd-mm-yyyy"));
            if(image1 != null) {
                String filePath1 = storageService.store(image1, mediaCoverage.get().getProgram().getProgramId(), "media");
                ProgramSessionFile file1 = programSessionFileRepository.save(ProgramSessionFile.builder().fileType("MEDIA").filePath(filePath1).programSessionFileId(request.getImage1()).build());
                mediaCoverage.get().setImage1(file1.getProgramSessionFileId());
            }
            if(image2 != null) {
                String filePath2 = storageService.store(image2, mediaCoverage.get().getProgram().getProgramId(), "media");
                ProgramSessionFile file2 = programSessionFileRepository.save(ProgramSessionFile.builder().fileType("MEDIA").filePath(filePath2).programSessionFileId(request.getImage2()).build());
                mediaCoverage.get().setImage2(file2.getProgramSessionFileId());
            }
            if(image3 != null) {
                String filePath3 = storageService.store(image3, mediaCoverage.get().getProgram().getProgramId(), "media");
                ProgramSessionFile file3 = programSessionFileRepository.save(ProgramSessionFile.builder().fileType("MEDIA").filePath(filePath3).programSessionFileId(request.getImage3()).build());
                mediaCoverage.get().setImage3(file3.getProgramSessionFileId());
            }
            coverage = mediaCoverageRepository.save(mediaCoverage.get());
        } else {
            Optional<Program> program = programRepository.findById(request.getProgramId());
            if (!program.isPresent()) return WorkflowResponse.builder().status(400).message("Invalid Program Id").build();
            MediaCoverage mediaCoverage = MediaCoverage.builder().mediaCoverageType(request.getMediaCoverageType()).mediaCoverageUrl(request.getMediaCoverageUrl()).date(DateUtil.stringToDate(request.getDate(), "dd-mm-yyyy")).program(program.get()).build();
            if(image1 != null) {
                String filePath1 = storageService.store(image1, program.get().getProgramId(), "media");
                ProgramSessionFile file1 = programSessionFileRepository.save(ProgramSessionFile.builder().fileType("MEDIA").filePath(filePath1).build());
                mediaCoverage.setImage1(file1.getProgramSessionFileId());
            }
            if(image2 != null) {
                String filePath2 = storageService.store(image2, program.get().getProgramId(), "media");
                ProgramSessionFile file2 = programSessionFileRepository.save(ProgramSessionFile.builder().fileType("MEDIA").filePath(filePath2).build());
                mediaCoverage.setImage2(file2.getProgramSessionFileId());
            }
            if(image3 != null) {
                String filePath3 = storageService.store(image3, program.get().getProgramId(), "media");
                ProgramSessionFile file3 = programSessionFileRepository.save(ProgramSessionFile.builder().fileType("MEDIA").filePath(filePath3).build());
                mediaCoverage.setImage3(file3.getProgramSessionFileId());
            }
            coverage = mediaCoverageRepository.save(mediaCoverage);
        }
        return WorkflowResponse.builder().status(200).message("Success").data(ProgramResponseMapper.mapMediaCoverage(coverage)).build();
    }

    @Override
    public Path getProgramFile(Long fileId) {
        Optional<ProgramSessionFile> file = programSessionFileRepository.findById(fileId);
        if (file.isPresent()) {
            Path path = storageService.load(file.get().getFilePath());
            return path;
        } else
            return null;
    }
}



