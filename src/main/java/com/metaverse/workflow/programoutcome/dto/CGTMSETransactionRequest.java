package com.metaverse.workflow.programoutcome.dto;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CGTMSETransactionRequest {


    public String creditApplicationDate;
    public String dprSubmissionDate;
    public String approvalDate;
    public String amountReleaseDate;
    public Double valueReleased;
    public Integer creditGuaranteePercentage;
    public String purpose;
    public String groundingDate;
    public String productName;
    public Integer productionPerMonth;
    public String marketType;
    public String marketingDate;
    public Integer marketVolume;
    public Double marketValue;
    public Double totalTurnover;
    public Integer employmentMale;
    public Integer employmentFemale;
    public String proposeOfEnterpriseUpgradation;
    public String dateOfGrounding;
    public Character influenced;
    private Long agencyId;
    private Long participantId;
    private Long organizationId;

}
