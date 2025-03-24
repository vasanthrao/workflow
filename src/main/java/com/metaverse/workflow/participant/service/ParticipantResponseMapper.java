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
				.organizationId(participant.getOrganization() != null ? participant.getOrganization().getOrganizationId() : null)
				.programIds(participant.getPrograms() != null ? participant.getPrograms().stream().map(program -> program.getProgramId()).collect(Collectors.toList()) : null)
				.organizationName(participant.getOrganization() != null ? participant.getOrganization().getOrganizationName() : null)
				.build();
	}

	public static ParticipantResponseForESDPTraining mapForESDPTraining(Participant participant)
	{
		return ParticipantResponseForESDPTraining.builder()
				.organizationId(participant.getOrganization().getOrganizationId())
				.organizationName(participant.getOrganization().getOrganizationName())
				.organizationCategory(participant.getOrganization().getOrganizationCategory())
				.organizationType(participant.getOrganization().getOrganizationType())
				.nameOfTheSHG(participant.getOrganization().getNameOfTheSHG())
				.startupCertificateNo(participant.getOrganization().getStartupCertificateNo())
				.natureOfStartup(participant.getOrganization().getNatureOfStartup())
				.areasOfWorking(participant.getOrganization().getAreasOfWorking())
				.programDates(participant.getPrograms().stream().map(program -> dateToString(program.getStartDate(), "dd-mm-yyyy"))
						.collect(Collectors.toList()))
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
				.isCertificateIssued((participant.getIsCertificateIssued()))
				.certificateIssueDate(DateUtil.dateToString(participant.getCertificateIssueDate(),"dd-mm-yyyy"))
				.needAssessmentMethodology(participant.getNeedAssessmentMethodology())
				.build();
	}

	public static ParticipantResponse mapAsprient(Participant participant)
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
				.build();
	}

}
