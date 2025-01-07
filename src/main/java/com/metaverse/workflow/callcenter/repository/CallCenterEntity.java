package com.metaverse.workflow.callcenter.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="call_center")
public class CallCenterEntity {
    @Id
    @Column(name="call_center_id")
    private Long callCenterId;
    private Long programId;
    private Long participantId;
    private String issue;
    private String reason;
    private String status;
}
