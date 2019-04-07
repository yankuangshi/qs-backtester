package com.kyan.qsbacktester.event;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * 行情事件（Market event）由 DataHandler 产生，
 * 并提供给策略（Strategy）计算生成策略信号（Signal event）
 *
 * 一个行情事件包含：
 *  currentTime 当前时间
 *
 * @author kyan
 */
@Slf4j
@Data
public class MarketEvent extends AbstractEvent {

    private LocalDateTime currentTime;

    public MarketEvent(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

}
