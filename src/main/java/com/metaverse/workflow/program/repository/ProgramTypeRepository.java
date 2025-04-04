package com.metaverse.workflow.program.repository;

import com.metaverse.workflow.model.Programs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramsRepository extends JpaRepository<Programs,Integer> {
}
