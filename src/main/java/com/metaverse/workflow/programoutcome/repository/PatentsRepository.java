package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.Patents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface PatentsRepository extends JpaRepository<Patents,Long> {
    long countByAgencyAgencyIdAndDateOfExportBetween(Long agencyId, Date dQ1Start, Date dQ1End);
}
