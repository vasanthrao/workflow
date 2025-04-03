package com.metaverse.workflow.callcenter.repository;

import com.metaverse.workflow.model.CallCenterVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantVerificationRepository extends JpaRepository<CallCenterVerification,Long> {


}
