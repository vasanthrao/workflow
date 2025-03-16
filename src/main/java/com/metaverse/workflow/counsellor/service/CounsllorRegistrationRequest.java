package com.metaverse.workflow.counsellor.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CounsllorRegistrationRequest {
    String dateOfRegistation;
    String nameOfCounseller;
    String dateOfBirth;
    String gender;
    String socialCategory;
    String educatiuonalQulification;
    Integer districtId;
    Integer mandalId;
    String vilage;
    String houseNo;
    String streetName;
    Integer pincode;
    String landmark;
    Integer expriance;
    String designation;
    String specialzation;
    Long contactNo;
    Long altContactNo;
    String emailId;
    Integer allortedDistrictId;
    Integer allortedMandalId;
}
