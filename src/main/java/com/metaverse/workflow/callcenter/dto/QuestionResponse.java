package com.metaverse.workflow.callcenter.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {

    private Integer QuestionId;
    private String questionFieldType;
    private String question;
    private List<String> options ;
}
