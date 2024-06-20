package org.travel2code.util;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class CustomColumnPositionStrategy<T> extends ColumnPositionMappingStrategy<T> {
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {
        super.generateHeader(bean);
        return super.getColumnMapping();
    }
}
