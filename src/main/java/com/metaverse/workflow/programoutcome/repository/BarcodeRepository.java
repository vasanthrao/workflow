package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface BarcodeRepository extends JpaRepository<Barcode,Long> {
    long countByAgencyAgencyIdAndDateOfRegistrationBetween(Long agencyId, Date dQ1Start, Date dQ1End);
}
