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
    Long counsellerRegistationId;
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
    Integer allocatedDistrictId;
    Integer allocatedMandalId;
}
