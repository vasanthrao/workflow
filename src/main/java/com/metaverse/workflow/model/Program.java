package com.metaverse.workflow.model;

import jakarta.persistence.*;
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
public class Program {

    @Id
    @GeneratedValue
    @Column(name="program_id")
    private Long programId;
    @Column(name="program_title")
    private String programTitle;
    @Column(name="program_type")
    private String programType;
    @Column(name="program_details")
    private String programDetails;
    @Column(name="agency_id")
    private Long agencyId;
    @Column(name="activity_id")
    private Long activityId;
    @Column(name="sub_activity_id")
    private Long subActivityId;
    @Column(name="kpi")
    private String kpi;
    @Column(name="start_date")
    private Date startDate;
    @Column(name="start_time")
    private String startTime;
    @Column(name="end_time")
    private String endTime;
    @Column(name="program_location")
    private Long programLocation;
    @Column(name="spoc_name")
    private String spocName;
    @Column(name="spoc_contact_no")
    private Long spocContactNo;
    @Column(name="created_on")
    private Date createdOn;
    @Column(name="updated_on")
    private Date updatedOn;
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramSession> programSessionList;
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MediaCoverage> mediaCoverageList;

}
