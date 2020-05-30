package com.demo.mvpdemo.utils;

import org.greenrobot.eventbus.EventBus;

public class EventBusUtils {

    public static void post(Object event) {
        EventBus.getDefault().post(event);
    }

    public static void register(Object subscriber) {
        if (!isRegistered(subscriber)) EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        if (isRegistered(subscriber)) EventBus.getDefault().unregister(subscriber);
    }

    public static boolean isRegistered(Object subscriber) {
        return EventBus.getDefault().isRegistered(subscriber);
    }

    public static void removeEvent(){
        EventBus.getDefault().removeAllStickyEvents();

    }

    public static void cancel(Object event) {
        EventBus.getDefault().cancelEventDelivery(event);
    }

}
