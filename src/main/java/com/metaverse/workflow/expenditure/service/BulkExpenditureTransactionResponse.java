package com.metaverse.workflow.expenditure.service;

import lombok.Data;

import java.util.Date;

@Data
public class BulkExpenditureTransactionResponse {
    private Long id;
    private Integer consumedQuantity;
    private Double allocatedCost;

}
