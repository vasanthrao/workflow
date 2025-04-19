package com.metaverse.workflow.program.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProgramService {
    WorkflowResponse createProgram(ProgramRequest request);
    WorkflowResponse createProgramSession(ProgramSessionRequest request, List<MultipartFile> files);
    WorkflowResponse getProgramById(Long id);
    WorkflowResponse getProgramParticipants(Long id);
    public WorkflowResponse getPrograms();
    WorkflowResponse updateProgram(ProgramRequest request);
    WorkflowResponse saveProgramType(ProgramTypeRequest programTypeRequest);
    WorkflowResponse getAllProgramTypes();
    WorkflowResponse getAllProgramTypeByAgencyId(Long agencyId);
    WorkflowResponse getProgramParticipantAndVerifications(Long id);
    WorkflowResponse editProgramSession(ProgramSessionRequest request, List<MultipartFile> files);
    WorkflowResponse saveSessionImages(Long sessionId, String sessionStreamingUrl, MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4, MultipartFile image5);
    WorkflowResponse saveMediaCoverage(Long programId, Long mediaCoverageId, String mediaCoverageUrl, String mediaCoverageType, String date, MultipartFile image1, MultipartFile image2, MultipartFile image3);
    MultipartFile getProgramFile(Long fileId);

}
