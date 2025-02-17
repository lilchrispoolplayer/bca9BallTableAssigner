/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bcaleaguetableassigner;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author c_dra
 */
public class ScheduleRecord {

    private final int leagueWeek;
    private final List<LeagueMatch> leagueMatches;

    public ScheduleRecord(String record, int numberOfWeeks) {
        String[] pieces = record.split(",");
        leagueWeek = Integer.parseInt(pieces[0]);

        leagueMatches = new ArrayList<>();
        for (int i = 1; i < pieces.length; i++) {
          leagueMatches.add(new LeagueMatch(pieces[i]));
        }
    }

    public int getLeagueWeek() {
        return leagueWeek;
    }

    public int getLeagueMatchesCount() {
        return leagueMatches.size();
    }

    public List<LeagueMatch> getAllLeagueMatches() {
        return leagueMatches;
    }

    public LeagueMatch getMatchWithTeamNumber(int teamNumber) {
        return leagueMatches.stream().filter(lm -> lm.getAwayTeam() == teamNumber || lm.getHomeTeam() == teamNumber).findFirst().get();
    }

    public LeagueMatch getLeagueMatch(String leagueMatch) {
        return leagueMatches.stream().filter(lm -> lm.getLeagueMatch().equalsIgnoreCase(leagueMatch)).findFirst().get();
    }
}
