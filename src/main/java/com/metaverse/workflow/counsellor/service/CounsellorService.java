package com.metaverse.workflow.counsellor.service;

import com.metaverse.workflow.common.response.WorkflowResponse;

public interface CounsellorService {
   WorkflowResponse saveCounseller(CounsellorRegistrationRequest counsellorRequest);
    CounsellorRegistrationResponse getCounsellerByMandal(String mandal);
}
