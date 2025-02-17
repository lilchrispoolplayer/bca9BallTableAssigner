/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bcaleaguetableassigner;

/**
 *
 * @author c_dra
 */
public class LeagueMatch {

    private final int awayTeam;
    private final int homeTeam;
    private final String leagueMatch ;

    public LeagueMatch(String lm) {
        leagueMatch = lm.replace(" ", "");
        String[] teams = leagueMatch.split("@");
        awayTeam = Integer.parseInt(teams[0]);
        homeTeam = Integer.parseInt(teams[1]);
    }

    public int getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeam() {
        return homeTeam;
    }

    public String getLeagueMatch() {
        return String.format("%2s @ %-2s", awayTeam, homeTeam);
    }
}
