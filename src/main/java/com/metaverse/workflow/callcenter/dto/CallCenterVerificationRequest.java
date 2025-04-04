package com.metaverse.workflow.callcenter.dto;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallCenterVerificationRequest {

    private String verifiedBy;
    private String transactionDate;
    private List<Integer> questionId;
    private List<String> answers;
    private Integer verificationStatusId;
    

}
