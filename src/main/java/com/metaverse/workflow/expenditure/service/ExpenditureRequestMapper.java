package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.*;

public class ExpenditureRequestMapper {

    public static BulkExpenditure mapBulkExpenditure(BulkExpenditureRequest request,Agency agency,HeadOfExpense headOfExpense)
    {
       return BulkExpenditure.builder()
                .agency(agency)
                .headOfExpense(headOfExpense)
                .itemName(request.getItemName())
                .purchaseDate(DateUtil.stringToDate(request.getPurchaseDate(),"dd-MM-yyyy"))
                .purchasedQuantity(request.getPurchasedQuantity())
                .availableQuantity(request.getPurchasedQuantity())
                .consumedQuantity(0)
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
    public static ProgramExpenditure mapProgramExpenditure(ProgramExpenditureRequest request,Activity activity,SubActivity subActivity,Program program, Agency agency,HeadOfExpense headOfExpense)
    {
        return ProgramExpenditure.builder()
                .activity(activity)
                .subActivity(subActivity)
                .program(program)
                .agency(agency)
                .expenditureType(request.getExpenditureType())
                .headOfExpense(headOfExpense)
                .cost(request.getCost())
                .billNo(request.getBillNo())
                .billDate(DateUtil.stringToDate(request.getBillDate(),"dd-MM-yyyy"))
                .payeeName(request.getPayeeName())
                .bankName(request.getBankName())
                .ifscCode(request.getIfscCode())
                .modeOfPayment(request.getModeOfPayment().toString())
                .purpose(request.getPurpose())
                .uploadBillUrl(request.getUploadBillUrl())
                .build();

    }

    public static ProgramExpenditure mapProgramExpenditureUpdate(ProgramExpenditureRequest request,Activity activity,SubActivity subActivity,Program program, Agency agency,HeadOfExpense headOfExpense)
    {
        return ProgramExpenditure.builder()
                .activity(activity)
                .subActivity(subActivity)
                .program(program)
                .agency(agency)
                .expenditureType(request.getExpenditureType())
                .headOfExpense(headOfExpense)
                .cost(request.getCost())
                .billNo(request.getBillNo())
                .billDate(DateUtil.stringToDate(request.getBillDate(),"dd-MM-yyyy"))
                .payeeName(request.getPayeeName())
                .bankName(request.getBankName())
                .ifscCode(request.getIfscCode())
                .modeOfPayment(request.getModeOfPayment().toString())
                .purpose(request.getPurpose())
                .uploadBillUrl(request.getUploadBillUrl())
                .build();

    }
}
