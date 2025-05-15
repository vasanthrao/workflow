package com.metaverse.workflow.programoutcome.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.model.Organization;
import com.metaverse.workflow.model.Participant;
import com.metaverse.workflow.model.outcomes.*;
import com.metaverse.workflow.programoutcome.dto.*;

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

    public static UdyamRegistration mapUdyamRegistration(UdyamRegistrationRequest request, Agency agency, Participant participant, Organization organization)
    {
        return UdyamRegistration.builder()
                .udyamRegistationDate(DateUtil.stringToDate(request.getUdyamRegistrationDate(),"dd-mm-yyyy"))
                .udyamRegistrationNo(request.getUdyamRegistrationNo())
                .agency(agency)
                .organization(organization)
                .participant(participant)
                .build();
    }

    public static CGTMSETransaction mapCGTMSETransaction(CGTMSETransactionRequest request, Agency agency, Participant participant, Organization organization)
    {
        return CGTMSETransaction.builder()
                .creditApplicationDate(DateUtil.stringToDate(request.getCreditApplicationDate(),"dd-MM-yyyy"))
                .dprSubmissionDate(DateUtil.stringToDate(request.getDprSubmissionDate(),"dd-MM-yyyy"))
                .approvalDate(DateUtil.stringToDate(request.getApprovalDate(),"dd-MM-yyyy"))
                .amountReleaseDate(DateUtil.stringToDate(request.getAmountReleaseDate(),"dd-MM-yyyy"))
                .valueReleased(request.getValueReleased())
                .creditGuaranteePercentage(request.getCreditGuaranteePercentage())
                .purpose(request.getPurpose())
                .groundingDate(DateUtil.stringToDate(request.getGroundingDate(),"dd-MM-yyyy"))
                .productName(request.getProductName())
                .productionPerMonth(request.getProductionPerMonth())
                .marketType(request.getMarketType())
                .marketingDate(DateUtil.stringToDate(request.getMarketingDate(),"dd-MM-yyyy"))
                .marketVolume(request.getMarketVolume())
                .marketValue(request.getMarketValue())
                .totalTurnover(request.getTotalTurnover())
                .employmentMale(request.getEmploymentMale())
                .employmentFemale(request.getEmploymentFemale())
                .proposeOfEnterpriseUpgradation(request.getProposeOfEnterpriseUpgradation())
                .dateOfGrounding(DateUtil.stringToDate(request.getDateOfGrounding(),"dd-MM-yyyy"))
                .influenced(request.getInfluenced()=='y'?true :false)
                .agency(agency)
                .organization(organization)
                .participant(participant)
                .build();
    }


    public static GeMTransaction mapGeMTransaction(GeMTransactionRequest request, Agency agency, Participant participant, Organization organization) {
        return GeMTransaction.builder()
                .procurementDate(DateUtil.stringToDate(request.getProcurementDate(),"dd-MM-yyyy"))
                .productName(request.getProductName())
                .unitOfMeasurement(request.getUnitOfMeasurement())
                .registeredAs(request.getRegisteredAs())
                .quantity(request.getQuantity())
                .productValue(request.getProductValue())
                .influenced(request.getInfluenced())
                .agency(agency)
                .organization(organization)
                .participant(participant)
                .build();
    }
}
