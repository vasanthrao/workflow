package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.ONDCRegistration;
import com.metaverse.workflow.model.outcomes.TReDSRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TReDSRegistrationRepository extends JpaRepository<TReDSRegistration,Integer> {
    @Query("SELECT DISTINCT treds FROM TReDSRegistration treds JOIN treds.participant p WHERE p.participantId = :participantId")
    List<TReDSRegistration> findByParticipantId(Long participantId);

    @Query("SELECT COUNT(r) FROM TReDSRegistration r WHERE r.agency.agencyId = :agencyId AND r.tredsRegistrationDate BETWEEN :startDate AND :endDate")
    long countByAgencyIdAndTredsRegistrationDateBetween(@Param("agencyId") Long agencyId,
                                                        @Param("startDate") Date startDate,
                                                        @Param("endDate") Date endDate);}
