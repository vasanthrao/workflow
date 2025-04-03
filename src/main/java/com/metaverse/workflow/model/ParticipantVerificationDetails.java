package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="participant_verification_Details")
@Entity
public class ParticipantVerificationDetails {

    @EmbeddedId
    private ParticipantVerificationId participantVerificationId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cc_verification_id", referencedColumnName = "cc_verification_id")
    private CallCenterVerification callCenterVerification;

    @Column(name="program_attendance_data")
    private String programAttendanceData;

    @Column(name="program_raw_material")
    private Character programRawMaterial;

    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;

}
