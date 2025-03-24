package com.metaverse.workflow.programoutcome.service;

import com.metaverse.workflow.agency.repository.AgencyRepository;
import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.Agency;
import com.metaverse.workflow.model.Organization;
import com.metaverse.workflow.model.Participant;
import com.metaverse.workflow.model.outcomes.ONDCRegistration;
import com.metaverse.workflow.model.outcomes.ProgramOutcomeTable;
import com.metaverse.workflow.organization.repository.OrganizationRepository;
import com.metaverse.workflow.participant.repository.ParticipantRepository;
import com.metaverse.workflow.programoutcome.dto.CGTMSETransactionRequest;
import com.metaverse.workflow.programoutcome.dto.ONDCRegistrationRequest;
import com.metaverse.workflow.programoutcome.dto.ONDCTransactionRequest;
import com.metaverse.workflow.programoutcome.dto.UdyamRegistrationRequest;
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
    UdyamResistrationRepository udyamResistrationRepository;

    @Autowired
    CGTMSETransactionRepository cgtmseTransactionRepository;

    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    ParticipantRepository participantRepository;

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
                    if (ondcRegistration == null || ondcRegistration.size() == 0)
                        return WorkflowResponse.builder().status(400).message("ONDC Registration not completed").build();
                    columnList.add(OutcomeDetails.OutcomeDataSet.builder().fieldDisplayName(getFieldDisplayName("ONDC Registration No")).fieldName("ondcRegistrationNo").fieldType("label").fieldValue(ondcRegistration.get(0).getOndcRegistrationNo()).build());
                }
            }
            return WorkflowResponse.builder().status(200).message("Success").data(OutcomeDetails.builder().outcomeForm(columnList).build()).build();
        } catch (ClassNotFoundException ex) {
            log.error("Invalid out come name");
            return WorkflowResponse.builder().status(500).message("Internal server error").build();
        }
    }

    @Override
    public WorkflowResponse saveOutCome(String outcomeName, String data) throws ParseException {
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
                udyamResistrationRepository.save(OutcomeRequestMapper.mapUdyamRegistration(udyamResistrationRequest, agency.get(), participant.get(), organization.get()));
                status = outcomeName + " Saved Successfully.";
                break;
            }
            case "CGTMSETransaction": {
                CGTMSETransactionRequest transactionRequest= parser.parse(data,CGTMSETransactionRequest.class);
                Optional<Agency> agency = agencyRepository.findById(transactionRequest.getAgencyId());
                if (!agency.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Agency").build();
                Optional<Participant> participant = participantRepository.findById(transactionRequest.getParticipantId());
                if (!participant.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Participant").build();
                Optional<Organization> organization = organizationRepository.findById(transactionRequest.getOrganizationId());
                if (!organization.isPresent())
                    return WorkflowResponse.builder().status(400).message("Invalid Organization").build();
                cgtmseTransactionRepository.save(OutcomeRequestMapper.mapCGTMSETransaction(transactionRequest,agency.get(),participant.get(),organization.get()));
                status = outcomeName + " Saved Successfully.";
                break;
            }
        }
        return WorkflowResponse.builder().status(200).message("Success").data(status).build();
    }

    private String getFieldType(Field field) {
        Class outcomeClass = field.getType();
        if (field.getName().endsWith("Date")) {
            return "date";
        } else if (outcomeClass.getName().equals("java.lang.String")) {
            return "text";
        } else if (outcomeClass.getName().equals("java.lang.Long") || outcomeClass.getName().equals("java.lang.Integer")) {
            return "number";
        } else if (outcomeClass.getName().equals("java.lang.Double") || outcomeClass.getName().equals("java.lang.Float")) {
            return "decimal";
        } else if (outcomeClass.getName().equals("java.lang.Character")) {
            return "character";
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
