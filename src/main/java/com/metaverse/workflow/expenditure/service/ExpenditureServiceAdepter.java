package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.agency.repository.AgencyRepository;
import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.ActivityDetailsException;
import com.metaverse.workflow.exceptions.AgencyDetailsException;
import com.metaverse.workflow.exceptions.ProgramDetailsException;
import com.metaverse.workflow.exceptions.SubActivityDetailsException;
import com.metaverse.workflow.expenditure.repository.BulkExpenditureRepository;
import com.metaverse.workflow.expenditure.repository.ProgramExpenditureRepository;
import com.metaverse.workflow.model.*;

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


    @Override
    public WorkflowResponse saveBulkExpenditure(BulkExpenditureRequest expenditureRequest) throws AgencyDetailsException {
        Agency agency = agencyRepository.findById(expenditureRequest.getAgencyId())
                .orElseThrow(() -> new AgencyDetailsException(
                        "Agency details for the agency id " + expenditureRequest.getAgencyId() + " do not exist.",
                        "AGENCY-DETAILS-NOT-FOUND",
                        400
                ));

        return WorkflowResponse.builder()
                .message("BulkExpenditure saved successfully")
                .data(BulkExpenditureResponseMapper.mapBulkExpenditure(
                        bulkExpenditureRepository.save(
                                BulkExpenditureRequestMapper
                                        .mapBulkExpenditure(expenditureRequest, agency)
                        )
                ))
                .status(200).build();
    }



}
