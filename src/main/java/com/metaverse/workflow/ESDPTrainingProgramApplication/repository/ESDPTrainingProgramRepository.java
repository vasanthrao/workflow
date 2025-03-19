package com.metaverse.workflow.ESDPTrainingProgramApplication.repository;

import com.metaverse.workflow.model.ESDPTrainingApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ESDPTrainingProgramRepository extends JpaRepository<ESDPTrainingApplication,Long> {
}
