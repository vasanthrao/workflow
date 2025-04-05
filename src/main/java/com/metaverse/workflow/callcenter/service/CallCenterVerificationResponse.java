package com.metaverse.workflow.callcenter.service;

import com.metaverse.workflow.model.QuestionAnswers;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallCenterVerificationResponse {

    private Long programId;
    private Long participantId;
    private String verifiedBy;
    private String verificationDate;
    private List<QuestionAnswers> questionAnswers;
    private String verificationStatus;
    
}

