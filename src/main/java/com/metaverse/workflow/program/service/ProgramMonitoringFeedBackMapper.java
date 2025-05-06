package com.metaverse.workflow.program.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.ProgramMonitoringFeedBack;
import com.metaverse.workflow.model.PreEventChecklist;
import com.metaverse.workflow.model.ProgramDeliveryDetails;
import com.metaverse.workflow.model.LogisticsEvaluation;

import java.util.List;
import java.util.stream.Collectors;

public class ProgramMonitoringFeedBackMapper {


    public static ProgramMonitoringFeedBack mapRequest(ProgramMonitoringFeedBackRequest request) {
        ProgramMonitoringFeedBack feedback = ProgramMonitoringFeedBack.builder()
                .programId(request.getProgramId())
                .state(request.getState())
                .district(request.getDistrict())
                .dateOfMonitoring(DateUtil.stringToDate(request.getDateOfMonitoring(), "dd-MM-yyy"))
                .agencyName(request.getAgencyName())
                .programType(request.getProgramType())
                .programName(request.getProgramName())
                .venueName(request.getVenueName())
                .hostingAgencyName(request.getHostingAgencyName())
                .spocName(request.getSpocName())
                .spocContact(request.getSpocContact())
                .inTime(request.getInTime())
                .outTime(request.getOutTime())
                .maleParticipants(request.getMaleParticipants())
                .femaleParticipants(request.getFemaleParticipants())
                .transGenderParticipants(request.getTransGenderParticipants())
                .totalParticipants(request.getTotalParticipants())
                .noOfSHG(request.getNoOfSHG())
                .noOfMSME(request.getNoOfMSME())
                .noOfStartup(request.getNoOfStartup())
                .noOfDIC(request.getNoOfDIC())
                .noOfIAs(request.getNoOfIAs())
                .noOfOthers(request.getNoOfOthers())
                .noOfSC(request.getNoOfSC())
                .noOfST(request.getNoOfST())
                .noOfOC(request.getNoOfOC())
                .noOfBC(request.getNoOfBC())
                .noOfMinority(request.getNoOfMinority())
                .timingPunctuality(request.getTimingPunctuality())
                .sessionContinuity(request.getSessionContinuity())
                .participantInterestLevel(request.getParticipantInterestLevel())
                .overallEnergyEngagement(request.getOverallEnergyEngagement())
                .unforeseenIssues(request.getUnforeseenIssues())
                .participantsFeedbackCollected(request.getParticipantsFeedbackCollected())
                .resourceFeedbackCollected(request.getResourceFeedbackCollected())
                .prePostTestsConducted(request.getPrePostTestsConducted())
                .learningHighlights(request.getLearningHighlights())
                .attendanceSheet(request.getAttendanceSheet())
                .registrationForms(request.getRegistrationForms())
                .participantFeedBack(request.getParticipantFeedBack())
                .speakerFeedBack(request.getSpeakerFeedBack())
                .photographsAttached(request.getPhotographsAttached())
                .summaryReportPrepared(request.getSummaryReportPrepared())
                .observedPractices(request.getObservedPractices())
                .challengesFaced(request.getChallengesFaced())
                .issuesAndCorrections(request.getIssuesAndCorrections())
                .overallProgramOrganization(request.getOverallProgramOrganization())
                .qualityOfSessions(request.getQualityOfSessions())
                .participantsSatisfaction(request.getParticipantsSatisfaction())
                .impactPotential(request.getImpactPotential())
                .additionalRemarks(request.getAdditionalRemarks())
                .build();

        // PreEventChecklist
        if (request.getPreEventChecklists() != null) {
            List<PreEventChecklist> checklistList = request.getPreEventChecklists().stream()
                    .map(item -> PreEventChecklist.builder()
                            .item(item.getItem())
                            .status(item.getStatus())
                            .remarks(item.getRemarks())
                            .programMonitoringFeedBack(feedback)
                            .build())
                    .collect(Collectors.toList());
            feedback.setPreEventChecklists(checklistList);
        }

        // ProgramDeliveryDetails
        if (request.getProgramDeliveryDetails() != null) {
            List<ProgramDeliveryDetails> deliveryList = request.getProgramDeliveryDetails().stream()
                    .map(d -> ProgramDeliveryDetails.builder()
                            .speakerName(d.getSpeakerName())
                            .topicDelivered(d.getTopicDelivered())
                            .timeTaken(d.getTimeTaken())
                            .audioVisualUsed(d.getAudioVisualUsed())
                            .relevance(d.getRelevance())
                            .speakerEffectiveness(d.getSpeakerEffectiveness())
                            .programMonitoringFeedBack(feedback)
                            .build())
                    .collect(Collectors.toList());
            feedback.setProgramDeliveryDetails(deliveryList);
        }

        // LogisticsEvaluation
        if (request.getLogisticsEvaluations() != null) {
            List<LogisticsEvaluation> logisticsList = request.getLogisticsEvaluations().stream()
                    .map(l -> LogisticsEvaluation.builder()
                            .parameter(l.getParameter())
                            .rating(l.getRating())
                            .remarks(l.getRemarks())
                            .programMonitoringFeedBack(feedback)
                            .build())
                    .collect(Collectors.toList());
            feedback.setLogisticsEvaluations(logisticsList);
        }

        return feedback;
    }

    public static ProgramMonitoringFeedBackResponse mapResponse(ProgramMonitoringFeedBack monitoringFeedBack) {
        return ProgramMonitoringFeedBackResponse.builder()
                .monitorId(monitoringFeedBack.getMonitoringFeedBackId())
                .programId(monitoringFeedBack.getProgramId())
                .state(monitoringFeedBack.getState())
                .district(monitoringFeedBack.getDistrict())
                .dateOfMonitoring(DateUtil.dateToString(monitoringFeedBack.getDateOfMonitoring(),"dd-MM-yyyy"))
                .agencyName(monitoringFeedBack.getAgencyName())
                .programType(monitoringFeedBack.getProgramType())
                .programName(monitoringFeedBack.getProgramName())
                .venueName(monitoringFeedBack.getVenueName())
                .hostingAgencyName(monitoringFeedBack.getHostingAgencyName())
                .spocName(monitoringFeedBack.getSpocName())
                .spocContact(monitoringFeedBack.getSpocContact())
                .inTime(monitoringFeedBack.getInTime())
                .outTime(monitoringFeedBack.getOutTime())

                .maleParticipants(monitoringFeedBack.getMaleParticipants())
                .femaleParticipants(monitoringFeedBack.getFemaleParticipants())
                .transGenderParticipants(monitoringFeedBack.getTransGenderParticipants())
                .totalParticipants(monitoringFeedBack.getTotalParticipants())
                .noOfSHG(monitoringFeedBack.getNoOfSHG())
                .noOfMSME(monitoringFeedBack.getNoOfMSME())
                .noOfStartup(monitoringFeedBack.getNoOfStartup())
                .noOfDIC(monitoringFeedBack.getNoOfDIC())
                .noOfIAs(monitoringFeedBack.getNoOfIAs())
                .noOfOthers(monitoringFeedBack.getNoOfOthers())
                .noOfSC(monitoringFeedBack.getNoOfSC())
                .noOfST(monitoringFeedBack.getNoOfST())
                .noOfOC(monitoringFeedBack.getNoOfOC())
                .noOfBC(monitoringFeedBack.getNoOfBC())
                .noOfMinority(monitoringFeedBack.getNoOfMinority())

                .timingPunctuality(monitoringFeedBack.getTimingPunctuality())
                .sessionContinuity(monitoringFeedBack.getSessionContinuity())
                .participantInterestLevel(monitoringFeedBack.getParticipantInterestLevel())
                .overallEnergyEngagement(monitoringFeedBack.getOverallEnergyEngagement())
                .unforeseenIssues(monitoringFeedBack.getUnforeseenIssues())

                .participantsFeedbackCollected(monitoringFeedBack.getParticipantsFeedbackCollected())
                .resourceFeedbackCollected(monitoringFeedBack.getResourceFeedbackCollected())
                .prePostTestsConducted(monitoringFeedBack.getPrePostTestsConducted())
                .learningHighlights(monitoringFeedBack.getLearningHighlights())

                .attendanceSheet(monitoringFeedBack.getAttendanceSheet())
                .registrationForms(monitoringFeedBack.getRegistrationForms())
                .participantFeedBack(monitoringFeedBack.getParticipantFeedBack())
                .speakerFeedBack(monitoringFeedBack.getSpeakerFeedBack())
                .photographsAttached(monitoringFeedBack.getPhotographsAttached())
                .summaryReportPrepared(monitoringFeedBack.getSummaryReportPrepared())

                .observedPractices(monitoringFeedBack.getObservedPractices())
                .challengesFaced(monitoringFeedBack.getChallengesFaced())
                .issuesAndCorrections(monitoringFeedBack.getIssuesAndCorrections())

                .overallProgramOrganization(monitoringFeedBack.getOverallProgramOrganization())
                .qualityOfSessions(monitoringFeedBack.getQualityOfSessions())
                .participantsSatisfaction(monitoringFeedBack.getParticipantsSatisfaction())
                .impactPotential(monitoringFeedBack.getImpactPotential())

                .additionalRemarks(monitoringFeedBack.getAdditionalRemarks())

                .preEventChecklists(
                        monitoringFeedBack.getPreEventChecklists().stream()
                                .map(pre -> ProgramMonitoringFeedBackResponse.PreEventChecklist.builder()
                                        .item(pre.getItem())
                                        .status(pre.getStatus())
                                        .remarks(pre.getRemarks())
                                        .build()
                                ).toList()
                )
                .programDeliveryDetails(
                        monitoringFeedBack.getProgramDeliveryDetails().stream()
                                .map(detail -> ProgramMonitoringFeedBackResponse.ProgramDelivery.builder()
                                        .programDeliveryDetailsId(detail.getProgramDeliveryDetailsId())
                                        .speakerName(detail.getSpeakerName())
                                        .topicDelivered(detail.getTopicDelivered())
                                        .timeTaken(detail.getTimeTaken())
                                        .audioVisualUsed(detail.getAudioVisualUsed())
                                        .relevance(detail.getRelevance())
                                        .speakerEffectiveness(detail.getSpeakerEffectiveness())
                                        .build()
                                ).toList()
                )
                .logisticsEvaluations(
                        monitoringFeedBack.getLogisticsEvaluations().stream()
                                .map(log -> ProgramMonitoringFeedBackResponse.LogisticsEvaluation.builder()
                                        .parameter(log.getParameter())
                                        .rating(log.getRating())
                                        .remarks(log.getRemarks())
                                        .build()
                                ).toList()
                )

                .build();
    }
    public static void updateEntityFromRequest(ProgramMonitoringFeedBack entity, ProgramMonitoringFeedBackRequest request) {
        entity.setState(request.getState());
        entity.setDistrict(request.getDistrict());
        entity.setDateOfMonitoring(DateUtil.stringToDate(request.getDateOfMonitoring(),"dd-MM-yyyy"));
        entity.setAgencyName(request.getAgencyName());
        entity.setProgramType(request.getProgramType());
        entity.setProgramName(request.getProgramName());
        entity.setVenueName(request.getVenueName());
        entity.setHostingAgencyName(request.getHostingAgencyName());
        entity.setSpocName(request.getSpocName());
        entity.setSpocContact(request.getSpocContact());
        entity.setInTime(request.getInTime());
        entity.setOutTime(request.getOutTime());

        entity.setMaleParticipants(request.getMaleParticipants());
        entity.setFemaleParticipants(request.getFemaleParticipants());
        entity.setTransGenderParticipants(request.getTransGenderParticipants());
        entity.setTotalParticipants(request.getTotalParticipants());
        entity.setNoOfSHG(request.getNoOfSHG());
        entity.setNoOfMSME(request.getNoOfMSME());
        entity.setNoOfStartup(request.getNoOfStartup());
        entity.setNoOfDIC(request.getNoOfDIC());
        entity.setNoOfIAs(request.getNoOfIAs());
        entity.setNoOfOthers(request.getNoOfOthers());
        entity.setNoOfSC(request.getNoOfSC());
        entity.setNoOfST(request.getNoOfST());
        entity.setNoOfOC(request.getNoOfOC());
        entity.setNoOfBC(request.getNoOfBC());
        entity.setNoOfMinority(request.getNoOfMinority());

        entity.setTimingPunctuality(request.getTimingPunctuality());
        entity.setSessionContinuity(request.getSessionContinuity());
        entity.setParticipantInterestLevel(request.getParticipantInterestLevel());
        entity.setOverallEnergyEngagement(request.getOverallEnergyEngagement());
        entity.setUnforeseenIssues(request.getUnforeseenIssues());

        entity.setParticipantsFeedbackCollected(request.getParticipantsFeedbackCollected());
        entity.setResourceFeedbackCollected(request.getResourceFeedbackCollected());
        entity.setPrePostTestsConducted(request.getPrePostTestsConducted());
        entity.setLearningHighlights(request.getLearningHighlights());

        entity.setAttendanceSheet(request.getAttendanceSheet());
        entity.setRegistrationForms(request.getRegistrationForms());
        entity.setParticipantFeedBack(request.getParticipantFeedBack());
        entity.setSpeakerFeedBack(request.getSpeakerFeedBack());
        entity.setPhotographsAttached(request.getPhotographsAttached());
        entity.setSummaryReportPrepared(request.getSummaryReportPrepared());

        entity.setObservedPractices(request.getObservedPractices());
        entity.setChallengesFaced(request.getChallengesFaced());
        entity.setIssuesAndCorrections(request.getIssuesAndCorrections());

        entity.setOverallProgramOrganization(request.getOverallProgramOrganization());
        entity.setQualityOfSessions(request.getQualityOfSessions());
        entity.setParticipantsSatisfaction(request.getParticipantsSatisfaction());
        entity.setImpactPotential(request.getImpactPotential());

        entity.setAdditionalRemarks(request.getAdditionalRemarks());

        // Replace children
        /*entity.setPreEventChecklists(request.getPreEventChecklists());
        entity.setProgramDeliveryDetails(request.getProgramDeliveryDetails());
        entity.setLogisticsEvaluations(request.getLogisticsEvaluations());
   */ }
}
