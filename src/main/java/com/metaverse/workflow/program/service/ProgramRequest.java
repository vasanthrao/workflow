package com.metaverse.workflow.program.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ProgramRequest {

    private Long activityId;
    private Long subActivityId;
    private String programType;
    private String programDetails;
    private String programTitle;
    private Long agencyId;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String spocName;
    private Long spocContactNo;
    private Long locationId;
    private String kpi;
    private List<ProgramSession> programSessionList;
    private List<MediaCoverage> mediaCoverageList;

    @Builder
    @AllArgsConstructor
    @Getter
    @Setter
    public static class ProgramSession {
        private String sessionDate;
        private String startTime;
        private String endTime;
        private String sessionTypeName;
        private String sessionTypeMethodology;
        private String sessionDetails;
        private Long resourceId;
        /*private List<MultipartFile> uploaFiles;
        private List<String> uploaFilePaths;*/
        private List<String> videoUrls;
        private String image1;
        private String image2;
        private String image3;
        private String image4;
        private String image5;
        //Video url of live streaming of session in youtube channel
        private String sessionStreamingUrl;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    @Setter
    public static class MediaCoverage {
        private String coverageType;
        private List<String> images;
        private String videoUrl;
    }

}
