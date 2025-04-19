package com.metaverse.workflow.expenditure.repository;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.model.ProgramExpenditure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramExpenditureRepository extends JpaRepository<ProgramExpenditure,Long> {
    List<ProgramExpenditure> findByExpenditureType(ExpenditureType expenditureType);
}
