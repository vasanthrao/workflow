package com.metaverse.workflow.counsellor.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.CounsellorRegistration;
import org.springframework.stereotype.Component;

@Component
public class CounsellorRegistrationResponseMapper {
    public static CounsellorRegistrationResponse map(CounsellorRegistration counsellorRegistration)
    {
        return CounsellorRegistrationResponse.builder().counsellerRegistationId(counsellorRegistration.getCounsellorRegistrationId())
                .dateOfRegistation(DateUtil.dateToString(counsellorRegistration.getDateOfRegistration(), "dd-mm-yyyy"))
                .nameOfCounseller(counsellorRegistration.getNameOfCounsellor())
                .dateOfBirth(DateUtil.dateToString(counsellorRegistration.getDateOfBirth(), "dd-mm-yyyy"))
                .gender(counsellorRegistration.getGender())
                .socialCategory((counsellorRegistration.getSocialCategory()))
                .educatiuonalQulification(counsellorRegistration.getEducationalQualification())
                .districtId(counsellorRegistration.getDistrict().getDistrictId())
                .mandalId(counsellorRegistration.getMandal().getMandalId())
                .vilage(counsellorRegistration.getVillage())
                .houseNo(counsellorRegistration.getHouseNo())
                .streetName(counsellorRegistration.getStreetName())
                .pincode(counsellorRegistration.getPincode())
                .landmark(counsellorRegistration.getLandmark())
                .expriance(counsellorRegistration.getExperience())
                .designation(counsellorRegistration.getDesignation())
                .specialzation(counsellorRegistration.getSpecialization())
                .contactNo(counsellorRegistration.getContactNo())
                .altContactNo(counsellorRegistration.getAltContactNo())
                .emailId(counsellorRegistration.getEmailId())
                 .build();
    }
}
