package com.metaverse.workflow.programoutcome.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StartupsOnFormalizationRegistrationRequest {

    private String udyamRegistrationDate;
    private String udyamRegistrationNo;
    private Long participantId;
    private Long organizationId;
    private Long agencyId;
}
