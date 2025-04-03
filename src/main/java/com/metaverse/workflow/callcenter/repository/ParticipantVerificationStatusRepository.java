package com.metaverse.workflow.callcenter.repository;

import com.metaverse.workflow.model.CallCenterVerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantVerificationStatusRepository extends JpaRepository<CallCenterVerificationStatus,Integer> {
}
