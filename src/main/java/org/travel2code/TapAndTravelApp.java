package org.travel2code;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.travel2code.requestModels.TapRequestInfo;
import org.travel2code.responseModels.UserTripInfo;
import org.travel2code.service.TapRequestsProcessor;
import org.travel2code.util.CsvUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class TapAndTravelApp {

    public static void main(String[] args) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException, URISyntaxException {

        ClassLoader loader = TapAndTravelApp.class.getClassLoader();
        URL res = loader.getResource("csv/Taps.csv");
        File inputfile = Paths.get(res.toURI()).toFile();
        String inputCsvFile = inputfile.getAbsolutePath();
        CsvUtils<TapRequestInfo> csvUtils = new CsvUtils<>();
        List<TapRequestInfo> tapRequestInfos = csvUtils.convertFromCSVToPOJO(inputCsvFile, TapRequestInfo.class);
        TapRequestsProcessor tapRequestsProcessor = new TapRequestsProcessor();
        List<UserTripInfo> userTripInfos = tapRequestsProcessor.processTapRequests(tapRequestInfos);

        File outputFile = new File("src/main/resources/csv/Trips.csv");
        String outputCsvFile = outputFile.getAbsolutePath();
        CsvUtils<UserTripInfo> createCsv = new CsvUtils<>();
        createCsv.convertFromPOJOToCsv(outputCsvFile, UserTripInfo.class, userTripInfos);

    }
}