package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.GIProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface GIProductRepository extends JpaRepository<GIProduct,Long> {
    long countByAgencyAgencyIdAndDateOfGIRegistrationBetween(Long agencyId, Date dQ1Start, Date dQ1End);
}
