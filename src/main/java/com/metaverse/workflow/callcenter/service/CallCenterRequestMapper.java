package com.metaverse.workflow.callcenter.service;

import com.metaverse.workflow.callcenter.dto.CallCenterVerificationRequest;
import com.metaverse.workflow.callcenter.dto.QuestionRequest;
import com.metaverse.workflow.callcenter.dto.SubActivityQuestionsRequest;
import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CallCenterRequestMapper {
    public static Question mapQuestion(QuestionRequest request)
    {
        return Question.builder().question(request.getQuestion())
                .questionFieldType(request.getQuestionFieldType())
                .options(request.getOptions()== null ? "" :
                        request.getOptions().stream().collect(Collectors.joining(",")))
                .build();
    }
    public static SubActivityQuestions mapSubActivityQuestions(SubActivityQuestionsRequest request)
    {
       return SubActivityQuestions.builder().subActivityId(request.getSubActivityId())
                .questionsIds(request.getQuestions()).build();

    }
    public static QuestionAnswers mapQuestionAnswer(Question question, String answer)
    {
        return QuestionAnswers.builder().question(question).answers(answer).build();
    }
    public static CallCenterVerification mapParticipantVerification(CallCenterVerificationRequest request, List<QuestionAnswers> questionAnswersList, User user)
    {
       return CallCenterVerification.builder()
               .verifiedBy(user)
               .transactionDate(DateUtil.stringToDate(request.getTransactionDate(),"dd-MM-yyyy"))
               .questionAnswers(questionAnswersList)
               //.participantVerificationDetails(request.getVerificationStatusId())
               .build();
    }
}
