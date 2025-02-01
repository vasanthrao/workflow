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
}
