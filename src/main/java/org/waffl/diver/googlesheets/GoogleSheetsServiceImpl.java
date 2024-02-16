package org.waffl.diver.googlesheets;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.waffl.diver.model.RegularSeasonTeamStats;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

    private final Sheets sheetsService;

    @Autowired
    public GoogleSheetsServiceImpl(Sheets sheetsService) {
        this.sheetsService = sheetsService;
    }

    @Override
    public List<List<Object>> readData(String spreadsheetId, String range) throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        return response.getValues();
    }

    @Override
    public void updateData(String spreadsheetId, String range, List<List<Object>> values) throws IOException {
        ValueRange body = new ValueRange()
                .setValues(values);
        sheetsService.spreadsheets().values()
                .update(spreadsheetId, range, body)
                .setValueInputOption("RAW")
                .execute();
    }

    public static String buildRangeString(String sheetName, String startCell, String endCell) {
        String range = sheetName + "!" + startCell + ":" + endCell;
        System.out.println(range);
        return range;
    }

    @Override
    public Map<String, RegularSeasonTeamStats> getRegularSeasonTeamStatsMap() throws IOException {

        String spreadsheetId = "10hTrKAubzc-uZ_RtKGVvU3cg9hhN2cDYll5p1ltfwmk";
        String range = GoogleSheetsServiceImpl.buildRangeString(
                "All-Time Regular Season Team Stats",
                "A2",
                "AG17"
        );

        System.out.println("fetching team stats from Google Sheets...");

        List<List<Object>> values = readData(spreadsheetId, range);
        Map<String, RegularSeasonTeamStats> teamStatsMap = new HashMap<>();
        for (List<Object> row : values) {
            populateTeamStatsFromRow(teamStatsMap, row);
        }

        return teamStatsMap;
    }

    private static void populateTeamStatsFromRow(Map<String, RegularSeasonTeamStats> teamStatsMap, List<Object> row) {
        RegularSeasonTeamStats teamStats = new RegularSeasonTeamStats();

        // Assuming the order of fields in the Google Sheet matches the order in TeamStats
        // Also assuming that all values are present and correctly formatted
        try {
            String teamName = row.get(1).toString(); // Team name

            teamStats.setRank(Integer.parseInt(row.get(0).toString())); // Rank
            teamStats.setTeam(teamName);
            teamStats.setAllTimeRegularSeasonWins(Integer.parseInt(row.get(2).toString()));
            teamStats.setAllTimeRegularSeasonLosses(Integer.parseInt(row.get(3).toString()));
            teamStats.setAllTimeWinPercentage(Double.parseDouble(row.get(4).toString()));
            teamStats.setGb(Double.parseDouble(row.get(5).toString())); // Games Behind
            teamStats.setAllTimeTeamRotoScore(Double.parseDouble(row.get(6).toString()));
            teamStats.setAllTimeTotalPointsScored(Double.parseDouble(row.get(7).toString()));
            teamStats.setAllTimePointsPerGame(Double.parseDouble(row.get(8).toString()));
            teamStats.setAllTimeTotalPointsAgainst(Double.parseDouble(row.get(9).toString()));
            teamStats.setAllTimeTotalPointsAgainstPerGame(Double.parseDouble(row.get(10).toString()));
            teamStats.setAverageMarginPerGame(Double.parseDouble(row.get(11).toString()));
            teamStats.setAllTimeLongestRegularSeasonWinningStreak(Integer.parseInt(row.get(12).toString()));
            teamStats.setAllTimeLongestRegularSeasonLosingStreak(Integer.parseInt(row.get(13).toString()));

            if (!row.get(14).toString().isEmpty()) {
                teamStats.setCurrentWStreak(Integer.parseInt(row.get(14).toString()));
            }

            if (!row.get(15).toString().isEmpty()) {
                teamStats.setCurrentLStreak(Integer.parseInt(row.get(15).toString()));
            }

            teamStats.setBestScoringGame(Double.parseDouble(row.get(16).toString()));
            teamStats.setBestScoringGameDate(row.get(17).toString());
            teamStats.setWorstScoringGame(Double.parseDouble(row.get(18).toString()));
            teamStats.setWorstScoringGameDate(row.get(19).toString());
            teamStats.setBestSeasonRecord(row.get(20).toString());
            teamStats.setBestSeasonYear(Integer.parseInt(row.get(21).toString()));
            teamStats.setWorstSeasonRecord(row.get(22).toString());
            teamStats.setWorstSeasonYear(Integer.parseInt(row.get(23).toString()));
            teamStats.setBestSeasonPoints(Double.parseDouble(row.get(24).toString()));
            teamStats.setBsPPG(Double.parseDouble(row.get(25).toString()));
            teamStats.setBsYear(Integer.parseInt(row.get(26).toString()));
            teamStats.setWorstSeasonPoints(Double.parseDouble(row.get(27).toString()));
            teamStats.setWsPPG(Double.parseDouble(row.get(28).toString()));
            teamStats.setWsYear(Integer.parseInt(row.get(29).toString()));
            teamStats.setNumberOfWinningSeasons(Integer.parseInt(row.get(30).toString()));
            teamStats.setNumberOfLosingSeasons(Integer.parseInt(row.get(31).toString()));
            teamStats.setNumberOfSeasonsInWAFFL(Integer.parseInt(row.get(32).toString()));

            teamStatsMap.put(teamName, teamStats);
        } catch (NumberFormatException | NullPointerException e) {
            // Log warning or error and continue, or handle missing/invalid data as needed
            System.out.println("Error processing row: " + e.getMessage());
        }
    }
}