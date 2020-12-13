package com.amosgross.cloud.raplacalendarconverter.calendar.caldav;

import com.amosgross.cloud.raplacalendarconverter.calendar.MyCalDavCredentials;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalDavClientTest {

    CalDavClient calDavClient = new CalDavClient(new MyCalDavCredentials());

    @Test
    void connect() {
        calDavClient.getCalendarItems();
        calDavClient.connect();
    }
}