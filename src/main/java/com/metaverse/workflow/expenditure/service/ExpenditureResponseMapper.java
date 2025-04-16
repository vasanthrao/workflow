package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.CommonExpenditure;
import com.metaverse.workflow.model.ProgramExpenditure;

public class ExpenditureResponseMapper {
    
    public static ExpenditureResponse mapProgramExpenditure(ProgramExpenditure expenditure)
    {
        return ExpenditureResponse.builder()
                .expenditureId(expenditure.getProgramExpenditureId())
                .activityId(expenditure.getActivity().getActivityId())
                .subActivityId(expenditure.getSubActivity().getSubActivityId())
                .programId(expenditure.getProgram().getProgramId())
                .activityName(expenditure.getActivity().getActivityName())
                .subActivityName(expenditure.getSubActivity().getSubActivityName())
                .programName(expenditure.getProgram().getProgramTitle())
                .expenditureType(expenditure.getExpenditureType())
                .headOfExpense(expenditure.getHeadOfExpense())
                .amount(expenditure.getAmount())
                .billNo(expenditure.getBillNo())
                .billDate(expenditure.getBillDate())
                .payeeName(expenditure.getPayeeName())
                .bankName(expenditure.getBankName())
                .ifscCode(expenditure.getIfscCode())
                .modeOfPayment(expenditure.getModeOfPayment())
                .purpose(expenditure.getPurpose())
                .uploadBillUrl(expenditure.getUploadBillUrl())
                .build();
    }
    public static ExpenditureResponse mapCommonExpenditure(CommonExpenditure expenditure)
    {
        return ExpenditureResponse.builder()
                .expenditureId(expenditure.getCommonExpenditureId())
                .activityId(expenditure.getActivity().getActivityId())
                .subActivityId(expenditure.getSubActivity().getSubActivityId())
                .programId(expenditure.getProgram().getProgramId())
                .activityName(expenditure.getActivity().getActivityName())
                .subActivityName(expenditure.getSubActivity().getSubActivityName())
                .programName(expenditure.getProgram().getProgramTitle())
                .expenditureType(expenditure.getExpenditureType().toString())
                .headOfExpense(expenditure.getHeadOfExpense())
                .amount(expenditure.getAmount())
                .billNo(expenditure.getBillNo())
                .billDate(expenditure.getBillDate())
                .payeeName(expenditure.getPayeeName())
                .bankName(expenditure.getBankName())
                .ifscCode(expenditure.getIfscCode())
                .modeOfPayment(expenditure.getModeOfPayment())
                .purpose(expenditure.getPurpose())
                .uploadBillUrl(expenditure.getUploadBillUrl())
                .build();
    }
}
