package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="call_center")
public class CallCenter {
    @Id
    @Column(name="call_center_id")
    private Long callCenterId;
    @Column(name="program_id")
    private Long programId;
    @Column(name="participant_id")
    private Long participantId;
    @Column(name="issue")
    private String issue;
    @Column(name="reason")
    private String reason;
    @Column(name="status")
    private String status;
    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;
    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;
}
