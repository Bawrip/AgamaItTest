package csv.reader.converter;

import com.opencsv.bean.AbstractBeanField;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class YearMonthConverter extends AbstractBeanField {
    @Override
    protected Object convert(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.yyyy", Locale.ENGLISH);

        return YearMonth.parse(s, formatter);
    }
}
