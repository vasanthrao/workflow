package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
@Table(name="counsellor_registration")
public class CounsellorRegistration {

    @Id
    @Column(name="counsellor_registration_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long counsellorRegistrationId;

    @Column(name="dateOf_registration")
    private Date dateOfRegistration;

    @Column(name="nameOf_counsellor")
    private String nameOfCounsellor;

    @Column(name="dateOfBirth")
    private Date dateOfBirth;

    @Column(name="gender")
    private String gender;

    @Column(name="socialcategory")
    private String socialCategory;

    @Column(name="educational_qualification")
    private String educationalQualification;


    @ManyToOne
    @JoinColumn(name="district_id", referencedColumnName="district_Id")
    private District district;


    @ManyToOne
    @JoinColumn(name="allotted_district_id", referencedColumnName="district_Id")
    private District allotedDistrict;


    @ManyToOne
    @JoinColumn(name="mandal_id", referencedColumnName="mandal_Id")
    private Mandal mandal;

    @ManyToOne
    @JoinColumn(name="allotted_mandal_id", referencedColumnName="mandal_Id")
    private Mandal allotedMandal;

    @Column(name="village")
    private String village;

    @Column(name="houseNo")
    private String houseNo;

    @Column(name="streetname")
    private String streetName;

    @Column(name="pincode")
    private Integer pincode;

    @Column(name="landmark")
    private String landmark;

    @Column(name="experience")
    private Integer experience;

    @Column(name="designation")
    private String designation;

    @Column(name="specialization")
    private String specialization;

    @Column(name="contactNo")
    private Long contactNo;

    @Column(name="altContactNo")
    private Long altContactNo;

    @Column(name="emailId")
    private String emailId;

    @Column(name="dateOfSelection")
    Date dateOfSelection;

    @OneToMany(mappedBy = "counsellorRegistration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CounsellorAllotment> counsellorAllotments ;
}
