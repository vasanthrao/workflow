package com.metaverse.workflow.programoutcome.service;

import com.metaverse.workflow.agency.repository.AgencyRepository;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.exceptions.DataException;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.model.Organization;
import com.metaverse.workflow.model.Participant;
import com.metaverse.workflow.model.outcomes.*;
import com.metaverse.workflow.organization.repository.OrganizationRepository;
import com.metaverse.workflow.participant.repository.ParticipantRepository;
import com.metaverse.workflow.programoutcome.dto.*;
import com.metaverse.workflow.programoutcome.repository.*;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProgramOutcomeServiceAdapter implements ProgramOutcomeService {

    @Autowired
    ProgramOutcomeTableRepository programOutcomeTableRepository;

    @Autowired
    ONDCRegistrationRepository ondcRegistrationRepository;

    @Autowired
    ONDCTransactionRepository ondcTransactionRepository;

    @Autowired
    UdyamRegistrationRepository udyamRegistrationRepository;

    @Autowired
    CGTMSETransactionRepository cgtmseTransactionRepository;

    @Autowired
    GeMTransactionRepository geMTransactionRepository;
    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    TReDSRegistrationRepository tredsRegistrationRepository;

    @Autowired
    TReDSTransactionRepository tredsTransactionRepository;

    @Autowired
    PMEGPRepository pmegpRepository;

    @Autowired
    PMMYRepository pmmyRepository;

    @Autowired
    PMSRepository pmsRepository;

    @Autowired
    ICSchemeRepository icSchemeRepository;

    @Autowired
    NSICRepository nsicRepository;

    @Override
    public List<ProgramOutcomeTable> getProgramOutcomeTables() {
        return programOutcomeTableRepository.findAll();
    }

    @Override
    public WorkflowResponse getOutcomeDetails(Long participantId, String outcome) {
        String className = "com.metaverse.workflow.programoutcome.dto." + outcome + "Request";
        try {
            Field[] fields = Class.forName(className).getFields();
            List<OutcomeDetails.OutcomeDataSet> columnList = new ArrayList<>();
            for (Field field : fields) {
                columnList.add(OutcomeDetails.OutcomeDataSet.builder().fieldDisplayName(getFieldDisplayName(field.getName())).fieldName(field.getName()).fieldType(getFieldType(field)).build());
            }
            switch (outcome) {
                case "ONDCTransaction": {
                    List<ONDCRegistration> ondcRegistration = ondcRegistrationRepository.findByParticipantId(participantId);
                    if (ondcRegistration == null || ondcRegistration.isEmpty())
                        return WorkflowResponse.builder().status(400).message("ONDC Registration not completed").build();
                    columnList.add(OutcomeDetails.OutcomeDataSet.builder().fieldDisplayName(getFieldDisplayName("ONDC Registration No")).fieldName("ondcRegistrationNo").fieldType("label").fieldValue(ondcRegistration.get(0).getOndcRegistrationNo()).build());
                }
                case "TReDSTransaction": {
                    List<TReDSRegistration> tReDSRegistrations = tredsRegistrationRepository.findByParticipantId(participantId);
                    if (tReDSRegistrations == null || tReDSRegistrations.isEmpty())
                        return WorkflowResponse.builder().status(400).message("TReDS Registration not completed").build();
                    columnList.add(OutcomeDetails.OutcomeDataSet.builder().fieldDisplayName(getFieldDisplayName("TReDS Registration No")).fieldName("tredsRegistrationNo").fieldType("label").fieldValue(tReDSRegistrations.get(0).getTredsRegistrationNo()).build());
                }
            }
            return WorkflowResponse.builder().status(200).message("Success").data(OutcomeDetails.builder().outcomeForm(columnList).build()).build();
        } catch (ClassNotFoundException ex) {
            log.error("Invalid out come name");
            return WorkflowResponse.builder().status(500).message("Internal server error").build();
        }
    }

    @Override
    public WorkflowResponse saveOutCome(String outcomeName, String data) throws ParseException, DataException {
        String status = "";
        JSONParser parser = new JSONParser();
        switch (outcomeName) {
            case "ONDCRegistration": {
                ONDCRegistrationRequest request = parser.parse(data, ONDCRegistrationRequest.class);
                Optional<Agency> agency = agencyRepository.findById(request.getAgencyId());
                if (!agency.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Agency").build();
                Optional<Participant> participant = participantRepository.findById(request.getParticipantId());
                if (!participant.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Participant").build();
                Optional<Organization> organization = organizationRepository.findById(request.getOrganizationId());
                if (!organization.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Organization").build();
                ondcRegistrationRepository.save(OutcomeRequestMapper.mapOndcRegistration(request, agency.get(), participant.get(), organization.get()));
                status = outcomeName + " Saved Successfully.";
                break;
            }
            case "ONDCTransaction": {
                ONDCTransactionRequest ondcTransactionRequest = parser.parse(data, ONDCTransactionRequest.class);
                List<ONDCRegistration> ondcRegistrationList = ondcRegistrationRepository.findByParticipantId(ondcTransactionRequest.getParticipantId());
                if (ondcRegistrationList == null || ondcRegistrationList.size() == 0)
                    return WorkflowResponse.builder().status(400).message("Invalid Ondc Registration").build();
                ondcTransactionRepository.save(OutcomeRequestMapper.mapOndcTransaction(ondcTransactionRequest, ondcRegistrationList.get(0)));
                status = outcomeName + " Saved Successfully.";
                break;
            }
            case "UdyamRegistration": {
                UdyamRegistrationRequest udyamResistrationRequest = parser.parse(data, UdyamRegistrationRequest.class);
                Optional<Agency> agency = agencyRepository.findById(udyamResistrationRequest.getAgencyId());
                if (!agency.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Agency").build();
                Optional<Participant> participant = participantRepository.findById(udyamResistrationRequest.getParticipantId());
                if (!participant.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Participant").build();
                Optional<Organization> organization = organizationRepository.findById(udyamResistrationRequest.getOrganizationId());
                if (!organization.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Organization").build();
                udyamRegistrationRepository.save(OutcomeRequestMapper.mapUdyamRegistration(udyamResistrationRequest, agency.get(), participant.get(), organization.get()));
                status = outcomeName + " Saved Successfully.";
                break;
            }
            case "CGTMSETransaction": {
                CGTMSETransactionRequest transactionRequest = parser.parse(data, CGTMSETransactionRequest.class);
                Optional<Agency> agency = agencyRepository.findById(transactionRequest.getAgencyId());
                if (!agency.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Agency").build();
                Optional<Participant> participant = participantRepository.findById(transactionRequest.getParticipantId());
                if (!participant.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Participant").build();
                Optional<Organization> organization = organizationRepository.findById(transactionRequest.getOrganizationId());
                if (!organization.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Organization").build();
                cgtmseTransactionRepository.save(OutcomeRequestMapper.mapCGTMSETransaction(transactionRequest, agency.get(), participant.get(), organization.get()));
                status = outcomeName + " Saved Successfully.";
                break;
            }
            case "GeMTransaction": {
                GeMTransactionRequest transactionRequest = parser.parse(data, GeMTransactionRequest.class);
                Optional<Agency> agency = agencyRepository.findById(transactionRequest.getAgencyId());
                if (!agency.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Agency").build();
                Optional<Participant> participant = participantRepository.findById(transactionRequest.getParticipantId());
                if (!participant.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Participant").build();
                Optional<Organization> organization = organizationRepository.findById(transactionRequest.getOrganizationId());
                if (!organization.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Organization").build();
                geMTransactionRepository.save(OutcomeRequestMapper.mapGeMTransaction(transactionRequest, agency.get(), participant.get(), organization.get()));
                status = outcomeName + " Saved Successfully.";
                break;
            }
            case "TReDSRegistration": {
                TReDSRegistrationRequest request = parser.parse(data, TReDSRegistrationRequest.class);
                Agency agency = agencyRepository.findById(request.getAgencyId())
                        .orElseThrow(() -> new DataException("Agency data not found", "AGENCY-DATA-NOT-FOUND", 400));

                Participant participant = participantRepository.findById(request.getParticipantId())
                        .orElseThrow(() -> new DataException("Participant data not found", "PARTICIPANT-DATA-NOT-FOUND", 400));

                Organization organization = organizationRepository.findById(request.getOrganizationId())
                        .orElseThrow(() -> new DataException("Organization data not found", "ORGANIZATION-DATA-NOT-FOUND", 400));
                tredsRegistrationRepository.save(OutcomeRequestMapper.mapTredsRegistration(request, agency, participant, organization));
                status = outcomeName + " Saved Successfully.";
                break;
            }
            case "TReDSTransaction": {
                TReDSTransactionRequest tredsTransactionRequest = parser.parse(data, TReDSTransactionRequest.class);
                List<TReDSRegistration> tredsRegistrationList = tredsRegistrationRepository.findByParticipantId(tredsTransactionRequest.getParticipantId());
                if (tredsRegistrationList == null || tredsRegistrationList.isEmpty())
                    return WorkflowResponse.builder().status(400).message("Invalid TReDS Registration").build();
                tredsTransactionRepository.save(OutcomeRequestMapper.mapTredsTransaction(tredsTransactionRequest, tredsRegistrationList.get(0)));
                status = outcomeName + " Saved Successfully.";
                break;
            }
            case "PMEGP": {
                PMEGPRequest request = parser.parse(data, PMEGPRequest.class);
                Agency agency = agencyRepository.findById(request.getAgencyId())
                        .orElseThrow(() -> new DataException("Agency data not found", "AGENCY-DATA-NOT-FOUND", 400));

                Participant participant = participantRepository.findById(request.getParticipantId())
                        .orElseThrow(() -> new DataException("Participant data not found", "PARTICIPANT-DATA-NOT-FOUND", 400));

                Organization organization = organizationRepository.findById(request.getOrganizationId())
                        .orElseThrow(() -> new DataException("Organization data not found", "ORGANIZATION-DATA-NOT-FOUND", 400));
               pmegpRepository.save(OutcomeRequestMapper.mapPmegp(request, agency, participant, organization));
                status = outcomeName + " Saved Successfully.";
                break;
            }
            case "PMMY": {
                PMMYRequest request = parser.parse(data, PMMYRequest.class);
                Agency agency = agencyRepository.findById(request.getAgencyId())
                        .orElseThrow(() -> new DataException("Agency data not found", "AGENCY-DATA-NOT-FOUND", 400));

                Participant participant = participantRepository.findById(request.getParticipantId())
                        .orElseThrow(() -> new DataException("Participant data not found", "PARTICIPANT-DATA-NOT-FOUND", 400));

                Organization organization = organizationRepository.findById(request.getOrganizationId())
                        .orElseThrow(() -> new DataException("Organization data not found", "ORGANIZATION-DATA-NOT-FOUND", 400));
                pmmyRepository.save(OutcomeRequestMapper.mapPmmy(request, agency, participant, organization));
                status = outcomeName + " Saved Successfully.";
                break;
            }
            case "PMS": {
                PMSRequest request = parser.parse(data, PMSRequest.class);
                Agency agency = agencyRepository.findById(request.getAgencyId())
                        .orElseThrow(() -> new DataException("Agency data not found", "AGENCY-DATA-NOT-FOUND", 400));

                Participant participant = participantRepository.findById(request.getParticipantId())
                        .orElseThrow(() -> new DataException("Participant data not found", "PARTICIPANT-DATA-NOT-FOUND", 400));

                Organization organization = organizationRepository.findById(request.getOrganizationId())
                        .orElseThrow(() -> new DataException("Organization data not found", "ORGANIZATION-DATA-NOT-FOUND", 400));
                pmsRepository.save(OutcomeRequestMapper.mapPms(request, agency, participant, organization));
                status = outcomeName + " Saved Successfully.";
                break;
            }
            case "ICScheme": {
                ICSchemeRequest request = parser.parse(data, ICSchemeRequest.class);
                Agency agency = agencyRepository.findById(request.getAgencyId())
                        .orElseThrow(() -> new DataException("Agency data not found", "AGENCY-DATA-NOT-FOUND", 400));

                Participant participant = participantRepository.findById(request.getParticipantId())
                        .orElseThrow(() -> new DataException("Participant data not found", "PARTICIPANT-DATA-NOT-FOUND", 400));

                Organization organization = organizationRepository.findById(request.getOrganizationId())
                        .orElseThrow(() -> new DataException("Organization data not found", "ORGANIZATION-DATA-NOT-FOUND", 400));
                icSchemeRepository.save(OutcomeRequestMapper.mapIcScheme(request, agency, participant, organization));
                status = outcomeName + " Saved Successfully.";
                break;
            }
            case "NSIC": {
                NSICRequest request = parser.parse(data, NSICRequest.class);
                Agency agency = agencyRepository.findById(request.getAgencyId())
                        .orElseThrow(() -> new DataException("Agency data not found", "AGENCY-DATA-NOT-FOUND", 400));

                Participant participant = participantRepository.findById(request.getParticipantId())
                        .orElseThrow(() -> new DataException("Participant data not found", "PARTICIPANT-DATA-NOT-FOUND", 400));

                Organization organization = organizationRepository.findById(request.getOrganizationId())
                        .orElseThrow(() -> new DataException("Organization data not found", "ORGANIZATION-DATA-NOT-FOUND", 400));
                nsicRepository.save(OutcomeRequestMapper.mapNsic(request, agency, participant, organization));
                status = outcomeName + " Saved Successfully.";
                break;
            }

        }
        return WorkflowResponse.builder().status(200).message("Success").data(status).build();
    }

    @Override
    public WorkflowResponse getOutcomeDetailsByName(String outcome) {
        switch (outcome) {
            case "ONDCRegistration": {
                List<ONDCRegistration> list = ondcRegistrationRepository.findAll();
            }
        }
        return null;
    }

    private String getFieldType(Field field) {
        Class outcomeClass = field.getType();
        if (field.getName().endsWith("Date") || field.getName().startsWith("date")) {
            return "date";
        } else if (outcomeClass.getName().equals("java.lang.String")) {
            return "text";
        } else if (outcomeClass.getName().equals("java.lang.Long") || outcomeClass.getName().equals("java.lang.Integer")) {
            return "number";
        } else if (outcomeClass.getName().equals("java.lang.Double") || outcomeClass.getName().equals("java.lang.Float")) {
            return "decimal";
        } else if (outcomeClass.getName().equals("java.lang.Character")) {
            return "character";
        }else if(field.getName().startsWith("is")) {
            return "radio button";
        }
        return "";
    }

    private String getFieldDisplayName(String fieldName) {
        String displayname = "";
        if (fieldName != null && fieldName.length() > 0) {
            displayname = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            displayname = displayname.replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2");
        }
        return displayname;
    }

}
