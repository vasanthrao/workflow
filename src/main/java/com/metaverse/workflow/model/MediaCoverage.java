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
@Table(name="media_coverage")
public class MediaCoverage {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="coverage_id")
    private Long coverageId;
    @Column(name="coverage_type")
    private String coverageType;
    @Column(name="images")
    private List<String> images;
    @Column(name="video_url")
    private String videoUrl;
    @Column(name="created_on")
    private Date createdOn;
    @Column(name="updated_on")
    private Date updatedOn;
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
}
