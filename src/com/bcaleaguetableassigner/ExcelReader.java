/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bcaleaguetableassigner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author c_dra
 */
public class ExcelReader {

    private Workbook workbook;
    private List<String> schedule;
    private Map<String, String> teamNames;

    public List<String> getSchedule() {
        return schedule;
    }

    public void loadSchedule(String scheduleFile) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(scheduleFile))) {
            workbook = new XSSFWorkbook(fileInputStream);
            teamNames = new HashMap<>();
            for(Row row : workbook.getSheetAt(1)) {
                teamNames.put(String.valueOf((int)row.getCell(0).getNumericCellValue()), row.getCell(1).getStringCellValue());
            }
            schedule = new ArrayList<>();
            for (Row row : workbook.getSheetAt(0)) {
                schedule.add(getRowAsString(row, ","));
            }
        } catch (IOException e) {
        }
    }

    public boolean validateSchedule() {
        // Sheet 0 is the schedule
        Sheet scheduleSheet = workbook.getSheetAt(0);
        Row firstRow = scheduleSheet.getRow(0);
        int firstRowCellCount = getCellCount(firstRow);
        for (Row row : scheduleSheet) {
            if (row.getCell(0).getStringCellValue().matches("\\d+")) {
                return false;
            }
            
            for (int i = 1; row.getCell(i).getCellType() != CellType.BLANK; i++) {
                if (!row.getCell(i).getStringCellValue().matches("\\d+ @ \\d+")) {
                    return false;
                }
            }

            // Check that records are the same length
            if (getCellCount(row) != firstRowCellCount) {
                return false;
            }
        }

        return true;
    }
    
    protected int getCellCount(Row row) {
        int index = 0;
        while (row.getCell(index).getCellType() != CellType.BLANK) {
            index++;
        }
        
        return index;
    }
    
    public static String getRowAsString(Row row, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            // Use DataFormatter to get cell value as String, handling different cell types
            DataFormatter formatter = new DataFormatter();
            String cellValue = formatter.formatCellValue(cell);
            sb.append(cellValue);
            if (i < row.getLastCellNum() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
    
    public Map<String, String> getTeamNames() {
        return teamNames;
    }
}
