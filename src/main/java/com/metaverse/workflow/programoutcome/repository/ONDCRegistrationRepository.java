package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.ONDCRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ONDCRegistrationRepository extends JpaRepository<ONDCRegistration, Long> {
    @Query("SELECT DISTINCT ondc FROM ONDCRegistration ondc JOIN ondc.participant p WHERE p.participantId = :participantId")
    List<ONDCRegistration> findByParticipantId(Long participantId);

    @Query("SELECT COUNT(r) FROM ONDCRegistration r " +
            "WHERE r.agency.agencyId = :agencyId " +
            "AND r.ondcRegistrationDate BETWEEN :startDate AND :endDate")
    long countByAgencyAndDateBetween(@Param("agencyId") Long agencyId,
                                     @Param("startDate") Date startDate,
                                     @Param("endDate") Date endDate);

    boolean existsByParticipant_ParticipantId(Long participantId);


        @Query("SELECT YEAR(o.createdOn), COUNT(o) " +
                "FROM ONDCRegistration o " +
                "WHERE o.participant.participantId = :participantId " +
                "GROUP BY YEAR(o.createdOn) " +
                "ORDER BY YEAR(o.createdOn)")
        List<Object[]> countRegistrationsByYearAndParticipant(@Param("participantId") Long participantId);
}
