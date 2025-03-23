package com.metaverse.workflow.counsellor.service;

import lombok.*;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CounsellorRegistrationRequest {
    String dateOfRegistration;
    String nameOfCounsellor;
    String dateOfBirth;
    String gender;
    String socialCategory;
    String educationalQualification;
    Integer districtId;
    Integer mandalId;
    String village;
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
    String dateOfSelection;



}
