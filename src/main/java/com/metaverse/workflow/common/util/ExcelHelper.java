package com.metaverse.workflow.common.util;

import com.metaverse.workflow.model.Participant;
import com.metaverse.workflow.model.Organization;
import com.metaverse.workflow.model.ParticipantTemp;
import com.metaverse.workflow.model.Program;
import com.metaverse.workflow.organization.repository.OrganizationRepository;
import com.metaverse.workflow.participant.repository.ParticipantRepository;
import com.metaverse.workflow.participant.repository.ParticipantTempRepository;
import com.metaverse.workflow.program.repository.ProgramRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ExcelHelper {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    ParticipantTempRepository participantTempRepository;

    public List<Participant> excelToParticipants(InputStream is, Long programId) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<Participant> participants = new ArrayList<>();
            List<ParticipantTemp> tempParticipants = new ArrayList<>();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Participant participant = new Participant();
                ParticipantTemp participantTemp = new ParticipantTemp();


                String organizationName = getCellValue(currentRow, 1);

                List<Organization> organizationList = organizationRepository.findAllByOrganizationNameIgnoreCase(organizationName);

                Optional<Program> programRes = programRepository.findById(programId);

                if (organizationList.size() == 1) {
                    participant.setParticipantName(getCellValue(currentRow, 0));
                    participant.setGender(getCellValue(currentRow, 1).charAt(2));
                    participant.setCategory(getCellValue(currentRow, 3));
                    participant.setDisability(getCellValue(currentRow, 4).charAt(0));
                    participant.setAadharNo(Long.parseLong(getCellValue(currentRow, 5)));
                    participant.setMobileNo(Long.parseLong(getCellValue(currentRow, 6)));
                    participant.setEmail(getCellValue(currentRow, 7));
                    participant.setDesignation(getCellValue(currentRow, 8));
                    participant.setIsParticipatedBefore(getCellValue(currentRow, 9).charAt(0));
                    participant.setPreviousParticipationDetails(getCellValue(currentRow, 10));
                    participant.setPreTrainingAssessmentConducted(getCellValue(currentRow, 11).charAt(0));
                    participant.setPostTrainingAssessmentConducted(getCellValue(currentRow, 12).charAt(0));
                    participant.setIsCertificateIssued(getCellValue(currentRow, 13).charAt(0));
                    String dateStr = getCellValue(currentRow, 14);
                    if (!dateStr.isEmpty()) {
                        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
                        participant.setCertificateIssueDate(date);
                    }
                    participant.setNeedAssessmentMethodology(getCellValue(currentRow, 15));
                    participant.setOrganization(organizationList.get(0));
                    participant.setPrograms(programRes.stream().toList());
                    participants.add(participant);
                }
                else {
                    participantTemp.setParticipantName(getCellValue(currentRow, 0));
                    participantTemp.setGender(getCellValue(currentRow, 1).charAt(2));
                    participantTemp.setCategory(getCellValue(currentRow, 3));
                    participantTemp.setDisability(getCellValue(currentRow, 4).charAt(0));
                    String aadharVal = getCellValue(currentRow, 5);
                    double parsedDouble = Double.parseDouble(aadharVal);
                    long aadharNumber = (long) parsedDouble;
                    participantTemp.setAadharNo(aadharNumber);
                    String mobileNo = getCellValue(currentRow, 6);
                    double mobileNumber = Double.parseDouble(mobileNo);
                    long mobile = (long) mobileNumber;
                    participantTemp.setMobileNo(mobile);
                    participantTemp.setEmail(getCellValue(currentRow, 7));
                    participantTemp.setDesignation(getCellValue(currentRow, 8));
                    participantTemp.setIsParticipatedBefore(getCellValue(currentRow, 9).charAt(0));
                    participantTemp.setPreviousParticipationDetails(getCellValue(currentRow, 10));
                    participantTemp.setPreTrainingAssessmentConducted(getCellValue(currentRow, 11).charAt(0));
                    participantTemp.setPostTrainingAssessmentConducted(getCellValue(currentRow, 12).charAt(0));
                    participantTemp.setIsCertificateIssued(getCellValue(currentRow, 13).charAt(0));
                    String dateStr = getCellValue(currentRow, 14);
                    if (!dateStr.isEmpty()) {
                        Date date = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(dateStr);
                        participantTemp.setCertificateIssueDate(date);
                    }
                    participantTemp.setNeedAssessmentMethodology(getCellValue(currentRow, 15));
                    participantTemp.setOrganization(organizationList.get(0));
                    participantTemp.setPrograms(programRes.stream().toList());
                    tempParticipants.add(participantTemp);
                }
            }

            workbook.close();
            if(!tempParticipants.isEmpty()) {
                participantTempRepository.saveAll(tempParticipants);
            }
            else {
                participantRepository.saveAll(participants);
            }
            return participants;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }

    private static String getCellValue(Row row, int index) {
        Cell cell = row.getCell(index);
        return (cell == null) ? "" : cell.toString().trim();
    }
}
