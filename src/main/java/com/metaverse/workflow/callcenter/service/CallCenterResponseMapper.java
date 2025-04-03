package com.metaverse.workflow.callcenter.service;

import com.metaverse.workflow.callcenter.dto.ParticipantVerificationResponse;
import com.metaverse.workflow.callcenter.dto.QuestionResponse;
import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.ParticipantVerification;
import com.metaverse.workflow.model.ParticipantVerificationStatus;
import com.metaverse.workflow.model.Question;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class CallCenterResponseMapper {

    public static QuestionResponse mapQuestion(Question question)
    {
        return QuestionResponse.builder()
                .QuestionId(question.getQuestionId())
                .question(question.getQuestion())
                .questionFieldType(question.getQuestionFieldType())
                .options(question.getOptions()==null ? Collections.emptyList()
                        : Arrays.stream(question.getOptions().split(","))
                        .collect(Collectors.toList()))
                .build();
    }


    public static ParticipantVerificationResponse mapParticipantVerification(ParticipantVerification participantVerification, ParticipantVerificationStatus verificationStatus) {
        return ParticipantVerificationResponse.builder()
                .participantVerificationId(participantVerification.getParticipantVerificationId())
                .verifiedBy(participantVerification.getVerifiedBy().getUserId())
                .transactionDate(DateUtil.dateToString(participantVerification.getTransactionDate(),"dd-MM-yyyy"))
                .questionAnswers(participantVerification.getQuestionAnswers())
                .verificationStatus(verificationStatus.getVerificationDetails())
                .build();
    }
}
