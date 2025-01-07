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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="program")
public class ProgramSessionEntity {
    @Id
    @Column(name="program_session_id")
    private Long programSessionId;
    @Column(name="start_time")
    private Date startTime;
    @Column(name="end_time")
    private Date endTime;
    @Column(name="sessionType_name")
    private String sessionTypeName;
    @Column(name="session_type_methodology")
    private String sessionTypeMethodology;
    @Column(name="session_details")
    private String sessionDetails;
    @Column(name="resource_id")
    private Long resourceId;
    @Column(name="meterial_type")
    private String meterialType;
    @Column(name="upload_file")
    private String uploaFile;
    @Column(name="coverage_type")
    private String coverageType;
    @Column(name="video_url")
    private String videoUrl;
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
}
