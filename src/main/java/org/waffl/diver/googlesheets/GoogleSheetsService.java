package org.waffl.diver.googlesheets;

import java.io.IOException;
import java.util.List;

public interface GoogleSheetsService {

    List<List<Object>> readData(String spreadsheetId, String range) throws IOException;

    void updateData(String spreadsheetId, String range, List<List<Object>> values) throws IOException;
}
