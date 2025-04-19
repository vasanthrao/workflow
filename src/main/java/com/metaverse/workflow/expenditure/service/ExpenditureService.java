package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.AgencyDetailsException;

public interface ExpenditureService {

    WorkflowResponse saveBulkExpenditure(BulkExpenditureRequest expenditureRequest) throws AgencyDetailsException;

}
