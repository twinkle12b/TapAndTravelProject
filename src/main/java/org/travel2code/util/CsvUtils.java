package org.travel2code.util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.travel2code.requestModels.TapRequestInfo;
import org.travel2code.responseModels.UserTripInfo;

import java.io.*;
import java.util.List;

public class CsvUtils<T> {

    public List<T> convertFromCSVToPOJO(String csvFile, Class<? extends T> type) throws IOException {
        try (Reader reader = new FileReader(csvFile)) {
            List<T> list = new CsvToBeanBuilder(reader)
                    .withType(type)
                    .build()
                    .parse();
            return list;
        }
    }


    public void convertFromPOJOToCsv(String outputFile, Class<? extends T> type, List<T> list) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        FileWriter writer = new
                FileWriter(outputFile);
        var mappingStrategy = new CustomColumnPositionStrategy<T>();
        mappingStrategy.setType(type);

        var builder = new StatefulBeanToCsvBuilder<T>(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withMappingStrategy(mappingStrategy)
                .build();
        builder.write(list);
        writer.close();
    }
}
