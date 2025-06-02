package com.metaverse.workflow.programoutcometargets.repository;

import com.metaverse.workflow.model.FinancialTarget;
import com.metaverse.workflow.model.PhysicalTarget;
import com.metaverse.workflow.model.outcomes.ProgramOutcomeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhysicalRepository extends JpaRepository<PhysicalTarget,Long> {
    List<PhysicalTarget> findByAgencyAgencyId(Long agencyId);

    List<PhysicalTarget> findByProgramOutcomeTableAndFinancialYear(ProgramOutcomeTable outcomeTable, String financialYear);
}
