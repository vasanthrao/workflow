package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
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
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="program_id")
    private Long programId;
    @Column(name="program_title")
    private String programTitle;
    @Column(name="program_type")
    private String programType;
    @Column(name="program_details")
    private String programDetails;
    @Column(name="activity_id")
    private Long activityId;
    @Column(name="sub_activity_id")
    private Long subActivityId;
    @Column(name="kpi")
    private String kpi;
    @Column(name="start_date")
    private Date startDate;
    @Column(name="end_date")
    private Date endDate;
    @Column(name="start_time")
    private String startTime;
    @Column(name="end_time")
    private String endTime;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @Column(name="spoc_name")
    private String spocName;
    @Column(name="spoc_contact_no")
    private Long spocContactNo;
    @Column(name="status")
    private String status;
    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;
    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramSession> programSessionList;
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MediaCoverage> mediaCoverageList;

    @ManyToMany(cascade = CascadeType.ALL,targetEntity = Participant.class,mappedBy = "programs")
    private List<Participant> participants = new ArrayList<>();

}
