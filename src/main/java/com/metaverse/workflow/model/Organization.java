package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="organization")
public class Organization {
    @Id
    @Column(name="organization_id")
    private Long organizationId;
    @Column(name="organization_type")
    private String organizationType;
    @Column(name="organization_category")
    private String organizationCategory;
    @Column(name="organization_name")
    private String organizationName;
    @Column(name="business_sector")
    private String businessSector;
    @Column(name="is_udyam_registration_enable")
    private Character isUdyamRegistrationEnable;
    @Column(name="registration_no")
    private String registrationNo;
    @Column(name="year_of_registration")
    private Date yearOfRegistration;
    @Column(name="state_id")
    private String stateId;
    @Column(name="dist_id")
    private String distId;
    @Column(name="mandal")
    private String mandal;
    @Column(name="town")
    private String town;
    @Column(name="street_or_locality")
    private String streetOrLocality;
    @Column(name="address")
    private String address;
    @Column(name="latitude")
    private Double latitude;
    @Column(name="longitude")
    private Double longitude;
    @Column(name="contact_no")
    private Long contactNo;
    @Column(name="email")
    private String email;
    @Column(name="website")
    private String website;
    @Column(name="owner_name")
    private String ownerName;
    @Column(name="owner_contact_no")
    private Long ownerContactNo;
    @Column(name="owner_email")
    private String ownerEmail;
    @Column(name="owner_address")
    private String ownerAddress;
    @Column(name="name_of_the_mandal_samakya")
    private String nameOfTheMandalSamakya;
    @Column(name="name_of_the_VO")
    private String nameOfTheVO;
    @Column(name="grama_panchayat")
    private String gramaPanchayat;
    @Column(name="bank_ac_no")
    private String bankAcNo;
    @Column(name="bank_name")
    private String bankName;
    @Column(name="ifsc_code")
    private String ifscCode;
    @Column(name="branch_name")
    private String branchName;
    @Column(name="startup_certificate_no")
    private String startupCertificateNo;
    @Column(name="nature_of_startup")
    private String natureOfStartup;
    @Column(name="areas_of_working")
    private String areasOfWorking;
    @Column(name="date_of_issue")
    private String dateOfIssue;
    @Column(name="valid_upto")
    private String validUpto;
    @Column(name="created_on")
    private Date createdOn;
    @Column(name="updated_on")
    private Date updatedOn;
}
