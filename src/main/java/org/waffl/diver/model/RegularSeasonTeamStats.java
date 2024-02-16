package org.waffl.diver.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RegularSeasonTeamStats {
    private int rank;
    private String team;
    private int allTimeRegularSeasonWins;
    private int allTimeRegularSeasonLosses;
    private double allTimeWinPercentage;
    private double gb; // Games Behind
    private double allTimeTeamRotoScore;
    private double allTimeTotalPointsScored;
    private double allTimePointsPerGame;
    private double allTimeTotalPointsAgainst;
    private double allTimeTotalPointsAgainstPerGame;
    private double averageMarginPerGame;
    private int allTimeLongestRegularSeasonWinningStreak;
    private int allTimeLongestRegularSeasonLosingStreak;
    private int currentWStreak;
    private int currentLStreak;
    private double bestScoringGame;
    private String bestScoringGameDate;
    private double worstScoringGame;
    private String worstScoringGameDate;
    private String bestSeasonRecord;
    private int bestSeasonYear;
    private String worstSeasonRecord;
    private int worstSeasonYear;
    private double bestSeasonPoints;
    private double bsPPG; // Best Season Points Per Game
    private int bsYear; // Best Season Year
    private double worstSeasonPoints;
    private double wsPPG; // Worst Season Points Per Game
    private int wsYear; // Worst Season Year
    private int numberOfWinningSeasons;
    private int numberOfLosingSeasons;
    private int numberOfSeasonsInWAFFL;
}