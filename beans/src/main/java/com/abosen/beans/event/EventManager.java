package com.abosen.beans.event;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public abstract class EventManager {
    private static List<BaseEventListener> listeners = new ArrayList<>();
    private static final Map<Class<? extends EventObject>, Class<? extends BaseEventListener>> listenerMaps = new ConcurrentHashMap<>(4);

    public static void addSupportType(Class<? extends EventObject> event, Class<? extends BaseEventListener> listener) {
        listenerMaps.put(event, listener);
    }

    public static void addListener(BaseEventListener listener) {
        if (isSupportedListenerType(listener)) {
            listeners.add(listener);
        }
    }

    public static void removeListener(BaseEventListener listener) {
        if (listeners == null) {
            return;
        }
        listeners.remove(listener);
    }

    public static void clear() {
        listeners.clear();
    }

    public static void publishEvent(EventObject eventObject) {
        Class<? extends BaseEventListener> supportListenerClass = null;
        for (Map.Entry<Class<? extends EventObject>, Class<? extends BaseEventListener>> classEntry : listenerMaps.entrySet()) {
            if (classEntry.getKey().isAssignableFrom(eventObject.getClass())) {
                // 支持的事件类型
                supportListenerClass = classEntry.getValue();
                break;
            }
        }
        if (supportListenerClass == null) return;
        for (BaseEventListener listener : listeners) {
            if (supportListenerClass.isAssignableFrom(listener.getClass())) {
                BaseEventListener castListener = supportListenerClass.cast(listener);
                castListener.action(eventObject);
            }
        }
    }

    private static boolean isSupportedListenerType(BaseEventListener listener) {
        Collection<Class<? extends BaseEventListener>> supportListeners = listenerMaps.values();
        for (Class<? extends BaseEventListener> supportListener : supportListeners) {
            if (supportListener.isAssignableFrom(listener.getClass()))
                return true;
        }
        return false;
    }

    public static int getListenerCount() {
        return listeners.size();
    }
}
