package com.metaverse.workflow.program.service;

import com.metaverse.workflow.model.Program;
import com.metaverse.workflow.program.repository.ProgramRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProgramServiceAdapter implements ProgramService {

    @Autowired
    ProgramRepository programRepository;

    @Override
    public ProgramResponse createProgram(ProgramRequest request) {
        Program program = programRepository.save(ProgramRequestMapper.map(request));
        return ProgramResponseMapper.map(program);
    }

    @Override
    public ProgramResponse getProgramById(Long id) {
        return null;
    }
}
