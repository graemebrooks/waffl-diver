package org.waffl.diver.googlesheets;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
}