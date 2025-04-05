package com.metaverse.workflow.callcenter.service;

import com.metaverse.workflow.common.response.WorkflowResponse;

public interface CallCenterService {

    WorkflowResponse saveQuestion(QuestionRequest request);
    WorkflowResponse getAllQuestion();
    WorkflowResponse getAllVerificationStatus();
    WorkflowResponse saveSubActivityQuestions(SubActivityQuestionsRequest request);
    WorkflowResponse getQuestionById(Integer id);
    WorkflowResponse getQuestionBySubActivityId(Long id);
    WorkflowResponse saveCallCenterVerification(CallCenterVerificationRequest request);
    WorkflowResponse getAllCallCenterVerificationData();


}
