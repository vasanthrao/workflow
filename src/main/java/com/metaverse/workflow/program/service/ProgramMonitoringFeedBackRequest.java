package com.metaverse.workflow.program.service;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProgramMonitoringFeedBackRequest {
    private Long programId;
    private String state;
    private String district;
    private String dateOfMonitoring;
    private String agencyName;
    private String programType;
    private String programName;
    private String venueName;
    private String hostingAgencyName;
    private String spocName;
    private Long spocContact;
    private String inTime;
    private String outTime;

    private Integer maleParticipants;
    private Integer femaleParticipants;
    private Integer transGenderParticipants;
    private Integer totalParticipants;
    private Integer noOfSHG;
    private Integer noOfMSME;
    private Integer noOfStartup;
    private Integer noOfDIC;
    private Integer noOfIAs;
    private Integer noOfOthers;
    private Integer noOfSC;
    private Integer noOfST;
    private Integer noOfOC;
    private Integer noOfBC;
    private Integer noOfMinority;

    private String timingPunctuality;
    private String sessionContinuity;
    private String participantInterestLevel;
    private String overallEnergyEngagement;
    private String unforeseenIssues;

    private Boolean participantsFeedbackCollected;
    private Boolean resourceFeedbackCollected;
    private Boolean prePostTestsConducted;
    private String learningHighlights;

    private Boolean attendanceSheet;
    private Boolean registrationForms;
    private Boolean participantFeedBack;
    private Boolean speakerFeedBack;
    private Boolean photographsAttached;
    private Boolean summaryReportPrepared;

    private String observedPractices;
    private String challengesFaced;
    private String issuesAndCorrections;

    private Integer overallProgramOrganization;
    private Integer qualityOfSessions;
    private Integer participantsSatisfaction;
    private Integer impactPotential;

    private String additionalRemarks;
    private List<PreEventChecklist> preEventChecklists;
    private List<ProgramDelivery> programDeliveryDetails;
    private List<LogisticsEvaluation> logisticsEvaluations;


    @Builder
    @AllArgsConstructor
    @Getter
    @Setter
    public static class PreEventChecklist {
        private String item;
        private Boolean status;
        private String remarks;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    @Setter
    public static class ProgramDelivery {
        private Long programDeliveryDetailsId;
        private String speakerName;
        private String topicDelivered;
        private Integer timeTaken;
        private Boolean audioVisualUsed;
        private String relevance;
        private Integer speakerEffectiveness;//Ratting
    }

    @Builder
    @AllArgsConstructor
    @Getter
    @Setter
    public static class LogisticsEvaluation {
        private String parameter;
        private Integer rating;
        private String remarks;
    }

}
