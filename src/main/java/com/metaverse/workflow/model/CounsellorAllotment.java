package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
@Entity
@Table(name="counsellor_allotment")
public class CounsellorAllotment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="counsellor_allotment_id")
    private Long counsellorAllotmentId;


    @ManyToOne
    @JoinColumn(name="counsellor_registration_id", referencedColumnName="counsellor_registration_id")
    private CounsellorRegistration counsellorRegistration;


    @ManyToOne
    @JoinColumn(name="allotted_district_id", referencedColumnName="district_Id")
    private District allotedDistrict;


    @ManyToOne
    @JoinColumn(name="allotted_mandal_id", referencedColumnName="mandal_Id")
    private Mandal allotedMandal;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @Column(name="dateOfSelection")
    Date dateOfSelection;
}
