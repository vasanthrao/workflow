package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.ONDCRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ONDCRegistrationRepository extends JpaRepository<ONDCRegistration, Long> {
    @Query("SELECT DISTINCT ondc FROM ONDCRegistration ondc JOIN ondc.participant p WHERE p.participantId = :participantId")
    List<ONDCRegistration> findByParticipantId(Long participantId);
    boolean existsByParticipant_ParticipantId(Long participantId);

}
