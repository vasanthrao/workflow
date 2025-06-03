package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.ONDCRegistration;
import com.metaverse.workflow.model.outcomes.TReDSRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TReDSRegistrationRepository extends JpaRepository<TReDSRegistration,Integer> {
    @Query("SELECT DISTINCT treds FROM TReDSRegistration treds JOIN treds.participant p WHERE p.participantId = :participantId")
    List<TReDSRegistration> findByParticipantId(Long participantId);

    boolean existsByParticipant_ParticipantId(Long participantId);


}
