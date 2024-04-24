package com.assesment.compass.service;

import com.assesment.compass.model.OutputRepeated;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
/*
    private ExcelService excelService;
    private OutputRepeated outputRepeated;
    private List<OutputRepeated> outputRepeatedList = new ArrayList<>();
    private MultipartFile mockMultipartFile;


    @BeforeEach
    void setUp() throws IOException {
        excelService = new ExcelService();

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        Sheet sheet = xssfWorkbook.createSheet("excel");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("test");


        FileOutputStream fileOutputStream = new FileOutputStream("excel.xlsx");
        xssfWorkbook.write(fileOutputStream);

        outputRepeated = OutputRepeated.builder().contractIdSource(1).contractIdMatch(2).Accuracy("High").build();
        outputRepeatedList.add(outputRepeated);
    }

    @Test
    public void findSimilarNamesTest() throws IOException {
        Path tempFile = Files.createTempFile("excel", ".xlsx");

        String content = "contractID,name*,name1,email,postalZip,address\n" +
                "1,John,Doe,john@example.com,12345,123 Main St\n" +
                "2,Jane,Smith,jane@example.com,67890,456 Elm St\n" +
                "3,John,Doe,john@example.com,12345,123 Main St\n";

        Files.write(tempFile, content.getBytes());

        mockMultipartFile = new MockMultipartFile("excel", "excel.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", Files.readAllBytes(tempFile));

        ByteArrayOutputStream response = excelService.findSimilarName(mockMultipartFile);

        assertNotNull(response);
    }*/


}
