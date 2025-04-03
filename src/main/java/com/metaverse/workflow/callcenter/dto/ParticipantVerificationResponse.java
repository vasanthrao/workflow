package com.metaverse.workflow.callcenter.dto;

import com.metaverse.workflow.model.QuestionAnswers;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantVerificationResponse {
    private Long participantVerificationId;
    private String verifiedBy;
    private String transactionDate;
    private List<QuestionAnswers> questionAnswers;
    private String verificationStatus;
}

