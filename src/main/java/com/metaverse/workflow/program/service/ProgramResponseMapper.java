package com.metaverse.workflow.program.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.Program;
import com.metaverse.workflow.model.ProgramSession;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProgramResponseMapper {

    public static ProgramResponse map(Program program) {
        return ProgramResponse.builder()
                .programId(program.getProgramId())
                .activityId(program.getActivityId())
                .subActivityId(program.getSubActivityId())
                .programType(program.getProgramType())
                .programDetails(program.getProgramDetails())
                .programTitle(program.getProgramTitle())
                .agencyId(program.getAgencyId())
                .startDate(DateUtil.dateToString(program.getStartDate(), "dd-mm-yyyy"))
                .startTime(program.getStartTime())
                .endTime(program.getEndTime())
                .spocName(program.getSpocName())
                .spocContactNo(program.getSpocContactNo())
                .programLocation(program.getProgramLocation())
                .kpi(program.getKpi())
                .createdOn(program.getCreatedOn())
                .updatedOn(program.getUpdatedOn())
                .programSessionList(getProgramSessions(program.getProgramSessionList()))
                .mediaCoverageList(null)
                .build();
    }

    private static List<ProgramResponse.ProgramSession> getProgramSessions(List<ProgramSession> sessions) {
        return sessions.stream().map(session -> ProgramResponse.ProgramSession.builder()
                        .sessionId(session.getProgramSessionId())
                        .sessionDate(DateUtil.dateToString(session.getSessionDate(), "dd-mm-yyyy"))
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
