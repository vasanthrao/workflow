package com.metaverse.workflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    private CallCenterVerification callCenterVerification;

    @Column(name="program_attendance_data")
    private String programAttendanceData;

    @Column(name="program_raw_material")
    private String programRawMaterial;

    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;

}
