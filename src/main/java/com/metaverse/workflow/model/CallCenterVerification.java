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
@Table(name="call_center_verification")
public class CallCenterVerification {
    @Id
    @Column(name="cc_verification_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ccVerificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User verifiedBy;

    @Column(name="transaction_date")
    private Date transactionDate;

    @OneToMany(mappedBy ="questionAnswersId",cascade = CascadeType.ALL )
    @Column(name="question_answers")
    List<QuestionAnswers> questionAnswers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cc_verification_status_id", referencedColumnName = "cc_verification_status_id")
    private CallCenterVerificationStatus ccVerificationStatus;

    @Column(name="created_on",insertable = true,updatable = false)
    @CreationTimestamp
    private Date createdOn;

    @Column(name="updated_on",insertable = false,updatable = true)
    @UpdateTimestamp
    private Date updatedOn;

    @OneToOne(mappedBy = "callCenterVerification")
    private ParticipantVerificationDetails participantVerificationDetails;
}
