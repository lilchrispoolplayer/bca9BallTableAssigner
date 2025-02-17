/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bcaleaguetableassigner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author c_dra
 */
public class Schedule {

    private final String scheduleFile;
    private List<ScheduleRecord> scheduleRecords;
    private CsvReader csvReader;
    private ExcelReader excelReader;

    public Schedule(String file) {
        scheduleFile = file;
        if (scheduleFile.endsWith(".csv")) {
            csvReader = new CsvReader();
            csvReader.loadSchedule(scheduleFile);
        } else if (scheduleFile.endsWith(".xls") || scheduleFile.endsWith(".xlsx")) {
            excelReader = new ExcelReader();
            excelReader.loadSchedule(scheduleFile);
        }
    }

    public void processSchedule() {
        scheduleRecords = new ArrayList<>();
        
        if (scheduleFile.endsWith(".csv")) {
            List<String> schedule = csvReader.getSchedule();
            schedule.stream().forEach(record -> scheduleRecords.add(new ScheduleRecord(record, schedule.size())));
        } else if (scheduleFile.endsWith(".xls") || scheduleFile.endsWith(".xlsx")) {
            List<String> schedule = excelReader.getSchedule();
            schedule.stream().forEach(record -> scheduleRecords.add(new ScheduleRecord(record, schedule.size())));
        }
    }
    
    public boolean validateSchedule() {
        boolean valid = true;
        if (scheduleFile.endsWith(".csv")) {
            valid = csvReader.validateSchedule();
        }
        
        return valid;
    }

    public ScheduleRecord getScheduledRecord(int index) {
        if (index < 0 || scheduleRecords.size() - 1 < index) {
            return null;
        }

        return scheduleRecords.get(index);
    }

    public List<ScheduleRecord> getAllScheduledRecords() {
        return scheduleRecords;
    }

    public int getNumberOfWeeksInSchedule() {
        return scheduleRecords.size();
    }
    
    public Map<String, String> getTeamNames() {
        return excelReader.getTeamNames();
    }
}
