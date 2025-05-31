package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.Patents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatentsRepository extends JpaRepository<Patents,Long> {
}
