package com.metaverse.workflow.programattendance.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.Participant;
import com.metaverse.workflow.model.ProgramAttendance;
import com.metaverse.workflow.model.Program;
import com.metaverse.workflow.program.repository.ProgramRepository;
import com.metaverse.workflow.programattendance.repository.ProgramAttendanceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProgramAttendanceServiceAdapter implements ProgramAttendanceService {

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    ProgramAttendanceRepository programAttendanceRepository;

    @Override
    public WorkflowResponse attendanceByProgramId(Long programId) {
        Optional<Program> program = programRepository.findById(programId);
        if (!program.isPresent())
            return WorkflowResponse.builder().status(400).message("Invalid Program").build();
        if (program.get().getProgramSessionList() == null)
            return WorkflowResponse.builder().status(400).message("No sessions created to program").build();
        Set<String> dateSet = program.get().getProgramSessionList().stream().map(session -> DateUtil.dateToString(session.getSessionDate(), "dd-mm-yyyy")).collect(Collectors.toSet());
        ProgramAttendanceResponse response = populateParticipantAttendace(programId, program.get().getParticipants(), dateSet.size());
        List<ProgramAttendance> list = programAttendanceRepository.findByProgramAttendances(programId);
        if (list == null || list.size() == 0) {
            return WorkflowResponse.builder().status(200).message("Success").data(response).build();
        } else {
            response = updateParticipantAttendances(list, response);
            return WorkflowResponse.builder().status(200).message("Success").data(response).build();
        }
    }

    @Override
    public WorkflowResponse updateProgramAttendance(ProgramAttendanceRequest request) {
        List<ProgramAttendance> attendanceList = ProgramAttendanceRequestMapper.map(request);
        List<ProgramAttendance> response = programAttendanceRepository.saveAll(attendanceList);
        return WorkflowResponse.builder().status(200).message("Success").data(ProgramAttendanceResponseMapper.map(response)).build();

    }

    private ProgramAttendanceResponse populateParticipantAttendace(Long programId, List<Participant> participants, Integer days) {
        List<ProgramAttendanceResponse.ParticipantAttendance> list = participants.stream().map(participant ->
                ProgramAttendanceResponse.ParticipantAttendance.builder()
                        .participantId(participant.getParticipantId())
                        .participantName(participant.getParticipantName())
                        .memberId(participant.getMemberId())
                        .SHGName(participant.getOrganization().getNameOfTheSHG())
                        .mobileNo(participant.getMobileNo())
                        .email(participant.getEmail())
                        .aadharNo(participant.getAadharNo())
                        .designation(participant.getDesignation())
                        .attendanceData(populateAttendaceData(days))
                        .build()).collect(Collectors.toList());
        return ProgramAttendanceResponse.builder().programId(programId).participantAttendanceList(list).build();
    }

    private Character[] populateAttendaceData(Integer days) {
        Character[] charArray = new Character[days];
        for (int i = 0; i < days; i++) {
            charArray[i] = 'A';
        }
        return charArray;
    }

    private ProgramAttendanceResponse updateParticipantAttendances(List<ProgramAttendance> list, ProgramAttendanceResponse response) {
        Map<Long, String> existingDetailsMap = list.stream().collect(Collectors.toMap(details -> details.getProgramAttendanceId().getParticipantId(), details -> details.getProgramAttendanceData()));
        for (ProgramAttendanceResponse.ParticipantAttendance attendance : response.getParticipantAttendanceList()) {
            String attendances = existingDetailsMap.get(attendance.getParticipantId());
            if (attendances != null) {
                Character[] charArray = new Character[attendances.length()];
                for (int i = 0; i < attendances.length(); i++) {
                    charArray[i] = attendances.charAt(i);
                }
                attendance.setAttendanceData(charArray);
            }
        }
        return response;
    }

}
