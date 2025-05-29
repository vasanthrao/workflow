package com.metaverse.workflow.programoutcome.service;

import com.metaverse.workflow.common.util.DateUtil;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.model.Organization;
import com.metaverse.workflow.model.Participant;
import com.metaverse.workflow.model.outcomes.*;
import com.metaverse.workflow.programoutcome.dto.*;

import static org.hibernate.type.descriptor.java.JdbcDateJavaType.DATE_FORMAT;

public class OutcomeRequestMapper {

    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public static ONDCRegistration mapOndcRegistration(ONDCRegistrationRequest request, Agency agency, Participant participant, Organization organization) {
        return ONDCRegistration.builder()
                .ondcRegistrationNo(request.getOndcRegistrationNo())
                .ondcRegistrationDate(DateUtil.stringToDate(request.getOndcRegistrationDate(),DATE_FORMAT))
                .agency(agency)
                .organization(organization)
                .participant(participant)
                .build();
    }

    public static ONDCTransaction mapOndcTransaction(ONDCTransactionRequest request, ONDCRegistration ondcRegistration) {
        return ONDCTransaction.builder()
                .ondcRegistration(ondcRegistration)
                .transactionDate(DateUtil.stringToDate(request.getTransactionDate(),DATE_FORMAT))
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
                .udyamRegistationDate(DateUtil.stringToDate(request.getUdyamRegistrationDate(),DATE_FORMAT))
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
                .isInfluenced(request.getIsInfluenced())
                .agency(agency)
                .organization(organization)
                .participant(participant)
                .build();
    }


    public static GeMTransaction mapGeMTransaction(GeMTransactionRequest request, Agency agency, Participant participant, Organization organization) {
        return GeMTransaction.builder()
                .procurementDate(DateUtil.stringToDate(request.getProcurementDate(),DATE_FORMAT))
                .productName(request.getProductName())
                .unitOfMeasurement(request.getUnitOfMeasurement())
                .registeredAs(request.getRegisteredAs())
                .quantity(request.getQuantity())
                .productValue(request.getProductValue())
                .isInfluenced(request.getIsInfluenced())
                .agency(agency)
                .organization(organization)
                .participant(participant)
                .build();
    }
    public static TReDSRegistration mapTredsRegistration(TReDSRegistrationRequest request, Agency agency, Participant participant, Organization organization) {
        return TReDSRegistration.builder()
                .tredsRegistrationNo(request.getTrdseRegistrationNo())
                .tredsRegistrationDate(DateUtil.stringToDate(request.getTredsRegistrationDate(),DATE_FORMAT))
                .isInfluenced(request.getIsInfluenced())
                .agency(agency)
                .organization(organization)
                .participant(participant)
                .build();
    }
    public static TReDSTransaction mapTredsTransaction(TReDSTransactionRequest request, TReDSRegistration tredsRegistration) {
        return TReDSTransaction.builder()
                .tredsRegistration(tredsRegistration)
                .tredsTransactionDate(DateUtil.stringToDate(request.getTredsTransactionDate(),DATE_FORMAT))
                .invoiceNumber(request.getInvoiceNumber())
                .buyerName(request.getBuyerName())
                .tredsPlatformUsed(request.getTredsPlatformUsed())
                .invoiceAmount(request.getInvoiceAmount())
                .bidOpeningDate(DateUtil.stringToDate(request.getBidOpeningDate(),DATE_FORMAT))
                .winnerFinancier(request.getWinnerFinancier())
                .discountRateOffered(request.getDiscountRateOffered())
                .discountingFeeFor60Days(request.getDiscountingFeeFor60Days())
                .finalPayoutToMsme(request.getFinalPayoutToMsme())
                .paymentSettlementDate(DateUtil.stringToDate(request.getPaymentSettlementDate(),DATE_FORMAT))
                .buyerDueDateToPay(DateUtil.stringToDate(request.getBuyerDueDateToPay(),DATE_FORMAT))
                .repaymentDate(DateUtil.stringToDate(request.getRepaymentDate(),DATE_FORMAT))
                .build();
    }
    public static PMEGP mapPmegp(PMEGPRequest request, Agency agency, Participant participant, Organization organization)
    {
        return PMEGP.builder()
                .loanSanctionedDate(DateUtil.stringToDate(request.getLoanSanctionedDate(),DATE_FORMAT))
                .loanAmountReleased(request.getLoanAmountReleased())
                .govtSubsidy(request.getGovtSubsidy())
                .beneficiaryContribution(request.getBeneficiaryContribution())
                .totalAmountReleased(request.getTotalAmountReleased())
                .groundingDate(DateUtil.stringToDate(request.getGroundingDate(),DATE_FORMAT))
                .businessTurnover(request.getBusinessTurnover())
                .dateOfMarketLinkage(DateUtil.stringToDate(request.getDateOfMarketLinkage(),DATE_FORMAT))
                .volumeOfMarket(request.getVolumeOfMarket())
                .units(request.getUnits())
                .valueOfMarket(request.getValueOfMarket())
                .nameOfProductMarket(request.getNameOfProductMarket())
                .numberOfPersonsEmployed(request.getNumberOfPersonsEmployed())
                .isInfluenced(request.getIsInfluenced())
                .agency(agency)
                .participant(participant)
                .organization(organization)
                .build();
    }
    public static PMMY mapPmmy(PMMYRequest request, Agency agency, Participant participant, Organization organization) {
        return PMMY.builder()
                .loanAmountReleased(request.getLoanAmountReleased())
                .loanSanctionedDate(DateUtil.stringToDate(request.getLoanSanctionedDate(), DATE_FORMAT))
                .groundingDate(DateUtil.stringToDate(request.getGroundingDate(), DATE_FORMAT))
                .businessTurnover(request.getBusinessTurnover())
                .marketLinkageDate(DateUtil.stringToDate(request.getMarketLinkageDate(), DATE_FORMAT))
                .marketVolume(request.getMarketVolume())
                .units(request.getUnits())
                .marketValue(request.getMarketValue())
                .productMarketedName(request.getProductMarketedName())
                .isInfluenced(request.getIsInfluenced())
                .agency(agency)
                .participant(participant)
                .organization(organization)
                .build();
    }
    public static PMS mapPms(PMSRequest request, Agency agency,Participant participant, Organization organization) {
        return PMS.builder()
                .businessTurnover(request.getBusinessTurnover())
                .loanNumber(request.getLoanNumber())
                .purposeOfLoan(request.getPurposeOfLoan())
                .amountOfLoanReleased(request.getAmountOfLoanReleased())
                .dateOfLoanReleased(DateUtil.stringToDate(request.dateOfLoanReleased,DATE_FORMAT))
                .employmentCreatedDirect(request.getEmploymentCreatedDirect())
                .employmentCreatedInDirect(request.getEmploymentCreatedInDirect())
                .repaymentAmount(request.getRepaymentAmount())
                .dateOfRepayment(DateUtil.stringToDate(request.getDateOfRepayment(),DATE_FORMAT))
                .upiOrQrCode(request.getUpiOrQrCode())
                .onlinePlatformUsed(request.getOnlinePlatformUsed())
                .isInfluenced(request.getIsInfluenced())
                .agency(agency)
                .participant(participant)
                .organization(organization)
                .build();
    }public static ICScheme mapIcScheme(ICSchemeRequest request, Agency agency, Participant participant, Organization organization) {
        return ICScheme.builder()
                .industryName(request.getIndustryName())
                .location(request.getLocation())
                .typeOfMsme(request.getTypeOfMsme())
                .annualTurnover(request.getAnnualTurnover())
                .domesticSales(request.getDomesticSales())
                .exportMarket(request.getExportMarket())
                .employmentDirect(request.getEmploymentDirect())
                .employmentIndirect(request.getEmploymentIndirect())
                .dateOfExport(request.getDateOfExport())
                .valueOfExport(request.getValueOfExport())
                .exportMarketDetails(request.getExportMarketDetails())
                .isInfluenced(request.getIsInfluenced())
                .agency(agency)
                .participant(participant)
                .organization(organization)
                .build();
    }
    public static NSIC mapNsic(NSICRequest request, Agency agency, Participant participant, Organization organization)
    {
        return NSIC.builder()
                .govtAgencyProcured(request.getGovtAgencyProcured())
                .dateOfProcurement(DateUtil.stringToDate(request.getDateOfProcurement(),DATE_FORMAT))
                .typeOfProductSupplied(request.getTypeOfProductSupplied())
                .valueOfProcurement(request.getValueOfProcurement())
                .costSavingsTender(request.getCostSavingsTender())
                .isInfluenced(request.getIsInfluenced())
                .agency(agency)
                .participant(participant)
                .organization(organization)
                .build();
    }

}
