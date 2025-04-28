package com.metaverse.workflow.common.util;

import com.metaverse.workflow.common.response.WorkflowResponse;
import com.metaverse.workflow.model.Participant;
import com.metaverse.workflow.model.Organization;
import com.metaverse.workflow.organization.repository.OrganizationRepository;
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

    public List<Participant> excelToParticipants(InputStream is, Organization organization) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<Participant> participants = new ArrayList<>();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Participant participant = new Participant();

                String organizationName = getCellValue(currentRow, 1);

                List<Organization> organizationList = organizationRepository.findAllByOrganizationNameIgnoreCase(organizationName);

                if (organizationList.size() == 1) {
                    participant.setParticipantName(getCellValue(currentRow, 0));
                    participant.setParticipantName(getCellValue(currentRow, 0));
                    participant.setGender(getCellValue(currentRow, 1).charAt(0));
                    participant.setCategory(getCellValue(currentRow, 2));
                    participant.setDisability(getCellValue(currentRow, 3).charAt(0));
                    participant.setAadharNo(Long.parseLong(getCellValue(currentRow, 4)));
                    participant.setMobileNo(Long.parseLong(getCellValue(currentRow, 5)));
                    participant.setEmail(getCellValue(currentRow, 6));
                    participant.setDesignation(getCellValue(currentRow, 7));
                    participant.setIsParticipatedBefore(getCellValue(currentRow, 8).charAt(0));
                    participant.setPreviousParticipationDetails(getCellValue(currentRow, 9));
                    participant.setPreTrainingAssessmentConducted(getCellValue(currentRow, 10).charAt(0));
                    participant.setPostTrainingAssessmentConducted(getCellValue(currentRow, 11).charAt(0));
                    participant.setIsCertificateIssued(getCellValue(currentRow, 12).charAt(0));
                    String dateStr = getCellValue(currentRow, 13);
                    if (!dateStr.isEmpty()) {
                        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
                        participant.setCertificateIssueDate(date);
                    }
                    participant.setNeedAssessmentMethodology(getCellValue(currentRow, 14));
                    participant.setOrganization(organizationList.get(0));
                    participants.add(participant);
                }
            }

            workbook.close();
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
