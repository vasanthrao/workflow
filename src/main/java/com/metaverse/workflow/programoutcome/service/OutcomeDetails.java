package com.metaverse.workflow.programoutcome.service;



import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OutcomeDetails {

    private List<OutcomeDataSet> columns;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class OutcomeDataSet {
        private String fieldName;
        private String fieldDisplayName;
        private String fieldType;

    }

}


