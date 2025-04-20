package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.exceptions.DataException;
import com.metaverse.workflow.model.BulkExpenditure;

import java.util.List;

public interface BulkExpenditureService {
    BulkExpenditureTransactionResponse saveTransaction(BulkExpenditureTransactionRequest request) throws DataException;
    BulkExpenditure getBulkExpendituresByExpenseAndItem(BulkExpenditureLookupRequest request) throws DataException;
    List<String> getItemsByHeadOfExpense(Integer expenseId) throws DataException;
}
