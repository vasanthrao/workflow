package com.metaverse.workflow.callcenter.repository;

import com.metaverse.workflow.model.CallCenterVerification;
import com.metaverse.workflow.model.CallCenterVerificationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CallCenterVerificationRepository extends JpaRepository<CallCenterVerification, CallCenterVerificationId> {
    Optional<CallCenterVerification> findByProgramIdAndParticipantId(Long programId, Long participantId);


}
