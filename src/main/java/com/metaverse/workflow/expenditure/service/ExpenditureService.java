package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.*;
import com.metaverse.workflow.model.BulkExpenditure;
import com.metaverse.workflow.model.ProgramExpenditure;

import java.util.List;

public interface ExpenditureService {

    WorkflowResponse saveBulkExpenditure(BulkExpenditureRequest expenditureRequest) throws AgencyDetailsException, HeadOfExpenseException;
    WorkflowResponse saveProgramExpenditure(ProgramExpenditureRequest expenditureRequest) throws AgencyDetailsException, ProgramDetailsException, ActivityDetailsException, SubActivityDetailsException, HeadOfExpenseException;
    WorkflowResponse getAllBulkExpenditure();
    WorkflowResponse getAllProgramExpenditure(ExpenditureType expenditureType);
}
