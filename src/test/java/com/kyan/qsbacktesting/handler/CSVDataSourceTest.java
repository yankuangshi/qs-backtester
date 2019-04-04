package com.kyan.qsbacktesting.handler;

import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class CSVDataSourceTest {

    @Test
    public void testCSVDataSource() {
        String symbol = "000002.SZ";
        String filePath = Paths.get("src", "main", "resources").toString()
                + File.separator + "histdata" + File.separator + symbol + ".csv";
        CSVDataSource csvDataSource = new CSVDataSource();
        csvDataSource.loadBarsFromCSV(symbol,filePath);
        assertEquals(1220, csvDataSource.getBars(symbol).size());
    }
}
