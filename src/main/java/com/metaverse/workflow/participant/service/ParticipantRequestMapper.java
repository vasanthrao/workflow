package com.metaverse.workflow.participant.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.Organization;
import com.metaverse.workflow.model.Participant;
import com.metaverse.workflow.model.Program;

import java.util.List;

public class ParticipantRequestMapper {
	
	public static Participant map(ParticipantRequest request, Organization organization, List<Program> programs)
	{
		return Participant.builder()
				.participantName(request.getParticipantName())
				.gender(request.getGender())
				.category(request.getCategory())
				.disability(request.getDisability())
				.aadharNo(request.getAadharNo())
				.mobileNo(request.getMobileNo())
				.email(request.getEmail())
				.designation(request.getDesignation())
				.isParticipatedBefore(request.getIsParticipatedBefore())
				.previousParticipationDetails(request.getPreviousParticipationDetails())
				.preTrainingAssessmentConducted(request.getPreTrainingAssessmentConducted())
				.postTrainingAssessmentConducted(request.getPostTrainingAssessmentConducted())
				.isCertificateIssued(request.getIsCertificateIssued())
				.certificateIssueDate(DateUtil.stringToDate(request.getCertificateIssueDate(), "dd-mm-yyyy"))
				.needAssessmentMethodology(request.getNeedAssessmentMethodology())
				.organization(organization != null ? organization : null)
				.programs(programs!= null && programs.size() > 0 ? programs : null)
				.build();
	}

	public static Participant mapAsprient(ParticipantRequest request)
	{
		return Participant.builder()
				.participantName(request.getParticipantName())
				.gender(request.getGender())
				.category(request.getCategory())
				.disability(request.getDisability())
				.aadharNo(request.getAadharNo())
				.mobileNo(request.getMobileNo())
				.email(request.getEmail())
				.designation(request.getDesignation())
				.isParticipatedBefore(request.getIsParticipatedBefore())
				.previousParticipationDetails(request.getPreviousParticipationDetails())
				.preTrainingAssessmentConducted(request.getPreTrainingAssessmentConducted())
				.postTrainingAssessmentConducted(request.getPostTrainingAssessmentConducted())
				.isCertificateIssued(request.getIsCertificateIssued())
				.certificateIssueDate(DateUtil.stringToDate(request.getCertificateIssueDate(), "dd-mm-yyyy"))
				.needAssessmentMethodology(request.getNeedAssessmentMethodology())
				.build();
	}

}
