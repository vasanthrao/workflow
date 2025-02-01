package com.metaverse.workflow.program.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProgramSessionResponse {
    private Long sessionId;
    private String sessionDate;
    private String startTime;
    private String endTime;
    private String sessionTypeName;
    private String sessionTypeMethodology;
    private String sessionDetails;
    private Long resourceId;
    private Long programId;
    private List<ProgramSessionFile> files;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private String sessionStreamingUrl;
    private Date createdOn;
    private Date updatedOn;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ProgramSessionFile {
        private Long programSessionFileId;
        private String fileType;
        private String filePath;
    }
}
