package com.kyan.qsbacktester.event;

import com.kyan.qsbacktester.framework.Event;

/**
 * 抽象事件类
 * 其他的事件类都必须继承该父类以获取事件类型
 *
 * @author kyan
 */
public abstract class AbstractEvent implements Event {

    @Override
    public Class<? extends Event> getType() {
        return getClass();
    }
}
