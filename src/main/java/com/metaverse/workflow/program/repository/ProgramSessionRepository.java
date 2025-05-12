package com.metaverse.workflow.program.repository;

import com.metaverse.workflow.model.ProgramSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramSessionRepository extends JpaRepository<ProgramSession, Long> {
    void deleteByProgramProgramId(Long programId);
}
