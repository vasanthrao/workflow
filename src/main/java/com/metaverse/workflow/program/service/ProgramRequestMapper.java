package com.metaverse.workflow.program.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.Program;
import com.metaverse.workflow.model.ProgramSession;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProgramRequestMapper {
    public static Program map(ProgramRequest programRequest) {
        return Program.builder()
                .activityId(programRequest.getActivityId())
                .subActivityId(programRequest.getSubActivityId())
                .programType(programRequest.getProgramType())
                .programDetails(programRequest.getProgramDetails())
                .programTitle(programRequest.getProgramTitle())
                .agencyId(programRequest.getAgencyId())
                .startDate(DateUtil.stringToDate(programRequest.getStartDate(), "dd-mm-yyyy"))
                .startTime(programRequest.getStartTime())
                .endTime(programRequest.getEndTime())
                .spocName(programRequest.getSpocName())
                .spocContactNo(programRequest.getSpocContactNo())
                .programLocation(programRequest.getProgramLocation())
                .kpi(programRequest.getKpi())
                .createdOn(programRequest.getCreatedOn())
                .updatedOn(programRequest.getUpdatedOn())
                .programSessionList(getProgramSessions(programRequest.getProgramSessionList()))
                .mediaCoverageList(null)
                .build();
    }

    private static List<ProgramSession> getProgramSessions(List<ProgramRequest.ProgramSession> sessions) {
        return sessions.stream().map(session -> ProgramSession.builder()
                        .sessionDate(DateUtil.stringToDate(session.getSessionDate(), "dd-mm-yyyy"))
                        .startTime(session.getStartTime())
                        .endTime(session.getEndTime())
                        .sessionTypeName(session.getSessionTypeName())
                        .sessionTypeMethodology(session.getSessionTypeMethodology())
                        .resourceId(session.getResourceId())
                        .meterialType(session.getMeterialType())
                        .uploaFile(session.getUploaFile())
                        .coverageType(session.getCoverageType())
                        .videoUrl(session.getVideoUrl())
                        .image1(session.getImage1())
                        .image2(session.getImage2())
                        .image3(session.getImage3())
                        .image4(session.getImage4())
                        .image5(session.getImage5())
                        .createdOn(session.getCreatedOn())
                        .updatedOn(session.getUpdatedOn())
                        .build())
                .collect(Collectors.toList());
    }
}
