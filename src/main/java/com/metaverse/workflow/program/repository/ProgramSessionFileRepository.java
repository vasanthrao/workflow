package com.metaverse.workflow.program.repository;

import com.metaverse.workflow.model.ProgramSession;
import com.metaverse.workflow.model.ProgramSessionFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgramSessionFileRepository extends JpaRepository<ProgramSessionFile, Long> {

    @Query("SELECT p FROM ProgramSessionFile p WHERE p.programSession.programSessionId = :programSessionId")
    List<ProgramSessionFile> findByProgramSessionId(Long programSessionId);
}
