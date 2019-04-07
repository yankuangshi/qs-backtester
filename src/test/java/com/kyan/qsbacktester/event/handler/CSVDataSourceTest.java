package com.kyan.qsbacktester.event.handler;

import com.kyan.qsbacktester.datasource.CSVDataSource;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class CSVDataSourceTest {

    @Test
    public void testCSVDataSource() {
        String symbol = "000002.SZ";
        String dirPath = Paths.get("src", "main", "resources").toString() + File.separator + "histdata";
        CSVDataSource csvDataSource = new CSVDataSource();
        csvDataSource.loadBarsFromCSV(dirPath, symbol);
        assertEquals(1220, csvDataSource.getBars(symbol).size());
    }
}
