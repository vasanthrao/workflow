package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.enums.PaymentType;
import com.metaverse.workflow.model.HeadOfExpense;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ProgramExpenditureRequest {

    private Long activityId;
    private Long subActivityId;
    private Long programId;
    private Long agencyId;
    private ExpenditureType expenditureType;
    private Integer headOfExpenseId;
    private Double cost;
    private Integer billNo;
    private String billDate;
    private String payeeName;
    private String bankName;
    private String ifscCode;
    private PaymentType modeOfPayment;
    private String purpose;
    private String uploadBillUrl;

}
