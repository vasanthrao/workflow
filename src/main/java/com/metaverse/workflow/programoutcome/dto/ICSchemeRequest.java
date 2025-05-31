package com.metaverse.workflow.programoutcome.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ICSchemeRequest {

    public String industryName;
    public String location;
    public String typeOfMsme;
    public Double annualTurnover;
    public Double domesticSales;
    public Double exportMarket;
    public Integer employmentDirect;
    public Integer employmentIndirect;
    public Date dateOfExport;
    public Double valueOfExport;
    public String exportMarketDetails;
    public Boolean isInfluenced;
    private Long agencyId;
    private Long participantId;
    private Long organizationId;
}
