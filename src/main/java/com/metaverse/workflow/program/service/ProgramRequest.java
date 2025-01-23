package com.metaverse.workflow.program.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class ProgramRequest {

    private Long activityId;
    private Long subActivityId;
    private String programType;
    private String programDetails;
    private String programTitle;
    private Long agencyId;
    private String startDate;
    private String startTime;
    private String endTime;
    private String spocName;
    private Long spocContactNo;
    private Long programLocation;
    private String kpi;
    private Date createdOn;
    private Date updatedOn;
    private List<ProgramSession> programSessionList;
    private List<MediaCoverage> mediaCoverageList;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class ProgramSession {
        private String sessionDate;
        private String startTime;
        private String endTime;
        private String sessionTypeName;
        private String sessionTypeMethodology;
        private String sessionDetails;
        private Long resourceId;
        private String meterialType;
        private String uploaFile;
        private String coverageType;
        private String videoUrl;
        private String image1;
        private String image2;
        private String image3;
        private String image4;
        private String image5;
        private Date createdOn;
        private Date updatedOn;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class MediaCoverage {
        private String coverageType;
        private List<String> images;
        private String videoUrl;
        private Date createdOn;
        private Date updatedOn;
    }

}
