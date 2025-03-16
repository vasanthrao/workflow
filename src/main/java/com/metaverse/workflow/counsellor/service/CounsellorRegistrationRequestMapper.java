package com.metaverse.workflow.counsellor.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.CounsellorRegistration;

public class CounsellorRegistrationRequestMapper {
    public static CounsellorRegistration map(CounsllorRegistrationRequest counsllorRequest)
    {
        return CounsellorRegistration.builder()
                .dateOfRegistration(DateUtil.stringToDate(counsllorRequest.getDateOfRegistation(),"dd-mm-yyyy"))
                .nameOfCounsellor(counsllorRequest.getNameOfCounseller())
                .dateOfBirth(DateUtil.stringToDate(counsllorRequest.getDateOfBirth(),"dd-mm-yyyy"))
                .gender(counsllorRequest.getGender())
                .socialCategory((counsllorRequest.getSocialCategory()))
                .educationalQualification(counsllorRequest.getEducatiuonalQulification()).village(counsllorRequest.getVilage())
                .houseNo(counsllorRequest.getHouseNo())
                .streetName(counsllorRequest.getStreetName())
                .pincode(counsllorRequest.getPincode())
                .landmark(counsllorRequest.getLandmark())
                .experience(counsllorRequest.getExpriance())
                .designation(counsllorRequest.getDesignation())
                .specialization(counsllorRequest.getSpecialzation())
                .contactNo(counsllorRequest.getContactNo())
                .altContactNo(counsllorRequest.getAltContactNo())
                .emailId(counsllorRequest.getEmailId())
                .build();
    }
}
