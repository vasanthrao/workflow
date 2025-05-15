package com.metaverse.workflow.programoutcome.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GeMTransactionRequest {

    public String procurementDate;
    public String productName;
    public String unitOfMeasurement;
    public String registeredAs; // Buyer or Seller
    public Integer quantity;
    public Double productValue;
    public Character influenced;
    private Long agencyId;
    private Long participantId;
    private Long organizationId;

}
