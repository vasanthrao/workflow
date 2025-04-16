package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.expenditure.repository.ProgramExpenditureRepository;
import com.metaverse.workflow.model.*;

public class ExpenditureRequestMapper {
    public static ProgramExpenditure mapProgramExpenditure(ExpenditureRequest expenditureRequest, Activity activity, SubActivity subActivity, Program program)
    {
        return ProgramExpenditure.builder()
                .activity(activity)
                .subActivity(subActivity)
                .program(program)
                .expenditureType(expenditureRequest.getExpenditureType().toString())
                .headOfExpense(expenditureRequest.getHeadOfExpense())
                .amount(expenditureRequest.getAmount())
                .billNo(expenditureRequest.getBillNo())
                .billDate(DateUtil.stringToDate(expenditureRequest.getBillDate(),"dd-MM-yyyy"))
                .payeeName(expenditureRequest.getPayeeName())
                .bankName(expenditureRequest.getBankName())
                .ifscCode(expenditureRequest.getIfscCode())
                .modeOfPayment(expenditureRequest.getModeOfPayment())
                .purpose(expenditureRequest.getPurpose())
                .uploadBillUrl(expenditureRequest.getUploadBillUrl())
                .build();
    }
    public static CommonExpenditure mapCommonExpenditure(ExpenditureRequest expenditureRequest, Activity activity, SubActivity subActivity, Program program)
    {
        return CommonExpenditure.builder()
                .activity(activity)
                .subActivity(subActivity)
                .program(program)
                .expenditureType(expenditureRequest.getExpenditureType())
                .headOfExpense(expenditureRequest.getHeadOfExpense())
                .amount(expenditureRequest.getAmount())
                .billNo(expenditureRequest.getBillNo())
                .billDate(DateUtil.stringToDate(expenditureRequest.getBillDate(),"dd-MM-yyyy"))
                .payeeName(expenditureRequest.getPayeeName())
                .bankName(expenditureRequest.getBankName())
                .ifscCode(expenditureRequest.getIfscCode())
                .modeOfPayment(expenditureRequest.getModeOfPayment())
                .purpose(expenditureRequest.getPurpose())
                .uploadBillUrl(expenditureRequest.getUploadBillUrl())
                .build();
    }
}
