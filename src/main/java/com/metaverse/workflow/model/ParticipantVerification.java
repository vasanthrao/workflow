package com.metaverse.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="participant_verification")
public class ParticipantVerification {
    @Id
    @Column(name="participant_verification_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long participantVerificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User verifiedBy;

    @Column(name="transaction_date")
    private Date transactionDate;

    @OneToMany(mappedBy ="questionAnswersId",cascade = CascadeType.ALL )
    @Column(name="question_answers")
    List<QuestionAnswers> questionAnswers;


    @Column(name="participant_verification_status_id")
    private Integer participantVerificationStatusId;

    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;
}
