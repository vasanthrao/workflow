package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.enums.ExpenditureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ExpenditureResponse {
    private Long expenditureId;
    private Long activityId;
    private Long subActivityId;
    private Long programId;
    private String activityName;
    private String subActivityName;
    private String programName;
    private String expenditureType;
    private String headOfExpense;
    private Double amount;
    private Integer billNo;
    private Date billDate;
    private String payeeName;
    private String bankName;
    private String ifscCode;
    private String modeOfPayment;
    private String purpose;
    private String uploadBillUrl;
}
