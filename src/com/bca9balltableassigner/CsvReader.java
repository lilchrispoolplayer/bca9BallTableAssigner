/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bca9balltableassigner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author c_dra
 */
public class CsvReader {
    
    private List<String> schedule;
    
    public List<String> getSchedule() {
        return schedule;
    }
    
    public void loadSchedule(String scheduleFile) {
        try {
            // File must be in CSV format
            schedule = Files.readAllLines(Paths.get(scheduleFile));
        } catch (IOException ioe) {

        }
    }
    
    public boolean validateSchedule() {
        String[] recordPieces = schedule.get(0).split(",");
        for (String record : schedule) {
            // Check the record contains commas
            if (!record.contains(",")) {
                return false;
            }

            // Check the record follows the format
            String[] pieces = record.split(",");
            if (!pieces[0].matches("\\d+")) {
                return false;
            }
            for (int i = 1; i < pieces.length; i++) {
                if (!pieces[i].matches("\\d+ @ \\d+")) {
                    return false;
                }
            }
            
            // Check that records are the same length
            if (record.split(",").length != recordPieces.length) {
                return false;
            }
        }

        return true;
    }
}
