package org.waffl.backend.googlesheets;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Configuration
public class GoogleSheetsConfig {

    @Bean
    public Sheets googleSheetsService() throws GeneralSecurityException, IOException {
        // Path to your service account key file
        String serviceAccountKeyFilePath = "path/to/your/service-account-key.json";

        // Load the service account key
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(new FileInputStream(serviceAccountKeyFilePath))
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));

        // Build the Sheets service
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), new HttpCredentialsAdapter(credentials))
                .setApplicationName("WAFFL-DIVER")
                .build();
    }
}