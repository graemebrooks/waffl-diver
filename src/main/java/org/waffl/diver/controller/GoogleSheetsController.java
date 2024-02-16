package org.waffl.diver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.waffl.diver.googlesheets.GoogleSheetsService;
import org.waffl.diver.googlesheets.GoogleSheetsServiceImpl;
import org.waffl.diver.model.RegularSeasonTeamStats;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/google-sheets")
public class GoogleSheetsController {

    private final GoogleSheetsService googleSheetsService;

    @Autowired
    public GoogleSheetsController(GoogleSheetsService googleSheetsService) {
        this.googleSheetsService = googleSheetsService;
    }

    @GetMapping("/data")
    public List<List<Object>> getSheetDataGeneric(String spreadsheetId, String sheetName, String startCell, String endCell) throws IOException {

        // Assume you're fetching data from a specific spreadsheet and range
        String range = GoogleSheetsServiceImpl.buildRangeString(sheetName, startCell, endCell);

        List<List<Object>> cells = googleSheetsService.readData(spreadsheetId, range);
        System.out.println(cells.get(0).get(0).toString());
        return cells;
    }



    @GetMapping("/regular-season-team-stats")
    public Map<String, RegularSeasonTeamStats> getSheetDataTest() throws IOException {
        Map<String, RegularSeasonTeamStats> teamStatsMap = googleSheetsService.getRegularSeasonTeamStatsMap();
        System.out.println(teamStatsMap.toString());
        return teamStatsMap;
    }
}