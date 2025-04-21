package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.DataException;

import java.util.List;

public interface BulkExpenditureService {
    BulkExpenditureTransactionResponse saveTransaction(BulkExpenditureTransactionRequest request) throws DataException;
    BulkExpenditureLookupResponse getBulkExpendituresByExpenseAndItem(BulkExpenditureLookupRequest request) throws DataException;
    List<String> getItemsByHeadOfExpense(Integer expenseId) throws DataException;
    WorkflowResponse getAllBulkExpenditureTransactionByProgram(Long programId);
}
