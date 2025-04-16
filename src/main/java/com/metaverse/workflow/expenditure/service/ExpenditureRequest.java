package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.model.Program;
import com.metaverse.workflow.model.SubActivity;
import lombok.*;

import java.lang.reflect.AccessibleObject;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ExpenditureRequest {
    private Long activityId;
    private Long subActivityId;
    private Long programId;
    private ExpenditureType expenditureType;
    private String headOfExpense;
    private Double amount;
    private Integer billNo;
    private String billDate;
    private String payeeName;
    private String bankName;
    private String ifscCode;
    private String modeOfPayment;
    private String purpose;
    private String uploadBillUrl;
}
