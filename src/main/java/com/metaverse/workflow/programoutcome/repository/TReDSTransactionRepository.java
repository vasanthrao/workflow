package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.TReDSTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TReDSTransactionRepository extends JpaRepository<TReDSTransaction,Long> {
}
