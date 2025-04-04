package com.metaverse.workflow.callcenter.service;

import com.metaverse.workflow.callcenter.dto.CallCenterVerificationResponse;
import com.metaverse.workflow.callcenter.dto.QuestionResponse;
import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.CallCenterVerification;
import com.metaverse.workflow.model.CallCenterVerificationStatus;
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


    public static CallCenterVerificationResponse mapParticipantVerification(CallCenterVerification callCenterVerification, CallCenterVerificationStatus verificationStatus) {
        return CallCenterVerificationResponse.builder()
                .participantVerificationId(callCenterVerification.getCcVerificationId())
                .verifiedBy(callCenterVerification.getVerifiedBy().getUserId())
                .transactionDate(DateUtil.dateToString(callCenterVerification.getTransactionDate(),"dd-MM-yyyy"))
                .questionAnswers(callCenterVerification.getQuestionAnswers())
                .verificationStatus(verificationStatus.getVerificationDetails())
                .build();
    }
}
