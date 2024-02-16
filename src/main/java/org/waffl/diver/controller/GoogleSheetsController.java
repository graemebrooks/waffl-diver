package org.waffl.diver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.waffl.diver.googlesheets.GoogleSheetsService;
import org.waffl.diver.googlesheets.GoogleSheetsServiceImpl;

import java.io.IOException;
import java.util.List;

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



    @GetMapping("/data")
    public List<List<Object>> getSheetDataTest() throws IOException {

        // Assume you're fetching data from a specific spreadsheet and range
        String spreadsheetId = "10hTrKAubzc-uZ_RtKGVvU3cg9hhN2cDYll5p1ltfwmk";
        String range = GoogleSheetsServiceImpl.buildRangeString(
                "All-Time Regular Season Team Stats",
                "B4",
                "B4"
        );

        List<List<Object>> cells = googleSheetsService.readData(spreadsheetId, range);
        System.out.println(cells.get(0).get(0).toString());
        return cells;
    }
}