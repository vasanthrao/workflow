package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.*;
import com.metaverse.workflow.model.BulkExpenditure;
import com.metaverse.workflow.model.HeadOfExpense;

import java.util.List;

public interface ExpenditureService {

    
    WorkflowResponse saveBulkExpenditure(BulkExpenditureRequest expenditureRequest) throws DataException;
    WorkflowResponse saveProgramExpenditure(ProgramExpenditureRequest expenditureRequest) throws DataException;
    BulkExpenditureTransactionResponse saveTransaction(BulkExpenditureTransactionRequest request) throws DataException;
    WorkflowResponse getAllBulkExpenditure();
    WorkflowResponse getAllProgramExpenditure(ExpenditureType expenditureType);
    WorkflowResponse getAllProgramExpenditureByProgram(ExpenditureType expenditureType,Long programId);
    WorkflowResponse getAllBulkExpenditureTransactionByProgram(Long programId);
    BulkExpenditureLookupResponse getBulkExpendituresByExpenseAndItem(BulkExpenditureLookupRequest request) throws DataException;
    List<String> getItemsByHeadOfExpense(Integer expenseId) throws DataException;
    List<HeadOfExpense> getAllHeadOfExpenses();
}

