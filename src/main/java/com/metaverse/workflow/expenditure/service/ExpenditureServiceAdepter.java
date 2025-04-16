package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.activity.repository.ActivityRepository;
import com.metaverse.workflow.activity.repository.SubActivityRepository;
import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.ActivityDetailsException;
import com.metaverse.workflow.exceptions.ProgramDetailsException;
import com.metaverse.workflow.exceptions.SubActivityDetailsException;
import com.metaverse.workflow.expenditure.repository.CommonExpenditureRepository;
import com.metaverse.workflow.expenditure.repository.ProgramExpenditureRepository;
import com.metaverse.workflow.model.*;
import com.metaverse.workflow.program.repository.ProgramRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ExpenditureServiceAdepter implements ExpenditureService {
    @Autowired
    ProgramExpenditureRepository programExpenditureRepository;
    @Autowired
    CommonExpenditureRepository commonExpenditureRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    SubActivityRepository subActivityRepository;
    @Autowired
    ProgramRepository programRepository;
    @Override
    public WorkflowResponse saveExpenditure(ExpenditureRequest expenditureRequest) throws ActivityDetailsException,
            SubActivityDetailsException, ProgramDetailsException {
        Activity activity = activityRepository.findById(expenditureRequest.getActivityId())
                .orElseThrow(() -> new ActivityDetailsException(
                        "Activity details for the activity id " + expenditureRequest.getActivityId() + "do not exist",
                        "ACTIVITY-DETAILS-NOT-FOUND",
                        400
                ));
        SubActivity subActivity = subActivityRepository.findById(expenditureRequest.getSubActivityId())
                .orElseThrow(() -> new SubActivityDetailsException(
                        "SubActivity details for the subactivity id " + expenditureRequest.getActivityId() + "do not exist",
                        "SUB-ACTIVITY-DETAILS-NOT-FOUND",
                        400
                ));
        Program program = programRepository.findById(expenditureRequest.getProgramId())
                .orElseThrow(() -> new ProgramDetailsException(
                        "Program details for the program id " + expenditureRequest.getActivityId() + "do not exist",
                        "PROGRAM-NOT-FOUND",
                        400
                ));


        if (expenditureRequest.getExpenditureType().equals(ExpenditureType.COMMON)) {
            CommonExpenditure commonExpenditure = ExpenditureRequestMapper.mapCommonExpenditure(expenditureRequest, activity, subActivity, program);
            return WorkflowResponse.builder().message("Expenditure saved successfully").status(200)
                    .data( ExpenditureResponseMapper.mapCommonExpenditure(commonExpenditureRepository.save(commonExpenditure))).build();
        } else {
            ProgramExpenditure programExpenditure = ExpenditureRequestMapper.mapProgramExpenditure(expenditureRequest, activity, subActivity, program);
            return WorkflowResponse.builder().message("Expenditure saved successfully").status(200)
                    .data( ExpenditureResponseMapper.mapProgramExpenditure(programExpenditureRepository.save(programExpenditure))).build();
        }

    }
}
