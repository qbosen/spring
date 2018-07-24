package com.abosen.beans.event;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public interface BaseEventListener extends EventListener {
    void action(EventObject eventObject);
}
