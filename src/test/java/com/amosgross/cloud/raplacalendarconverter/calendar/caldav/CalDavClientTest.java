package com.amosgross.cloud.raplacalendarconverter.calendar.caldav;

import com.amosgross.cloud.raplacalendarconverter.calendar.MyCalDavCredentials;
import com.amosgross.cloud.raplacalendarconverter.models.Lecture;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CalDavClientTest {

    CalDavClient calDavClient = new CalDavClient(new MyCalDavCredentials());

    @Test
    void connect() {
        LocalTime time = LocalTime.of(4, 20);
        Lecture lecture = new Lecture("Hey, this is a test", "Created purely by ultraman in java", time, time.plusHours(2), LocalDate.now());
        calDavClient.getCalendarItems();
        calDavClient.createCalendarItem(lecture);
        calDavClient.removeCalendarItem(lecture);
    }
}