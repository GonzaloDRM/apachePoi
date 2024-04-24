package com.assesment.compass.service;

import com.assesment.compass.model.ExcelObject;
import com.assesment.compass.model.OutputRepeated;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ExcelService {

    public List<ExcelObject> excelValidator(MultipartFile excel) throws IOException {
        String fileName = excel.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        InputStream is = excel.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Workbook workbook;
        Sheet sheet;
        List<ExcelObject> excelObjectList = new ArrayList<>();

        if (extension.endsWith(".xls")) {
            workbook = new HSSFWorkbook(is);
        } else {
            workbook = new XSSFWorkbook(is);
        }

        sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        String[] headers = { "contactID", "name", "name1", "email", "postalZip", "address" };

        if (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell cell = row.getCell(0);
            for (int i = 1; headers.length > i; i++) {
                cell = row.getCell(i);
                if (!headers[i].equalsIgnoreCase(cell.getStringCellValue())) {
                    break;
                }
            }
        }

        Row row;

        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            ExcelObject excelObject = new ExcelObject();

            excelObject.setContractId((int) row.getCell(0).getNumericCellValue());
            excelObject.setName(row.getCell(1).getStringCellValue());
            excelObject.setName1(row.getCell(2).getStringCellValue());
            excelObject.setEmail(row.getCell(3).getStringCellValue());
            excelObject.setPostalZip((int) row.getCell(4).getNumericCellValue());
            excelObject.setAddress(row.getCell(5).getStringCellValue());

            excelObjectList.add(excelObject);
        }

        workbook.write(os);
        workbook.close();

        return excelObjectList;
    }

    public ByteArrayOutputStream findSimilarName(MultipartFile excel) throws IOException {
        List<ExcelObject> excelObjectList = excelValidator(excel);
        List<OutputRepeated> similarNames = new ArrayList<>();

        for(ExcelObject object : excelObjectList){
            for (ExcelObject object1 : excelObjectList){
                if (object1.getName().equals(object.getName()) && !Objects.equals(object.getContractId(), object1.getContractId())){
                    OutputRepeated outputRepeated = new OutputRepeated(object.getContractId(),
                            object1.getContractId(), "High");
                    similarNames.add(outputRepeated);
                } else if (object1.getName().contains(object.getName()) && !Objects.equals(object.getContractId(), object1.getContractId())) {
                    OutputRepeated outputRepeated = new OutputRepeated(object.getContractId(),
                            object1.getContractId(), "Low");
                    similarNames.add(outputRepeated);
                }
            }
        }

        return createExcelResponse(excel, similarNames);
    }

    public ByteArrayOutputStream createExcelResponse(MultipartFile excel, List<OutputRepeated> repeatedList) throws IOException {
        InputStream inputStream = excel.getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.createSheet("Repeated names");

        for (int i = 0; i< repeatedList.size()-10; i++){
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            Cell cell1 = row.createCell(1);
            Cell cell2 = row.createCell(2);
            cell.setCellValue(repeatedList.get(i).getContractIdSource());
            cell1.setCellValue(repeatedList.get(i).getContractIdMatch());
            cell2.setCellValue(repeatedList.get(i).getAccuracy());
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        workbook.close();

        return os;
    }
}
