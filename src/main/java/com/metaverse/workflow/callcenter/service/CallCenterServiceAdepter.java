package com.metaverse.workflow.callcenter.service;

import com.metaverse.workflow.activity.repository.SubActivityRepository;
import com.metaverse.workflow.callcenter.dto.*;
import com.metaverse.workflow.callcenter.repository.ParticipantVerificationRepository;
import com.metaverse.workflow.callcenter.repository.ParticipantVerificationStatusRepository;
import com.metaverse.workflow.callcenter.repository.QuestionRepository;
import com.metaverse.workflow.callcenter.repository.SubActivityQuestionsRepository;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.login.repository.LoginRepository;
import com.metaverse.workflow.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CallCenterServiceAdepter implements CallCenterService {

    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ParticipantVerificationStatusRepository participantVerificationStatusRepository;
    @Autowired
    SubActivityRepository subActivityRepository;
    @Autowired
    SubActivityQuestionsRepository subActivityQuestionsRepository;
    @Autowired
    ParticipantVerificationRepository participantVerificationRepository;
    @Autowired
    LoginRepository loginRepository;
    @Override
    public WorkflowResponse saveQuestion(QuestionRequest request) {
        Question question=CallCenterRequestMapper.mapQuestion(request);
        Question savedQuestion = questionRepository.save(question);
        return WorkflowResponse.builder().message("Question saved successfully").status(200).data(savedQuestion).build();
    }

    @Override
    public WorkflowResponse getAllQuestion() {
        List<Question> questionList=questionRepository.findAll();
        if(questionList.isEmpty())
            return WorkflowResponse.builder().message("Question Not found").status(400).build();
        List<QuestionResponse> questionResponses = questionList.stream().map(question -> CallCenterResponseMapper.mapQuestion(question)).collect(Collectors.toList());
        return WorkflowResponse.builder().message("Success").status(200).data(questionResponses).build();
    }
    @Override
    public WorkflowResponse getQuestionById(Integer id) {

        Optional<Question> question = questionRepository.findById(id);
        if(!question.isPresent())return WorkflowResponse.builder().message(id+" is a wrong Question id ").status(400).build();
        return WorkflowResponse.builder().message("success").status(200).data(question.get()).build();
    }



    @Override
    public WorkflowResponse getAllVerificationStatus() {
        List<CallCenterVerificationStatus> statusList = participantVerificationStatusRepository.findAll();
        if(statusList.isEmpty())
            return WorkflowResponse.builder().message("Status Not found").status(400).build();

        return WorkflowResponse.builder().message("Success").status(200).data(statusList).build();

    }

    @Override
    public WorkflowResponse saveSubActivityQuestions(SubActivityQuestionsRequest request) {

        Optional< SubActivity> subActivity = subActivityRepository.findById(request.getSubActivityId());
        if(!subActivity.isPresent())return WorkflowResponse.builder().message("SubActivity  Not found").status(400).build();
        List<SubActivityQuestions> allExistingQuestions = subActivityQuestionsRepository.findAll();

        boolean exists = allExistingQuestions.stream()
                .anyMatch(q -> q.getSubActivityId().equals(request.getSubActivityId()));

        if (exists) {
            return WorkflowResponse.builder().message("SubActivity already exists, cannot add duplicate").status(400).build();
        }


        List<Question> allQuestions = questionRepository.findAllById(request.getQuestions());
        if (allQuestions.size() != request.getQuestions().size()) {
            return WorkflowResponse.builder().message("Some questions were not found").status(400).build();
        }

        SubActivityQuestions subActivityQuestions = subActivityQuestionsRepository.save(CallCenterRequestMapper.mapSubActivityQuestions(request));
        return WorkflowResponse.builder().message("SubActivityQuestion saved successfully")
                .status(200).data(subActivityQuestions).build();
    }
    @Override
    public WorkflowResponse getQuestionBySubActivityId(Long subActivityId) {
        SubActivityQuestions subActivityQuestions = subActivityQuestionsRepository.findBySubActivityId(subActivityId);
        if(subActivityQuestions ==null)return  WorkflowResponse.builder().message("SubActivity not not found").status(400).build();
        List<Question> questions = questionRepository.findAllById(subActivityQuestions.getQuestionsIds());
        return  WorkflowResponse.builder().message("success").status(200).data(questions).build();
    }

    @Override
    public WorkflowResponse saveParticipantVerification(ParticipantVerificationRequest request) {
        if(request.getQuestionId().size()!=request.getAnswers().size())
            return WorkflowResponse.builder().message("Must give an answer for every question.").status(422).build();
        Optional<CallCenterVerificationStatus> verificationStatus = participantVerificationStatusRepository.findById(request.getVerificationStatusId());
        if(!verificationStatus.isPresent())
            return WorkflowResponse.builder().message("Give  a valid verification status").status(422).build();
        List<Question> questionList = questionRepository.findAllById(request.getQuestionId());
        List<QuestionAnswers> questionAnswersList = IntStream.range(0, request.getQuestionId().size())
                .mapToObj(i -> CallCenterRequestMapper.mapQuestionAnswer(questionList.get(i), request.getAnswers().get(i)))
                .collect(Collectors.toList());
        Optional<User> user = loginRepository.findById(request.getVerifiedBy());
        if(!user.isPresent()) return WorkflowResponse.builder().message("User not found").status(4).build();
        CallCenterVerification callCenterVerification = participantVerificationRepository.save(CallCenterRequestMapper.mapParticipantVerification(request, questionAnswersList, user.get()));


        return WorkflowResponse.builder().message("Participant Verification data is saved successfully").status(200).data(callCenterVerification).build();

    }

    @Override
    public WorkflowResponse getAllParticipantVerificationData() {

        List<CallCenterVerification> callCenterVerificationList = participantVerificationRepository.findAll();
        if(callCenterVerificationList.isEmpty())
            return WorkflowResponse.builder().message("Verification list empty").status(400).build();
        List<ParticipantVerificationResponse> verificationResponses = callCenterVerificationList.stream().map(callCenterVerification -> CallCenterResponseMapper.mapParticipantVerification(callCenterVerification
                , getVerificationStatusByID(callCenterVerification.getCcVerificationStatus().getCcVerificationStatusId()))).collect(Collectors.toList());
        return WorkflowResponse.builder().message("Success").status(200).data(verificationResponses).build();
    }

   public CallCenterVerificationStatus getVerificationStatusByID(Integer participantVerificationStatusId) {
       Optional<CallCenterVerificationStatus> status = participantVerificationStatusRepository.findById(participantVerificationStatusId);
       return status.get();
    }


}
