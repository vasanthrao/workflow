package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.GeMTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeMTransactionRepository extends JpaRepository<GeMTransaction,Long> {
}
