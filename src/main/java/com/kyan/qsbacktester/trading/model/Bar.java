package com.kyan.qsbacktester.trading.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Bar {

    private String symbol;
    private LocalDateTime dateTime;
    private String period;
    private double open;
    private double high;
    private double low;
    private double close;
    private double adjClose;
    private long volume;

    public Bar(String symbol) {
        this.symbol = symbol;
    }
}
