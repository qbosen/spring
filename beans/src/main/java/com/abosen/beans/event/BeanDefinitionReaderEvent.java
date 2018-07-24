package com.abosen.beans.event;

import com.abosen.beans.factory.support.BeanDefinitionRegistry;

import java.util.EventObject;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */


public class BeanDefinitionReaderEvent extends EventObject {
    private static final long serialVersionUID = 6496098798146410884L;

    /**
     * The object on which the Event initially occurred.
     */
    protected transient BeanDefinitionRegistry source;
    protected String basePackages;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public BeanDefinitionReaderEvent(BeanDefinitionRegistry source, String basePackages) {
        super(source);
        this.source = source;
        this.basePackages = basePackages;
    }

    /**
     * The object on which the Event initially occurred.
     *
     * @return The object on which the Event initially occurred.
     */
    public BeanDefinitionRegistry getSource() {
        return source;
    }

    public String getBasePackages() {
        return basePackages;
    }

    /**
     * Returns a String representation of this EventObject.
     *
     * @return A a String representation of this EventObject.
     */
    public String toString() {
        return getClass().getName() + "[source=" + source + "]";
    }
}
