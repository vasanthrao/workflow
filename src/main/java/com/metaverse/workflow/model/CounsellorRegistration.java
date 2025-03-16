package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Setter
@Getter
@Entity
@Table(name="counsellor_registration")
public class CounsellorRegistration {

    @Id
    @Column(name="counsellorRegistrationId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long counsellorRegistrationId;

    @Column(name="dateOfRegistration")
    private Date dateOfRegistration;

    @Column(name="nameOfCounsellor")
    private String nameOfCounsellor;

    @Column(name="dateOfBirth")
    private Date dateOfBirth;

    @Column(name="gender")
    private String gender;

    @Column(name="socialCategory")
    private String socialCategory;

    @Column(name="educationalQualification")
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
    LocalDate dateOfSelection;
}
