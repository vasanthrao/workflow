package com.metaverse.workflow.programattendance.service;

import com.metaverse.workflow.common.response.WorkflowResponse;

public interface ProgramAttendanceService {
    WorkflowResponse attendanceByProgramId(Long programId);
    WorkflowResponse updateProgramAttendance(ProgramAttendanceRequest request);
}
