package com.assesment.compass.controller;

import com.assesment.compass.service.ExcelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    private ExcelController controller;
    private MockMultipartFile mockMultipartFile;
    private ByteArrayOutputStream byteArrayOutputStream;
    @Mock
    private ExcelService excelService;

    @BeforeEach
    void setUp() {
        controller = new ExcelController(excelService);
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Test
    public void excelControllerTest() throws IOException {
        Path tempFile = Files.createTempFile("excel", ".xlsx");
        String content = "contractID,name*,name1,email,postalZip,address\n" +
                "1,John,Doe,john@example.com,12345,123 Main St\n" +
                "2,Jane,Smith,jane@example.com,67890,456 Elm St";
        Files.write(tempFile, content.getBytes());

        mockMultipartFile = new MockMultipartFile("file", "excel.xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", Files.readAllBytes(tempFile));

        when(excelService.findSimilarName(mockMultipartFile)).thenReturn(byteArrayOutputStream);

        assertNotNull(controller.similarNames(mockMultipartFile));
    }
}
