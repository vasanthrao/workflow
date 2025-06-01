package com.metaverse.workflow.programoutcometargets.service;

import com.metaverse.workflow.model.FinancialTarget;
import com.metaverse.workflow.model.PhysicalTarget;

public class TargetResponseMapper {

    public static FinancialTargetResponse mapFinancialTarget(FinancialTarget request)
    {
        return FinancialTargetResponse.builder()
                .financialTargetId(request.getFinancialTargetId())
                .financialYear(request.getFinancialYear())
                .q1(request.getQ1())
                .q2(request.getQ2())
                .q3(request.getQ3())
                .q4(request.getQ4())
                .agencyName(request.getAgency().getAgencyName())
                .outcomeTableName(request.getProgramOutcomeTable().getOutcomeTableName())
                .build();
    }
    public static PhysicalTargetResponse mapPhysicalTarget(PhysicalTarget request)
    {
        return PhysicalTargetResponse.builder()
                .physicalTargetId(request.getPhysicalTargetId())
                .financialYear(request.getFinancialYear())
                .q1(request.getQ1())
                .q2(request.getQ2())
                .q3(request.getQ3())
                .q4(request.getQ4())
                .agencyName(request.getAgency().getAgencyName())
                .outcomeTableName(request.getProgramOutcomeTable().getOutcomeTableName())
                .build();
    }
}
