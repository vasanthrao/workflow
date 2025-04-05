package com.metaverse.workflow.callcenter.repository;

import com.metaverse.workflow.model.CallCenterVerification;
import com.metaverse.workflow.model.CallCenterVerificationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallCenterVerificationRepository extends JpaRepository<CallCenterVerification, CallCenterVerificationId> {


}
