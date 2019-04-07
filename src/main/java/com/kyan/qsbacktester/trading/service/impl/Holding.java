package com.kyan.qsbacktester.trading.service.impl;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class Holding {

    private LocalDateTime dateTime;
    private double cash;        //现金余额
    private double commission;  //交易佣金
    private double total;       //总市值
    private Map<String, Long> positions = new HashMap<>();      //各个股票仓位
    private Map<String, Double> marketValues = new HashMap<>(); //各个股票市值

}
