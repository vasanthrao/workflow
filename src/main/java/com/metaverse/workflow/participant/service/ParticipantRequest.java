package com.metaverse.workflow.participant.service;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.metaverse.workflow.model.Organization;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParticipantRequest {
	
	private String participantName;
	private Character gender;
	private String category;
	private Character disability;
	private Long aadharNo;
	private Long mobileNo;
	private String email;
	private String designation;
	private Character isParticipatedBefore;
	private String previousParticipationDetails;
	private Character preTrainingAssessmentConducted;
	private Character postTrainingAssessmentConducted;
	private Character isCertificateIssued;
	private String certificateIssueDate;
	private String needAssessmentMethodology;
	private Long organizationId;
	private Set<Long> programIds;
}
