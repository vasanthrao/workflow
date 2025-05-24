package com.metaverse.workflow.program.service;

import com.metaverse.workflow.model.Program;
import com.metaverse.workflow.program.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OverdueProgramUpdater {

    @Autowired
    private ProgramRepository programRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at 2:00 AM
    public void updateOverduePrograms() {
        LocalDate twoDaysAgo = LocalDate.now().minusDays(2);

        List<Program> overduePrograms = programRepository.findProgramsWithStartDateBefore(twoDaysAgo);

        for (Program program : overduePrograms) {
            program.setOverdue(true); // Assuming Program has setOverdue(boolean)
        }

        programRepository.saveAll(overduePrograms); // Batch update
    }
}
