package com.metaverse.workflow.participant.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.Participant;

import java.util.stream.Collectors;

import static com.metaverse.workflow.common.util.DateUtil.dateToString;
public class ParticipantResponseMapper {
	
	public static ParticipantResponse map(Participant participant)
	{
		return ParticipantResponse.builder()
				.participantId(participant.getParticipantId())
				.participantName(participant.getParticipantName())
				.gender(participant.getGender())
				.category(participant.getCategory())
				.disability(participant.getDisability())
				.aadharNo(participant.getAadharNo())
				.mobileNo(participant.getMobileNo())
				.email(participant.getEmail())
				.designation(participant.getDesignation())
				.isParticipatedBefore(participant.getIsParticipatedBefore())
				.previousParticipationDetails(participant.getPreviousParticipationDetails())
				.preTrainingAssessmentConducted(participant.getPreTrainingAssessmentConducted())
				.postTrainingAssessmentConducted(participant.getPostTrainingAssessmentConducted())
				.isCertificateIssued(participant.getIsCertificateIssued())
				.certificateIssueDate(DateUtil.dateToString(participant.getCertificateIssueDate(), "dd-mm-yyyy"))
				.needAssessmentMethodology(participant.getNeedAssessmentMethodology())
				.organizationId(participant.getOrganization().getOrganizationId())
				.programIds(participant.getPrograms().stream().map(program -> program.getProgramId()).collect(Collectors.toList()))
				.organizationName(participant.getOrganization().getOrganizationName())
				.build();
	}

	public static ParticipantResponseForESDPTraining map1(Participant participant)
	{
		return ParticipantResponseForESDPTraining.builder()
				.participantName(participant.getParticipantName())
				.organizationName(participant.getOrganization().getOrganizationName())
				.programDates(participant.getPrograms().stream().map(program -> dateToString(program.getStartDate(), "dd-mm-yyyy"))
						.collect(Collectors.toList())).build();
	}

}
