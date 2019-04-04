package com.kyan.qsbacktester.datasource;


import com.kyan.qsbacktester.model.Bar;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * CSV data source
 */
@Slf4j
public class CSVDataSource implements DataSource {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Map<String, Map<LocalDateTime, Bar>> histData;

    public CSVDataSource() {
        this.histData = new HashMap<>();
    }

    // load stock historical data from csv
    public void loadBarsFromCSV(String dirPath, String... symbols) {
        for (String symbol : symbols) {
            try {
                log.info("Loads hist data of symbol: {}", symbol);
                String filePath = dirPath + File.separator + symbol + ".csv";
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
                String[] nextRecord;
                Map<LocalDateTime, Bar> bars = new LinkedHashMap<>();
                while ((nextRecord = csvReader.readNext()) != null) {
                    //Date,Open,High,Low,Close,Adj Close,Volume,Period
                    Bar bar = new Bar(symbol);
                    LocalDate ld = LocalDate.parse(nextRecord[0], dateTimeFormatter);
                    bar.setDateTime(LocalDateTime.of(ld, LocalTime.of(0, 0)));
                    bar.setOpen(Double.valueOf(nextRecord[1]));
                    bar.setHigh(Double.valueOf(nextRecord[2]));
                    bar.setLow(Double.valueOf(nextRecord[3]));
                    bar.setClose(Double.valueOf(nextRecord[4]));
                    bar.setAdjClose(Double.valueOf(nextRecord[5]));
                    bar.setVolume(Long.valueOf(nextRecord[6]));
                    bar.setPeriod("D"); //Daily data
                    bars.put(bar.getDateTime(), bar);
                }
                histData.put(symbol, bars);
            } catch (IOException e) {
                log.error("Error loads hist data of symbol: {}", symbol);
                e.printStackTrace();
            }
        }
    }

    public List<String> getSymbols() {
        return new ArrayList<>(histData.keySet());
    }

    public Map<String, Map<LocalDateTime, Bar>> getHistData() {
        return histData;
    }

    public Map<LocalDateTime, Bar> getBars(String symbol) {
        return histData.get(symbol);
    }


}
