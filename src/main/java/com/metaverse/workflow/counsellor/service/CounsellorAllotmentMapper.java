package com.metaverse.workflow.counsellor.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.CounsellorAllotment;
import com.metaverse.workflow.model.CounsellorRegistration;

public class CounsellorAllotmentMapper {

    public static CounsellorAllotment map(CounsellorRegistration counsellorRegistration,CounsllorRegistrationRequest  counsellorRequest)
    {
        return CounsellorAllotment.builder().counsellorRegistration(counsellorRegistration)
                .dateOfSelection(counsellorRegistration.getDateOfSelection())
                .allotedDistrict(counsellorRegistration.getAllotedDistrict())
                .allotedMandal(counsellorRegistration.getAllotedMandal())
                .startDate(DateUtil.stringToDate(counsellorRequest.startDate,"dd-mm-yyyy"))
                .endDate(DateUtil.stringToDate(counsellorRequest.endDate,"dd-mm-yyyy"))
                .build();
    }
}
