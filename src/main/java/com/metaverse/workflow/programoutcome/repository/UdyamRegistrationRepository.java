package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.UdyamRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UdyamRegistrationRepository extends JpaRepository<UdyamRegistration,Long> {
    boolean existsByParticipant_ParticipantId(Long participantId);

    List<UdyamRegistration> findByParticipantParticipantId(Long participantId);
}
