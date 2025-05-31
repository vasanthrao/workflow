package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepository extends JpaRepository<Barcode,Long> {
}
