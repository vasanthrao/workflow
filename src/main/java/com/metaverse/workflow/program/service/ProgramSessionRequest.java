package com.metaverse.workflow.program.service;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProgramSessionRequest {
    private Long programId;
    private Long programSessionId;
    private String sessionDate;
    private String startTime;
    private String endTime;
    private String sessionTypeName;
    private String sessionTypeMethodology;
    private String sessionDetails;
    private Long resourceId;
    private List<String> videoUrls;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String sessionStreamingUrl;
}
