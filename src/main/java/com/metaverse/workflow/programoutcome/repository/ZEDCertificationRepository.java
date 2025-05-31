package com.metaverse.workflow.programoutcome.repository;

import com.metaverse.workflow.model.outcomes.ZEDCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZEDCertificationRepository extends JpaRepository<ZEDCertification,Long> {
    @Query("SELECT DISTINCT zed FROM ZEDCertification zed JOIN zed.participant p WHERE p.participantId = :participantId")
    List<ZEDCertification> findByParticipantId(Long participantId);
}
