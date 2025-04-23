package com.metaverse.workflow.pdfandexcelgenerater.controller;

import com.metaverse.workflow.pdfandexcelgenerater.service.ProgramExcelGenerator;
import com.metaverse.workflow.pdfandexcelgenerater.service.ProgramPdfGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class FileGeneratorController {

    @Autowired
    ProgramPdfGenerator programPdfGenerator;
    @Autowired
    ProgramExcelGenerator programExcelGenerator;

    @GetMapping(value="/program/openpdf" ,produces= MediaType.APPLICATION_PDF_VALUE)
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

    @GetMapping("/program/openexcel")
    public void generateExcelReport(HttpServletResponse response) throws IOException
    {
        response.setContentType("apllication/octet-stream");
//        String headerKey="Content-Disposition";
//        String headerValue="attachment;fileName=users.xls";
        response.setHeader("Content-Disposition", "attachment;fileName=users.xls");
        programExcelGenerator.generateProgramsExcel(response);
    }


}
