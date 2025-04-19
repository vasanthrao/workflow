package com.metaverse.workflow.expenditure.repository;

import com.metaverse.workflow.model.BulkExpenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BulkExpenditureRepository extends JpaRepository<BulkExpenditure,Long> {
}
