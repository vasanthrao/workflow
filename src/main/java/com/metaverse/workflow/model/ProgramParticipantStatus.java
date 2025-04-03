package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="program_participant_status")
public class ProgramParticipantStatus {

    @Id
    @Column(name="program_participant_status_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long programParticipantStatusId;

    @Column(name="program_id")
    private Long programId;

    @Column(name="participant_Id")
    private Long participantId;

    @Column(name="participant_verification_id")
    private Integer participantVerificationId;

    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;
    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;
}
