package com.metaverse.workflow.participant.repository;

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
@Table(name="participant")
public class ParticipantEntity {
    @Id
    @Column(name="participant_id")
    private Long participantId;
    private String participantName;
    private Character gender;
    private String category;
    private String disability;
    private String videoUrl;
    private Long aadhar_no;
    private Long mobileNo;
    private String email;
    private String designation;
    private Character isParticipatedBefore;
    private String participationDetails;

}
