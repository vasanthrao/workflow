package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.BulkExpenditure;
import com.metaverse.workflow.model.ProgramExpenditure;

public class ExpenditureResponseMapper {


    public static BulkExpenditureResponse mapBulkExpenditure(BulkExpenditure expenditure)
    {
        return BulkExpenditureResponse.builder()
                .bulkExpenditureId(expenditure.getBulkExpenditureId())
                .agencyId(expenditure.getAgency().getAgencyId())
                .agencyName(expenditure.getAgency().getAgencyName())
                .itemName(expenditure.getItemName())
                .purchaseDate(expenditure.getBillDate())
                .headOfExpense(expenditure.getHeadOfExpense().getExpenseName())
                .purchasedQuantity(expenditure.getPurchasedQuantity())
                .unitCost(expenditure.getUnitCost())
                .totalCost(expenditure.getTotalCost())
                .billNo(expenditure.getBillNo())
                .billDate(expenditure.getBillDate())
                .payeeName(expenditure.getPayeeName())
                .bankName(expenditure.getBankName())
                .ifscCode(expenditure.getIfscCode())
                .modeOfPayment(expenditure.getModeOfPayment())
                .remarks(expenditure.getRemarks())
                .uploadBillUrl(expenditure.getUploadBillUrl())
                .build();
    }
    public static ProgramExpenditureResponse mapProgramExpenditure(ProgramExpenditure  expenditure)
    {
        return ProgramExpenditureResponse.builder()
                .programExpenditureId(expenditure.getProgramExpenditureId())
                .activityId(expenditure.getActivity().getActivityId())
                .activityName(expenditure.getActivity().getActivityName())
                .subActivityId(expenditure.getSubActivity().getSubActivityId())
                .subActivityName(expenditure.getSubActivity().getSubActivityName())
                .programId(expenditure.getProgram().getProgramId())
                .programName(expenditure.getProgram().getProgramTitle())
                .agencyId(expenditure.getAgency().getAgencyId())
                .agencyName(expenditure.getAgency().getAgencyName())
                .expenditureType(expenditure.getExpenditureType().toString())
                .headOfExpense(expenditure.getHeadOfExpense().getExpenseName())
                .cost(expenditure.getCost())
                .billNo(expenditure.getBillNo())
                .billDate(DateUtil.dateToString(expenditure.getBillDate(),"dd-MM-yyyy"))
                .payeeName(expenditure.getPayeeName())
                .bankName(expenditure.getBankName())
                .ifscCode(expenditure.getIfscCode())
                .modeOfPayment(expenditure.getModeOfPayment())
                .purpose(expenditure.getPurpose())
                .uploadBillUrl(expenditure.getUploadBillUrl())
                .build();

    }

    public static BulkExpenditureLookupResponse mapBulkExpenditureDetails(BulkExpenditure  expenditure)
    {
        return BulkExpenditureLookupResponse.builder()
                .purchasedQuantity(expenditure.getPurchasedQuantity())
                .unitCost(expenditure.getUnitCost())
                .purchaseDate(expenditure.getPurchaseDate())
                .consumedQuantity(expenditure.getConsumedQuantity())
                .availableQuantity(expenditure.getAvailableQuantity())
                .build();

    }

}
