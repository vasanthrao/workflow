package com.metaverse.workflow.program.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramResponse {

    private Long programId;
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
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProgramSession {
        private Long sessionId;
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
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MediaCoverage {
        private Long coverageId;
        private String coverageType;
        private List<String> images;
        private String videoUrl;
        private Date createdOn;
        private Date updatedOn;
    }

}
