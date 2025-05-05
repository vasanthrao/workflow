package com.metaverse.workflow.model;

import com.metaverse.workflow.common.enums.OrganizationType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.ToOne;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "program_monitoring_feedback")
public class ProgramMonitoringFeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="monitoring_id")
    private Long monitoringFeedBackId;

    @OneToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(name="state")
    private String state;

    @Column(name="district")
    private String district;

    @Column(name="dateOfMonitoring")
    private Date dateOfMonitoring;

    @Column(name="programType")
    private String programType;

    @Column(name="organization_type")
    private List<OrganizationType> organizationType;
    
    @Column(name="male_participants")
    private Integer maleParticipants;

    @Column(name="female_participants")
    private Integer femaleParticipants;

    @Column(name="transe_gender_participants")
    private Integer transGenderParticipants;

    @Column(name="total_participants")
    private Integer totalParticipants;

    @Column(name="category_representation")
    private List<String> categoryRepresentation;

    @OneToMany
    private List<PreEventChecklist> preEventChecklists;

    @OneToMany
    private List<ProgramDeliveryDetails> programDeliveryDetails;


    @OneToMany
    private List<LogisticsEvaluation> logisticsEvaluations;

   /* @Embedded
    private ExecutionObservation executionObservation;table

    @Embedded
    private PostTrainingAssessment postTrainingAssessment;

    @Embedded
    private DocumentChecklist documentChecklist;

    @Embedded
    private ObservationsAndChallenges observationsAndChallenges;

    @Embedded
    private ProgramRating programRating;

    @Embedded
    private AdditionalRemarks   additionalRemarks;;*/


}
