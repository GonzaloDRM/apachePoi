package com.assesment.compass.controller;

import com.assesment.compass.service.ExcelService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService){
        this.excelService = excelService;
    }

    @PostMapping("/similarNames")
    public ResponseEntity<?> similarNames(@RequestPart MultipartFile excel) throws IOException {
        ByteArrayOutputStream response = excelService.findSimilarName(excel);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(response.toByteArray());
    }

}
