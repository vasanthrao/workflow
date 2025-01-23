package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name="participant")
public class Participant {
    @Id
    @Column(name="participant_id")
    private Long participantId;
    @Column(name="organization_id")
    private String organizationId;
    @Column(name="participant_name")
    private String participantName;
    @Column(name="gender")
    private Character gender;
    @Column(name="category")
    private String category;
    @Column(name="disability")
    private Character disability;
    @Column(name="aadhar_no")
    private Long aadharNo;
    @Column(name="mobile_no")
    private Long mobileNo;
    @Column(name="email")
    private String email;
    @Column(name="designation")
    private String designation;
    @Column(name="is_participated_before")
    private Character isParticipatedBefore;
    @Column(name="previous_participation_details")
    private String previousParticipationDetails;
    @Column(name="pre_training_ssessment_conduct")
    private Character preTrainingAssessmentConduct;
    @Column(name="post_training_ssessment_conduct")
    private Character postTrainingAssessmentConduct;
    @Column(name="is_certificate_issued")
    private Character isCertificateIssued;
    @Column(name="certificate_issue_date")
    private Date certificateIssueDate;
    @Column(name="need_assessment_methodology")
    private String needAssessmentMethodology;
    @Column(name="created_on")
    private Date createdOn;
    @Column(name="updated_on")
    private Date updatedOn;

}
