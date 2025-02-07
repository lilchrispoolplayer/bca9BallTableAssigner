/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bca9balltableassigner;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author c_dra
 */
public class LeagueTeam {

    private final int id;
    private final Map<Integer, Integer> tableWeights;

    public LeagueTeam(int id, int numberOfTables) {
        this.id = id;
        tableWeights = new HashMap<>();
        for (int i = 1; i <= numberOfTables; i++) {
            tableWeights.put(i, 0);
        }
    }
    
    public int getId() {
        return id;
    }

    public int getTableWeight(int tableNumber) {
        return tableWeights.get(tableNumber);
    }

    public void incrementTableWeight(int tableNumber) {
        tableWeights.merge(tableNumber, 1, Integer::sum);
    }

    public void decrementTableWeight(int tableNumber) {
        tableWeights.merge(tableNumber, -1, Integer::sum);
    }

    public int getTableWeightSum() {
        return tableWeights.values().stream().mapToInt(Integer::intValue).sum();
    }
}
