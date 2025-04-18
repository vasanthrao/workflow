package com.metaverse.workflow.expenditure.service;

import com.metaverse.workflow.common.enums.ExpenditureType;
import com.metaverse.workflow.common.enums.PaymentType;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class BulkExpenditureRequest {
    private Long agencyId;
    private String itemName;
    private String purchaseDate;
    private Integer purchasedQuantity;
    private Double unitCost;
    private Integer billNo;
    private String billDate;
    private String payeeName;
    private String bankName;
    private String ifscCode;
    private PaymentType modeOfPayment;
    private String remarks;
    private String uploadBillUrl;
}
