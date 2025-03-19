package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.ONDCTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ONDCTransactionRepository extends JpaRepository<ONDCTransaction, Long> {
}
