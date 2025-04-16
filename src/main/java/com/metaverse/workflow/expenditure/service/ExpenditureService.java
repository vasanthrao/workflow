package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.ActivityDetailsException;
import com.metaverse.workflow.exceptions.ProgramDetailsException;
import com.metaverse.workflow.exceptions.SubActivityDetailsException;

public interface ExpenditureService {

    WorkflowResponse saveExpenditure(ExpenditureRequest expenditureRequest) throws ActivityDetailsException, SubActivityDetailsException, ProgramDetailsException;
}
