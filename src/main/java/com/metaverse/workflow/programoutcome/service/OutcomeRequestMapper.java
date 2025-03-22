package com.metaverse.workflow.programoutcome.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.model.Organization;
import com.metaverse.workflow.model.Participant;
import com.metaverse.workflow.model.outcomes.ONDCRegistration;
import com.metaverse.workflow.model.outcomes.ONDCTransaction;
import com.metaverse.workflow.model.outcomes.UdyamRegistration;
import com.metaverse.workflow.programoutcome.dto.ONDCRegistrationRequest;
import com.metaverse.workflow.programoutcome.dto.ONDCTransactionRequest;
import com.metaverse.workflow.programoutcome.dto.StartupsOnFormalizationRegistrationRequest;

public class OutcomeRequestMapper {

    public static ONDCRegistration mapOndcRegistration(ONDCRegistrationRequest request, Agency agency, Participant participant, Organization organization) {
        return ONDCRegistration.builder()
                .ondcRegistrationNo(request.getOndcRegistrationNo())
                .ondcRegistrationDate(DateUtil.stringToDate(request.getOndcRegistrationDate(), "dd-mm-yyyy"))
                .agency(agency)
                .organization(organization)
                .participant(participant)
                .build();
    }

    public static ONDCTransaction mapOndcTransaction(ONDCTransactionRequest request, ONDCRegistration ondcRegistration) {
        return ONDCTransaction.builder()
                .ondcRegistration(ondcRegistration)
                .transactionDate(DateUtil.stringToDate(request.getTransactionDate(), "dd-mm-yyyy"))
                .transactionType(request.getTransactionType())
                .transactionValue(request.getTransactionValue())
                .productName(request.getProductName())
                .productUnits(request.getProductUnits())
                .productQuantity(request.getProductQuantity())
                .build();
    }

    public static UdyamRegistration mapStartupsOnFormalizationRegistration(StartupsOnFormalizationRegistrationRequest request, Agency agency, Participant participant, Organization organization)
    {
        return UdyamRegistration.builder()
                .udyamRegistationDate(DateUtil.stringToDate(request.getUdyamRegistrationDate(),"dd,MM,yyyy"))
                .udyamRegistrationNo(request.getUdyamRegistrationNo())
                .agency(agency)
                .organization(organization)
                .participant(participant)
                .build();
    }

}
