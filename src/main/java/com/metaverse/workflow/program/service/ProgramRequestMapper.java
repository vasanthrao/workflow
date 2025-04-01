package com.metaverse.workflow.program.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProgramRequestMapper {
    public static Program map(ProgramRequest programRequest, Agency agency, Location location) {
        return Program.builder()
                .activityId(programRequest.getActivityId())
                .subActivityId(programRequest.getSubActivityId())
                .programType(programRequest.getProgramType())
                .programDetails(programRequest.getProgramDetails())
                .programTitle(programRequest.getProgramTitle())
                .startDate(DateUtil.stringToDate(programRequest.getStartDate(), "dd-mm-yyyy"))
                .endDate(DateUtil.stringToDate(programRequest.getEndDate(), "dd-mm-yyyy"))
                .startTime(programRequest.getStartTime())
                .endTime(programRequest.getEndTime())
                .spocName(programRequest.getSpocName())
                .spocContactNo(programRequest.getSpocContactNo())
                .location(location)
                .kpi(programRequest.getKpi())
                .agency(agency)
                .build();
    }

    public static ProgramSession mapSession(ProgramSessionRequest session, Resource resource, Program program) {
        return ProgramSession.builder()
                .sessionDate(DateUtil.stringToDate(session.getSessionDate(), "dd-mm-yyyy"))
                .startTime(session.getStartTime())
                .endTime(session.getEndTime())
                .sessionTypeName(session.getSessionTypeName())
                .sessionTypeMethodology(session.getSessionTypeMethodology())
                .resource(resource)
                .program(program)
                .sessionDetails(session.getSessionDetails())
                .sessionStreamingUrl(session.getSessionStreamingUrl())
                .image1(session.getImage1())
                .image2(session.getImage2())
                .image3(session.getImage3())
                .image4(session.getImage4())
                .image5(session.getImage5())
                .build();
    }

    private static List<ProgramSession> getProgramSessions(List<ProgramRequest.ProgramSession> sessions) {
        return sessions.stream().map(session -> ProgramSession.builder()
                        .sessionDate(DateUtil.stringToDate(session.getSessionDate(), "dd-mm-yyyy"))
                        .startTime(session.getStartTime())
                        .endTime(session.getEndTime())
                        .sessionTypeName(session.getSessionTypeName())
                        .sessionTypeMethodology(session.getSessionTypeMethodology())
                        //.resourceId(session.getResourceId())
                        .sessionStreamingUrl(session.getSessionStreamingUrl())
                        //.programSessionFileList(session.getVideoUrls() != null ? getProgramSessionFile(session.getVideoUrls()) : null)
                        .image1(session.getImage1())
                        .image2(session.getImage2())
                        .image3(session.getImage3())
                        .image4(session.getImage4())
                        .image5(session.getImage5())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<ProgramSessionFile> mapProgramFiles(List<String> videos, List<String> files){
        List<ProgramSessionFile> programSessionFileList = new ArrayList<>();
        if (files != null && files.size() > 0)
            files.stream().forEach(file -> programSessionFileList.add(ProgramSessionFile.builder().fileType("FILE").filePath(file).build()));
        if (videos != null && videos.size() > 0)
            videos.stream().forEach(file -> programSessionFileList.add(ProgramSessionFile.builder().fileType("VIDEO").filePath(file).build()));
        return programSessionFileList;
    }

    private static List<MediaCoverage> getMediaCoverages(List<ProgramRequest.MediaCoverage> mediaCoverages) {
        return mediaCoverages.stream().map(mediaCoverage -> MediaCoverage.builder()
                        .coverageType(mediaCoverage.getCoverageType())
                        .videoUrl(mediaCoverage.getVideoUrl())
                        .images(mediaCoverage.getImages())
                        .build())
                .collect(Collectors.toList());
    }

    public static Program mapUpdate(ProgramRequest programRequest, Agency agency, Location location, Program exisitingProgram) {

        Program program = exisitingProgram;
        program.setActivityId(programRequest.getActivityId());
        program.setSubActivityId(programRequest.getSubActivityId());
        program.setProgramType(programRequest.getProgramType());
        program.setProgramDetails(programRequest.getProgramDetails());
        program.setProgramTitle(programRequest.getProgramTitle());
        program.setStartTime(programRequest.getStartTime());
        program.setEndTime(programRequest.getEndTime());
        program.setSpocName(programRequest.getSpocName());
        program.setSpocContactNo(programRequest.getSpocContactNo());
        program.setKpi(programRequest.getKpi());
        program.setStartDate(DateUtil.stringToDate(programRequest.getStartDate(), "dd-MM-yyyy"));
        program.setEndDate(DateUtil.stringToDate(programRequest.getEndDate(), "dd-MM-yyyy"));
        program.setAgency(agency);
        program.setLocation(location);
        return program;
    }



}
