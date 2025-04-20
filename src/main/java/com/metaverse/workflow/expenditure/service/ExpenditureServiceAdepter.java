package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.activity.repository.ActivityRepository;
import com.metaverse.workflow.activity.repository.SubActivityRepository;
import com.metaverse.workflow.agency.repository.AgencyRepository;
import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.*;
import com.metaverse.workflow.expenditure.repository.BulkExpenditureRepository;
import com.metaverse.workflow.expenditure.repository.HeadOfExpenseRepository;
import com.metaverse.workflow.expenditure.repository.ProgramExpenditureRepository;
import com.metaverse.workflow.model.*;

import com.metaverse.workflow.program.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExpenditureServiceAdepter implements ExpenditureService {
    @Autowired
    ProgramExpenditureRepository programExpenditureRepository;
    @Autowired
    BulkExpenditureRepository bulkExpenditureRepository;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    ProgramRepository programRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    SubActivityRepository subActivityRepository;
    @Autowired
    HeadOfExpenseRepository headOfExpenseRepository;

    @Override
    public WorkflowResponse saveBulkExpenditure(BulkExpenditureRequest expenditureRequest) throws AgencyDetailsException, HeadOfExpenseException {
        Agency agency = agencyRepository.findById(expenditureRequest.getAgencyId())
                .orElseThrow(() -> new AgencyDetailsException(
                        "Agency details for the agency id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "AGENCY-DETAILS-NOT-FOUND",
                        400
                ));
        HeadOfExpense headOfExpense = headOfExpenseRepository.findById(expenditureRequest.getExpenseId())
                .orElseThrow(() -> new HeadOfExpenseException(
                        "HeadOfExpense details for the HeadOfExpense id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "HEAD-OF-EXPENSE-DETAILS-NOT-FOUND",
                        400
                ));

        return WorkflowResponse.builder()
                .message("BulkExpenditure saved successfully")
                .data(ExpenditureResponseMapper.mapBulkExpenditure(
                        bulkExpenditureRepository.save(
                                ExpenditureRequestMapper
                                        .mapBulkExpenditure(expenditureRequest, agency, headOfExpense)
                        )
                ))
                .status(200).build();
    }

    @Override
    public WorkflowResponse saveProgramExpenditure(ProgramExpenditureRequest expenditureRequest) throws AgencyDetailsException, ProgramDetailsException, ActivityDetailsException, SubActivityDetailsException, HeadOfExpenseException {
        Program program = programRepository.findById(expenditureRequest.getProgramId())
                .orElseThrow(() -> new ProgramDetailsException(
                        "Program details for the program id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "PROGRAM-DETAILS-NOT-FOUND",
                        400
                ));
        Agency agency = agencyRepository.findById(expenditureRequest.getAgencyId())
                .orElseThrow(() -> new AgencyDetailsException(
                        "Agency details for the agency id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "AGENCY-DETAILS-NOT-FOUND",
                        400
                ));
        Activity activity=activityRepository.findById(expenditureRequest.getActivityId())
                .orElseThrow(() -> new ActivityDetailsException(
                        "Activity details for the activity id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "ACTIVITY-DETAILS-NOT-FOUND",
                        400
                ));
        SubActivity subActivity=subActivityRepository.findById(expenditureRequest.getActivityId())
                .orElseThrow(() -> new SubActivityDetailsException(
                        "SubActivity details for the subActivity id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "SUB-ACTIVITY-DETAILS-NOT-FOUND",
                        400
                ));
        HeadOfExpense headOfExpense = headOfExpenseRepository.findById(expenditureRequest.getHeadOfExpenseId())
                .orElseThrow(() -> new HeadOfExpenseException(
                        "HeadOfExpense details for the HeadOfExpense id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "HEAD-OF-EXPENSE-DETAILS-NOT-FOUND",
                        400
                ));
        return WorkflowResponse.builder()
                .message("Program Expenditure saved successfully")
                .data(ExpenditureResponseMapper.mapProgramExpenditure(
                        programExpenditureRepository.save(
                                ExpenditureRequestMapper.mapProgramExpenditure(expenditureRequest,activity,subActivity,program,agency,headOfExpense)
                        )

                ))
                .status(200).build();
    }

    @Override
    public WorkflowResponse getAllBulkExpenditure() {
        List<BulkExpenditure> bulkExpenditureList = bulkExpenditureRepository.findAll();
        if(bulkExpenditureList.isEmpty())
            return WorkflowResponse.builder().message("BulkExpenditure is Empty").status(400).build();

        return WorkflowResponse.builder().message("Success").status(200)
                .data(
                        bulkExpenditureList.stream().map(
                                ExpenditureResponseMapper::mapBulkExpenditure).toList()

                )
                .build();
    }


    @Override
    public WorkflowResponse getAllProgramExpenditure(ExpenditureType expenditureType) {
        List<ProgramExpenditure> programExpendituresList = programExpenditureRepository.findByExpenditureType(expenditureType);
        if(programExpendituresList.isEmpty())
            return WorkflowResponse.builder().message("Expenditures is Empty").status(400).build();
        return WorkflowResponse.builder().message("Success").status(200)
                .data(
                        programExpendituresList.stream().map(
                                ExpenditureResponseMapper::mapProgramExpenditure).toList()
                ).build();

    }

}
