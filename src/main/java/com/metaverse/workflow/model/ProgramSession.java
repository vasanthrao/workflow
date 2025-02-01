package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="program_session")
public class ProgramSession {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="program_session_id")
    private Long programSessionId;
    @Column(name="session_date")
    private Date sessionDate;
    @Column(name="start_time")
    private String startTime;
    @Column(name="end_time")
    private String endTime;
    @Column(name="sessionType_name")
    private String sessionTypeName;
    @Column(name="session_type_methodology")
    private String sessionTypeMethodology;
    @Column(name="session_details")
    private String sessionDetails;
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
    @OneToMany(mappedBy = "programSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramSessionFile> programSessionFileList;
    @Column(name="session_streaming_url")
    private String sessionStreamingUrl;
    @Column(name="coverage_type")
    private String coverageType;
    @Column(name="image1")
    private String image1;
    @Column(name="image2")
    private String image2;
    @Column(name="image3")
    private String image3;
    @Column(name="image4")
    private String image4;
    @Column(name="image5")
    private String image5;
    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;
    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

}
