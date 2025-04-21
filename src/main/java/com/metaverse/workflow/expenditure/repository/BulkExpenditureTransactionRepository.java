package com.metaverse.workflow.expenditure.repository;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.model.BulkExpenditureTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BulkExpenditureTransactionRepository extends JpaRepository<BulkExpenditureTransaction,Long> {

    List<BulkExpenditureTransaction> findByProgram_ProgramId(Long programId);

}
