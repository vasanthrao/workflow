package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.activity.repository.ActivityRepository;
import com.metaverse.workflow.activity.repository.SubActivityRepository;
import com.metaverse.workflow.agency.repository.AgencyRepository;
import com.metaverse.workflow.exceptions.DataException;
import com.metaverse.workflow.expenditure.repository.BulkExpenditureRepository;
import com.metaverse.workflow.expenditure.repository.BulkExpenditureTransactionRepository;
import com.metaverse.workflow.expenditure.repository.HeadOfExpenseRepository;
import com.metaverse.workflow.model.*;
import com.metaverse.workflow.program.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulkExpenditureTransactionService implements BulkExpenditureService {

    @Autowired
    private BulkExpenditureTransactionRepository transactionRepo;

    @Autowired
    private ActivityRepository activityRepo;

    @Autowired
    private BulkExpenditureRepository expenditureRepo;

    @Autowired
    private SubActivityRepository subActivityRepo;

    @Autowired
    private ProgramRepository programRepo;

    @Autowired
    private AgencyRepository agencyRepo;

    @Autowired
    private HeadOfExpenseRepository expenseRepo;

    @Override
    public BulkExpenditureTransactionResponse saveTransaction(BulkExpenditureTransactionRequest request) throws DataException {

         BulkExpenditure bulkExpenditure = expenditureRepo.findById(request.getBulkExpenditureId())
                 .orElseThrow(() -> new DataException("Bulk expenditure data not found", "BULK-EXPENDITURE-DATA-NOT-FOUND", 400));

        Activity activity = activityRepo.findById(request.getActivityId())
                .orElseThrow(() -> new DataException("Activity data not found", "ACTIVITY-DATA-NOT-FOUND", 400));

        SubActivity subActivity = subActivityRepo.findById(request.getSubActivityId())
                .orElseThrow(() -> new DataException("Sub Activity data not found", "SUB-ACTIVITY-DATA-NOT-FOUND", 400));

        Program program = programRepo.findById(request.getProgramId())
                .orElseThrow(() -> new DataException("Program data not found", "PROGRAM-DATA-NOT-FOUND", 400));


        Agency agency = agencyRepo.findById(request.getAgencyId())
                .orElseThrow(() -> new DataException("Agency data not found", "AGENCY-DATA-NOT-FOUND", 400));

        HeadOfExpense headOfExpense = expenseRepo.findById(request.getHeadOfExpenseId())
                .orElseThrow(() -> new DataException("Head of expense data not found", "HEAD-OF-EXPENSE-DATA-NOT-FOUND", 400));


        BulkExpenditureTransaction transaction = BulkExpenditureTransaction.builder()
                .activity(activity)
                .expenditure(bulkExpenditure)
                .subActivity(subActivity)
                .program(program)
                .agency(agency)
                .headOfExpense(headOfExpense)
                .consumedQuantity(request.getConsumedQuantity())
                .allocatedCost(request.getAllocatedCost())
                .build();

        BulkExpenditureTransaction saved = transactionRepo.save(transaction);

        if (expenditureRepo != null && request.getConsumedQuantity() != null) {
            int updatedAvailableQty = 0;
            if (bulkExpenditure.getAvailableQuantity() > request.getConsumedQuantity()) {
                updatedAvailableQty = bulkExpenditure.getAvailableQuantity() - request.getConsumedQuantity();
            }
            bulkExpenditure.setPurchasedQuantity(updatedAvailableQty);
            bulkExpenditure.setConsumedQuantity(bulkExpenditure.getConsumedQuantity() + request.getConsumedQuantity());
            expenditureRepo.save(bulkExpenditure);
        }

        BulkExpenditureTransactionResponse response = new BulkExpenditureTransactionResponse();
        response.setId(saved.getBulkExpenditureTransactionId());
        response.setConsumedQuantity(saved.getConsumedQuantity());
        response.setAllocatedCost(saved.getAllocatedCost());
        response.setCreatedOn(saved.getCreatedOn());
        response.setUpdatedOn(saved.getUpdatedOn());

        return response;
    }

    @Override
    public BulkExpenditure getBulkExpendituresByExpenseAndItem(BulkExpenditureLookupRequest request) throws DataException {
        HeadOfExpense headOfExpense = expenseRepo.findById(request.getExpenseId())
                .orElseThrow(() -> new DataException("Head of Expense not found", "HEAD-OF-EXPENSE-NOT-FOUND", 400));
        return expenditureRepo.findByHeadOfExpenseAndItemNameIgnoreCase(headOfExpense, request.getItemName());
    }

    @Override
    public List<String> getItemsByHeadOfExpense(Integer expenseId) {
        return expenditureRepo.findDistinctItemNamesByHeadOfExpense(expenseId);
    }
}
