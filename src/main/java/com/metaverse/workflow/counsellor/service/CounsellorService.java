package com.metaverse.workflow.counsellor.service;

public interface CounsellorService {
    CounsellorRegistrationResponse saveCounseller(CounsllorRegistrationRequest counsllerRequest);
    CounsellorRegistrationResponse getCounsellerByMandal(String mandal);
}
