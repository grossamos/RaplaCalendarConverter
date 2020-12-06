package com.amosgross.cloud.raplacalendarconverter.caldav;

import org.junit.jupiter.api.Test;
import org.osaf.caldav4j.exceptions.CalDAV4JException;

import static org.junit.jupiter.api.Assertions.*;

class CalDavClientTest {

    CalDavClient client = new CalDavClient();

    @Test
    void doesnt_crash() throws CalDAV4JException {
        client.connect();
    }

}