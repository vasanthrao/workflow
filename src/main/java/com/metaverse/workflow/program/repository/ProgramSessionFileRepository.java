package com.metaverse.workflow.program.repository;

import com.metaverse.workflow.model.ProgramSession;
import com.metaverse.workflow.model.ProgramSessionFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramSessionFileRepository extends JpaRepository<ProgramSessionFile, Long> {
}
