package com.amosgross.cloud.raplacalendarconverter.examples;

import com.amosgross.cloud.raplacalendarconverter.calendar.caldav.CalDavClient;
import com.amosgross.cloud.raplacalendarconverter.calendar.caldav.CalDavCredentials;
import com.amosgross.cloud.raplacalendarconverter.calendar.caldav.CalDavManager;

public class CalDavExample {
    private class MyCreds implements CalDavCredentials{

        @Override
        public String getUrl() {
            return "some_url";
        }

        @Override
        public String getServerUserName() {
            return "some_username";
        }

        @Override
        public String getServerPassword() {
            return "some_password";
        }
    }

    CalDavManager calDavManager = new CalDavManager(new CalDavClient(new MyCreds()));
}
