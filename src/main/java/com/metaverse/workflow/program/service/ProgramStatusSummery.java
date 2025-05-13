package com.metaverse.workflow.program.service;

import lombok.Builder;
import lombok.Getter;

@Builder
public class ProgramStatusSummery {
    private Integer programScheduled;
    private Integer programsCompleted;
    private Integer programsOverdue;
    private Integer programInProcess;
    private Integer programDue;
}
