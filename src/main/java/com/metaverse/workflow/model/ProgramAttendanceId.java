package com.metaverse.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Embeddable
public class ProgramAttendanceId {

    @Column(name = "program_id", nullable = false)
    private Long programId;
    @Column(name = "participant_id", nullable = false)
    private Long participantId;

}
