package com.metaverse.workflow.program.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="program")
public class ProgramEntity {

    @Id
    @Column(name="program_id")
    private Long programId;
    @Column(name="program_title")
    private String programTitle;
    @Column(name="agency_id")
    private Long agencyId;
    @Column(name="activity_id")
    private Long activityId;
    @Column(name="sub_activity_id")
    private Long subActivityId;
    @Column(name="start_date")
    private Date startDate;
    @Column(name="endDate")
    private Date endDate;
    @Column(name="start_time")
    private String startTime;
    @Column(name="end_time")
    private String endTime;
    @Column(name="program_location")
    private Long programLocation;
    @Column(name="expected_participants")
    private Integer expectedParticipants;
    @Column(name="spoc_name")
    private String spocName;
    @Column(name="spoc_contact_no")
    private Long spocContactNo;
    private List<ProgramSessionEntity> programSessionEntityList;

}
