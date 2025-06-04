package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.TreadMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface TreadMarkRepository extends JpaRepository<TreadMark,Long> {
    long countByAgencyAgencyIdAndDateOfRegistrationBetween(Long agencyId, Date dQ1Start, Date dQ1End);
}
