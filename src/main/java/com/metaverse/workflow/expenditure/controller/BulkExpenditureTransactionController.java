package com.metaverse.workflow.expenditure.controller;

import com.metaverse.workflow.common.util.RestControllerBase;
import com.metaverse.workflow.exceptions.*;
import com.metaverse.workflow.expenditure.service.BulkExpenditureLookupRequest;
import com.metaverse.workflow.expenditure.service.BulkExpenditureTransactionRequest;
import com.metaverse.workflow.expenditure.service.BulkExpenditureTransactionResponse;
import com.metaverse.workflow.expenditure.service.BulkExpenditureTransactionService;
import com.metaverse.workflow.model.BulkExpenditure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bulk/transactions")
public class BulkExpenditureTransactionController {

    @Autowired
    private BulkExpenditureTransactionService service;

    @PostMapping("/save")
    public ResponseEntity<?> saveTransaction(
            @RequestBody BulkExpenditureTransactionRequest request) throws DataException {
        try {
            BulkExpenditureTransactionResponse response = service.saveTransaction(request);
            return ResponseEntity.ok(response);
        }
        catch (DataException ex) {
            return RestControllerBase.error(ex);
        }
    }

    @PostMapping("/lookup")
    public ResponseEntity<?> getExpendituresByExpenseAndItem(
            @RequestBody BulkExpenditureLookupRequest request) throws DataException {
        try {
            BulkExpenditure result = service.getBulkExpendituresByExpenseAndItem(request);
            return ResponseEntity.ok(result);
        }
        catch (DataException ex) {
            return RestControllerBase.error(ex);
        }
    }

    @GetMapping("/items")
    public ResponseEntity<List<String>> getItemsByExpense(@RequestParam Integer expenseId) {
        List<String> items = service.getItemsByHeadOfExpense(expenseId);
        return ResponseEntity.ok(items);
    }
}
