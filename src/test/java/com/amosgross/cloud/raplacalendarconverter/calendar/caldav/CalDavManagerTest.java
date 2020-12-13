package com.amosgross.cloud.raplacalendarconverter.calendar.caldav;

import com.amosgross.cloud.raplacalendarconverter.calendar.MyCalDavCredentials;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalDavManagerTest {

    CalDavManager calDavManager = new CalDavManager(new CalDavClient(new MyCalDavCredentials()));

    @Test
    void getNeededLectureUpdates() {
        calDavManager.updateCalendar();
    }
}