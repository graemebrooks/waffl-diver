package org.waffl.diver.googlesheets;

import org.waffl.diver.model.RegularSeasonTeamStats;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GoogleSheetsService {

    List<List<Object>> readData(String spreadsheetId, String range) throws IOException;

    void updateData(String spreadsheetId, String range, List<List<Object>> values) throws IOException;
    Map<String, RegularSeasonTeamStats> getRegularSeasonTeamStatsMap() throws IOException;
}
