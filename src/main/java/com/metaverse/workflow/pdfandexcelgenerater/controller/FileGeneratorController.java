package com.metaverse.workflow.pdfandexcelgenerater.controller;

import com.metaverse.workflow.common.util.RestControllerBase;
import com.metaverse.workflow.exceptions.DataException;
import com.metaverse.workflow.pdfandexcelgenerater.service.ProgramExcelGenerator;
import com.metaverse.workflow.pdfandexcelgenerater.service.ProgramPdfGenerator;
import com.metaverse.workflow.pdfandexcelgenerater.service.ProgramSummeryExcelGenerator;
import com.metaverse.workflow.pdfandexcelgenerater.service.ProgramSummeryPdfGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class FileGeneratorController {

    @Autowired
    ProgramPdfGenerator programPdfGenerator;
    @Autowired
    ProgramExcelGenerator programExcelGenerator;
    @Autowired
    ProgramSummeryPdfGenerator programSummeryPdfGenerator;
    @Autowired
    ProgramSummeryExcelGenerator programSummeryExcelGenerator;
    @GetMapping(value="/program/pdf" ,produces= MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generatePdfReport(HttpServletResponse response) throws IOException
    {
        ByteArrayInputStream bis = programPdfGenerator.generateProgramsPdf(response);
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=users.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));

    }

    @GetMapping("/program/excel")
    public void generateExcelReport(HttpServletResponse response) throws IOException
    {
        response.setContentType("apllication/octet-stream");
//        String headerKey="Content-Disposition";
//        String headerValue="attachment;fileName=users.xls";
        response.setHeader("Content-Disposition", "attachment;fileName=users.xls");
        programExcelGenerator.generateProgramsExcel(response);
    }
    @GetMapping(value="/program/summery/pdf/{programId}" ,produces= MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateProgramSummeryPDF(@PathVariable Long programId, HttpServletResponse response)  {

        ByteArrayInputStream bis = null;
        try {
            bis = programSummeryPdfGenerator.generateProgramsSummeryPdf(response, programId);
        } catch (DataException e) {
             return RestControllerBase.error(e);
        }
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=users.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));

    }
    @GetMapping("/program/summery/excel/{programId}")
    public void generateProgramSummeryExcel(@PathVariable Long programId,HttpServletResponse response) throws IOException, DataException {
        response.setContentType("apllication/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName=users.xls");
        programSummeryExcelGenerator.generateProgramsExcel(response,programId);

    }

}
