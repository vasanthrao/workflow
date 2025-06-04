package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.TReDSTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface TReDSTransactionRepository extends JpaRepository<TReDSTransaction,Long> {
    @Query("SELECT COUNT(t) FROM TReDSTransaction t WHERE t.tredsRegistration.agency.agencyId = :agencyId AND t.tredsTransactionDate BETWEEN :startDate AND :endDate")
    long countByAgencyAndTransactionDateBetween(@Param("agencyId") Long agencyId,
                                                @Param("startDate") Date startDate,
                                                @Param("endDate") Date endDate);
}
