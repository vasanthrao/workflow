package com.metaverse.workflow.expenditure.repository;

import com.metaverse.workflow.model.BulkExpenditureTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BulkExpenditureTransactionRepository extends JpaRepository<BulkExpenditureTransaction,Integer> {
}
