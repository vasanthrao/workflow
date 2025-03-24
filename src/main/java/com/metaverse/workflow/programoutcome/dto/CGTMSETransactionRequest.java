package com.metaverse.workflow.programoutcome.dto;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CGTMSERegistrationRequest {

    private String proposeOfEnterpriseUpgradation;
    private String dateOfGrounding;
    private Boolean influenced;
    private Long participantId;
    private Long organizationId;
    private Long agencyId;
}
