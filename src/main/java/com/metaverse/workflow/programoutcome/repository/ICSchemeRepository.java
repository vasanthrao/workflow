package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.ICScheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ICSchemeRepository extends JpaRepository<ICScheme,Long> {
    long countByAgencyAgencyIdAndDateOfExportBetween(Long agencyId, Date dQ1Start, Date dQ1End);
}
