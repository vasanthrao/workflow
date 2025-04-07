package com.metaverse.workflow.program.service;

import com.metaverse.workflow.common.util.CommonUtil;
import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.*;
import com.metaverse.workflow.participant.service.ParticipantResponse;
import com.metaverse.workflow.participant.service.ParticipantResponseMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProgramResponseMapper {

    public static ProgramResponse map(Program program) {
        return ProgramResponse.builder()
                .programId(program.getProgramId())
                .activityId(program.getActivityId())
                .subActivityId(program.getSubActivityId())
                .activityName(CommonUtil.activityMap.get(program.getActivityId()))
                .subActivityName(CommonUtil.subActivityMap.get(program.getSubActivityId()))
                .programType(program.getProgramType())
                .programDetails(program.getProgramDetails())
                .programTitle(program.getProgramTitle())
                .agencyId(program.getAgency().getAgencyId())
                .agencyName(program.getAgency().getAgencyName())
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
                .resourceName(session.getResource().getName())
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
                .activityName(CommonUtil.activityMap.get(program.getActivityId()))
                .subActivityName(CommonUtil.subActivityMap.get(program.getSubActivityId()))
                .programType(program.getProgramType())
                .programDetails(program.getProgramDetails())
                .programTitle(program.getProgramTitle())
                .agencyId(program.getAgency().getAgencyId())
                .agencyName(program.getAgency().getAgencyName())
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

    public static List<ParticipantVerificationResponse> mapProgramParticipantVerification(List<Participant> participantList, List<CallCenterVerification> callCenterVerificationList) {
        List<ParticipantVerificationResponse> responseList = new ArrayList<>();
        final Map<Long, CallCenterVerification> verificationMap = callCenterVerificationList != null && callCenterVerificationList.size() > 0 ? callCenterVerificationList.stream().collect(Collectors.toMap(callCenterVerification -> callCenterVerification.getParticipantId(), callCenterVerification -> callCenterVerification)) : new HashMap<>();
        if(participantList != null && participantList.size() > 0) {
            responseList = participantList.stream().map(participant ->
                ParticipantVerificationResponse
                        .builder()
                        .participantId(participant.getParticipantId())
                        .email(participant.getEmail())
                        .ccVerificationStatusId(verificationMap.get(participant.getParticipantId()) != null ? verificationMap.get(participant.getParticipantId()).getCcVerificationStatus().getCcVerificationStatusId() : null)
                        .ccVerificationStatus(verificationMap.get(participant.getParticipantId()) != null ? verificationMap.get(participant.getParticipantId()).getCcVerificationStatus().getVerificationDetails() : null)
                        .verifiedBy(verificationMap.get(participant.getParticipantId()) != null ? verificationMap.get(participant.getParticipantId()).getVerifiedBy().getUserId() : null)
                        .aadharNo(participant.getAadharNo())
                        .mobileNo(participant.getMobileNo())
                        .participantName(participant.getParticipantName())
                        .organizationName(participant.getOrganization() != null ? participant.getOrganization().getOrganizationName() : null)
                        .gender(participant.getGender())
                        .designation(participant.getDesignation())
                        .disability(participant.getDisability())
                        .memberId(participant.getMemberId())
                        .build()
            ).collect(Collectors.toList());
        }
        return responseList;
    }



}
