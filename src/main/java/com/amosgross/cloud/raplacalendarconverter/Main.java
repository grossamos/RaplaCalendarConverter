package com.amosgross.cloud.raplacalendarconverter;

import com.amosgross.cloud.raplacalendarconverter.calendar.MyCalDavCredentials;
import com.amosgross.cloud.raplacalendarconverter.calendar.caldav.CalDavClient;
import com.amosgross.cloud.raplacalendarconverter.calendar.caldav.CalDavManager;

public class Main {
    public static void main(String[] args) {
        CalDavManager calDavManager = new CalDavManager(new CalDavClient(new MyCalDavCredentials()));
        calDavManager.updateCalendar();
    }
}
