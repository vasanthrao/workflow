package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.*;

public interface ExpenditureService {

    WorkflowResponse saveBulkExpenditure(BulkExpenditureRequest expenditureRequest) throws DataException;
    WorkflowResponse saveProgramExpenditure(ProgramExpenditureRequest expenditureRequest) throws DataException;
    WorkflowResponse getAllBulkExpenditure();
    WorkflowResponse getAllProgramExpenditure(ExpenditureType expenditureType);
}
