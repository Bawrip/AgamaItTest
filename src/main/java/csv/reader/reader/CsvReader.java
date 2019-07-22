package csv.reader.reader;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import csv.reader.pojo.PersonalAccountBean;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvReader<T> {
    public List<T> readCsvToBean(String csvFilePath, Class<T> clazz) throws IOException, URISyntaxException {
        Path path = Paths.get(csvFilePath);

        ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy();
        ms.setType(clazz);

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .withIgnoreQuotations(true)
                .build();
        Reader reader = Files.newBufferedReader(path);

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(1)
                .withCSVParser(parser)
                .build();

        CsvToBean cb = new CsvToBeanBuilder(csvReader)
                .withType(clazz)
                .withMappingStrategy(ms)
                .build();

        List<T> csvList = cb.parse();
        reader.close();

        return csvList;
    }
}
