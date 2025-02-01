package com.metaverse.workflow.program.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.Participant;
import com.metaverse.workflow.model.Program;
import com.metaverse.workflow.model.ProgramSession;
import com.metaverse.workflow.model.ProgramSessionFile;
import com.metaverse.workflow.participant.service.ParticipantResponse;
import com.metaverse.workflow.participant.service.ParticipantResponseMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
                .agencyId(program.getAgency().getAgencyId())
                .startDate(DateUtil.dateToString(program.getStartDate(), "dd-mm-yyyy"))
                .endDate(DateUtil.dateToString(program.getEndDate(), "dd-mm-yyyy"))
                .startTime(program.getStartTime())
                .endTime(program.getEndTime())
                .spocName(program.getSpocName())
                .spocContactNo(program.getSpocContactNo())
                .programLocation(program.getLocation().getLocationId())
                .kpi(program.getKpi())
                .createdOn(program.getCreatedOn())
                .updatedOn(program.getUpdatedOn())
                .build();
    }

    public static ProgramSessionResponse mapSession(ProgramSession session, List<ProgramSessionFile> sessionFiles) {
        return ProgramSessionResponse.builder()
                .sessionId(session.getProgramSessionId())
                .sessionDate(DateUtil.dateToString(session.getSessionDate(), "dd-mm-yyyy"))
                .startTime(session.getStartTime())
                .endTime(session.getEndTime())
                .sessionTypeName(session.getSessionTypeName())
                .sessionTypeMethodology(session.getSessionTypeMethodology())
                .resourceId(session.getResource().getResourceId())
                .programId(session.getProgram().getProgramId())
                .sessionStreamingUrl(session.getSessionStreamingUrl())
                .files(mapSessionFile(sessionFiles))
                .image1(session.getImage1())
                .image2(session.getImage2())
                .image3(session.getImage3())
                .image4(session.getImage4())
                .image5(session.getImage5())
                .createdOn(session.getCreatedOn())
                .updatedOn(session.getUpdatedOn())
                .build();
    }

    public static ProgramResponse mapProgram(Program program) {
        return ProgramResponse.builder()
                .programId(program.getProgramId())
                .activityId(program.getActivityId())
                .subActivityId(program.getSubActivityId())
                .programType(program.getProgramType())
                .programDetails(program.getProgramDetails())
                .programTitle(program.getProgramTitle())
                .agencyId(program.getAgency().getAgencyId())
                .startDate(DateUtil.dateToString(program.getStartDate(), "dd-mm-yyyy"))
                .endDate(DateUtil.dateToString(program.getEndDate(), "dd-mm-yyyy"))
                .startTime(program.getStartTime())
                .endTime(program.getEndTime())
                .spocName(program.getSpocName())
                .spocContactNo(program.getSpocContactNo())
                .programLocation(program.getLocation().getLocationId())
                .kpi(program.getKpi())
                .createdOn(program.getCreatedOn())
                .updatedOn(program.getUpdatedOn())
                .programSessionList(program.getProgramSessionList().stream().map(session -> mapSession(session, session.getProgramSessionFileList())).collect(Collectors.toList()) )
                .build();
    }

    public static List<ProgramSessionResponse.ProgramSessionFile> mapSessionFile(List<ProgramSessionFile> programSessionFileList) {
        List<ProgramSessionResponse.ProgramSessionFile> files = new ArrayList<>();
        return programSessionFileList != null ? programSessionFileList.stream().map(file ->
                        ProgramSessionResponse.ProgramSessionFile.builder()
                                .programSessionFileId(file.getProgramSessionFileId())
                                .fileType(file.getFileType())
                                .filePath(file.getFilePath())
                                .build())
                .collect(Collectors.toList()) : files;
    }

    private static List<ProgramResponse.ProgramSession> getProgramSessions(List<ProgramSession> sessions) {
        return sessions.stream().map(session -> ProgramResponse.ProgramSession.builder()
                        .sessionId(session.getProgramSessionId())
                        .sessionDate(DateUtil.dateToString(session.getSessionDate(), "dd-mm-yyyy"))
                        .startTime(session.getStartTime())
                        .endTime(session.getEndTime())
                        .sessionTypeName(session.getSessionTypeName())
                        .sessionTypeMethodology(session.getSessionTypeMethodology())
                        .sessionStreamingUrl(session.getSessionStreamingUrl())
                        .coverageType(session.getCoverageType())
                        .videoUrls(session.getProgramSessionFileList().stream().map(file -> file.getFilePath()).collect(Collectors.toList()))
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

    public static List<ParticipantResponse> mapProgramParticipants(List<Participant> participantList) {
        return participantList != null ? participantList.stream().map(participant ->
                        ParticipantResponseMapper.map(participant))
                .collect(Collectors.toList()) : null;
    }



}
