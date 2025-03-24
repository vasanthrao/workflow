package com.metaverse.workflow.counsellor.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CounsellorRegistrationResponse {
    Long counsellorRegistrationId;
    String dateOfRegistration;
    String nameOfCounsellor;
    String dateOfBirth;
    String gender;
    String socialCategory;
    String educationalQualification;
    Integer districtId;
    String districtName;
    Integer mandalId;
    String mandalName;
    String village;
    String houseNo;
    String streetName;
    Integer pincode;
    String landmark;
    Integer experience;
    String designation;
    String specialization;
    Long contactNo;
    Long altContactNo;
    String emailId;
    Integer allocatedDistrictId;
    String allocatedDistrictName;
    Integer allocatedMandalId;
    String allocatedMandalName;
}
