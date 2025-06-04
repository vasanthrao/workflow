package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.ONDCTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ONDCTransactionRepository extends JpaRepository<ONDCTransaction, Long> {
    @Query("SELECT COUNT(t) FROM ONDCTransaction t WHERE t.ondcRegistration.agency.agencyId = :agencyId AND t.transactionDate BETWEEN :startDate AND :endDate")
    long countByAgencyAndTransactionDateBetween(@Param("agencyId") Long agencyId,
                                                @Param("startDate") Date startDate,
                                                @Param("endDate") Date endDate);
}
