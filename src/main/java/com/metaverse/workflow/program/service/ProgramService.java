package com.metaverse.workflow.program.service;

public interface ProgramService {
    ProgramResponse createProgram(ProgramRequest request);
    ProgramResponse getProgramById(Long id);
}
