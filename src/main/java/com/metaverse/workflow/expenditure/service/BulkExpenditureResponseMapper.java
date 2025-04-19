package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.BulkExpenditure;
import com.metaverse.workflow.model.ProgramExpenditure;

public class BulkExpenditureResponseMapper {



    public static BulkExpenditureResponse mapBulkExpenditure(BulkExpenditure expenditure)
    {
        return BulkExpenditureResponse.builder()
                .bulkExpenditureId(expenditure.getBulkExpenditureId())
                .agencyId(expenditure.getAgency().getAgencyId())
                .agencyName(expenditure.getAgency().getAgencyName())
                .itemName(expenditure.getItemName())
                .purchaseDate(expenditure.getBillDate())
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

}
