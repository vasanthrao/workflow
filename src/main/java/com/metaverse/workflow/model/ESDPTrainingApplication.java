package com.metaverse.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ESDPTrainingApplication")
public class ESDPTrainingApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ESDPTrainingApplicationId")
    private Long ESDPTrainingApplicationId;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "participantId", referencedColumnName = "participant_id")
    private Participant participantId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "organizationId", referencedColumnName = "organization_id")
    private Organization organizationId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "agencyId", referencedColumnName = "agency_id")
    private Agency agency;


    @Column(name = "dateOfAwarenessProgram")
    private Date dateOfAwarenessProgram;

    @Column(name = "interestedInAttending15Days")
    private String interestedInAttending15Days;

    @Column(name = "dateOfApplicationReceived")
    private Date dateOfApplicationReceived;

    @Column(name = "selectedForTraining")
    private String selectedForTraining;


    @Column(name="created_on", insertable = true, updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name="updated_on", insertable = false, updatable = true)
    @UpdateTimestamp
    private Date updatedOn;

}
