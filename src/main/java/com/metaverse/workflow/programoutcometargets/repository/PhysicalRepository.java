package com.metaverse.workflow.programoutcometargets.repository;

import com.metaverse.workflow.model.FinancialTarget;
import com.metaverse.workflow.model.PhysicalTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhysicalRepository extends JpaRepository<PhysicalTarget,Long> {
    List<PhysicalTarget> findByAgencyAgencyId(Long agencyId);
}
