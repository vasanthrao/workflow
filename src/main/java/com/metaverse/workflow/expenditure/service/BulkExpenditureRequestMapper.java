package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.*;

public class BulkExpenditureRequestMapper {


    public static BulkExpenditure mapBulkExpenditure(BulkExpenditureRequest request,Agency agency)
    {
       return BulkExpenditure.builder()
                .agency(agency)
                .itemName(request.getItemName())
                .purchaseDate(DateUtil.stringToDate(request.getPurchaseDate(),"dd-MM-yyyy"))
                .purchasedQuantity(request.getPurchasedQuantity())
                .unitCost(request.getUnitCost())
                .totalCost(request.getUnitCost()*request.getPurchasedQuantity())
                .billNo(request.getBillNo())
                .billDate(DateUtil.stringToDate(request.getBillDate(),"dd-MM-yyyy"))
                .payeeName(request.getPayeeName())
                .bankName(request.getBankName())
                .ifscCode(request.getIfscCode())
                .modeOfPayment(request.getModeOfPayment().toString())
                .remarks(request.getRemarks())
                .uploadBillUrl(request.getUploadBillUrl())
               .build();

    }

}
