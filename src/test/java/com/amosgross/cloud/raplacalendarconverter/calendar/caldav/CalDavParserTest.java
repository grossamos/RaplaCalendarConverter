package com.amosgross.cloud.raplacalendarconverter.calendar.caldav;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalDavParserTest {
    String testRequest = "<?xml version=\"1.0\"?>\n" +
            "<d:multistatus xmlns:d=\"DAV:\" xmlns:s=\"http://sabredav.org/ns\" xmlns:cal=\"urn:ietf:params:xml:ns:caldav\" xmlns:cs=\"http://calendarserver.org/ns/\" xmlns:oc=\"http://owncloud.org/ns\" xmlns:nc=\"http://nextcloud.org/ns\"><d:response><d:href>/remote.php/dav/calendars/Amos/test/44749543-6C26-4D54-81AD-04DC2293FC8D.ics</d:href><d:propstat><d:prop><d:getetag>&quot;252bb730d19a4fe44b25cf46c6efccc2&quot;</d:getetag><cal:calendar-data>BEGIN:VCALENDAR\n" +
            "PRODID:-//IDN nextcloud.com//Calendar app 2.1.2//EN\n" +
            "CALSCALE:GREGORIAN\n" +
            "VERSION:2.0\n" +
            "BEGIN:VEVENT\n" +
            "CREATED:20201212T233206Z\n" +
            "DTSTAMP:20201212T233225Z\n" +
            "LAST-MODIFIED:20201212T233225Z\n" +
            "SEQUENCE:2\n" +
            "UID:79917c70-926d-477e-8b2c-4eddb92b6ed7\n" +
            "DTSTART;TZID=Europe/Berlin:20201217T100000\n" +
            "DTEND;TZID=Europe/Berlin:20201217T110000\n" +
            "SUMMARY:Test 3\n" +
            "DESCRIPTION:some link\n" +
            "LOCATION:online\n" +
            "END:VEVENT\n" +
            "BEGIN:VTIMEZONE\n" +
            "TZID:Europe/Berlin\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19700329T020000\n" +
            "RRULE:FREQ=YEARLY;BYMONTH=3;BYDAY=-1SU\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0100\n" +
            "TZNAME:CET\n" +
            "DTSTART:19701025T030000\n" +
            "RRULE:FREQ=YEARLY;BYMONTH=10;BYDAY=-1SU\n" +
            "END:STANDARD\n" +
            "END:VTIMEZONE\n" +
            "END:VCALENDAR</cal:calendar-data></d:prop><d:status>HTTP/1.1 200 OK</d:status></d:propstat></d:response><d:response><d:href>/remote.php/dav/calendars/Amos/test/844FF998-3499-4712-92AE-B6D4BEEE7DB0.ics</d:href><d:propstat><d:prop><d:getetag>&quot;5013f7230eb04e3ec1256d3b52267efe&quot;</d:getetag><cal:calendar-data>BEGIN:VCALENDAR\n" +
            "PRODID:-//IDN nextcloud.com//Calendar app 2.1.2//EN\n" +
            "CALSCALE:GREGORIAN\n" +
            "VERSION:2.0\n" +
            "BEGIN:VEVENT\n" +
            "CREATED:20201212T233238Z\n" +
            "DTSTAMP:20201212T233246Z\n" +
            "LAST-MODIFIED:20201212T233246Z\n" +
            "SEQUENCE:2\n" +
            "UID:38eb2221-9aa3-4f91-ae6c-9094ccf9cbf8\n" +
            "DTSTART;TZID=Europe/Berlin:20201222T100000\n" +
            "DTEND;TZID=Europe/Berlin:20201222T110000\n" +
            "SUMMARY:test6\n" +
            "END:VEVENT\n" +
            "BEGIN:VTIMEZONE\n" +
            "TZID:Europe/Berlin\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19700329T020000\n" +
            "RRULE:FREQ=YEARLY;BYMONTH=3;BYDAY=-1SU\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0100\n" +
            "TZNAME:CET\n" +
            "DTSTART:19701025T030000\n" +
            "RRULE:FREQ=YEARLY;BYMONTH=10;BYDAY=-1SU\n" +
            "END:STANDARD\n" +
            "END:VTIMEZONE\n" +
            "END:VCALENDAR</cal:calendar-data></d:prop><d:status>HTTP/1.1 200 OK</d:status></d:propstat></d:response><d:response><d:href>/remote.php/dav/calendars/Amos/test/A76DCB2B-2778-44E7-A797-BC3A11707728.ics</d:href><d:propstat><d:prop><d:getetag>&quot;dd5afc3fe402b8a356e8b50ce563c281&quot;</d:getetag><cal:calendar-data>BEGIN:VCALENDAR\n" +
            "PRODID:-//IDN nextcloud.com//Calendar app 2.1.2//EN\n" +
            "CALSCALE:GREGORIAN\n" +
            "VERSION:2.0\n" +
            "BEGIN:VEVENT\n" +
            "CREATED:20201212T233227Z\n" +
            "DTSTAMP:20201212T233236Z\n" +
            "LAST-MODIFIED:20201212T233236Z\n" +
            "SEQUENCE:2\n" +
            "UID:ed9b8f68-a5a4-4be7-a665-cba7ea16e714\n" +
            "DTSTART;VALUE=DATE:20201223\n" +
            "DTEND;VALUE=DATE:20201224\n" +
            "SUMMARY:test 77\n" +
            "END:VEVENT\n" +
            "END:VCALENDAR</cal:calendar-data></d:prop><d:status>HTTP/1.1 200 OK</d:status></d:propstat></d:response><d:response><d:href>/remote.php/dav/calendars/Amos/test/B07F10B6-BFCA-4B50-A7A2-8B3412BB1B25.ics</d:href><d:propstat><d:prop><d:getetag>&quot;973a70ff4c6c987ddee2d91d64494d51&quot;</d:getetag><cal:calendar-data>BEGIN:VCALENDAR\n" +
            "PRODID:-//IDN nextcloud.com//Calendar app 2.1.2//EN\n" +
            "CALSCALE:GREGORIAN\n" +
            "VERSION:2.0\n" +
            "BEGIN:VEVENT\n" +
            "CREATED:20201212T233134Z\n" +
            "DTSTAMP:20201212T233204Z\n" +
            "LAST-MODIFIED:20201212T233204Z\n" +
            "SEQUENCE:2\n" +
            "UID:33576af2-e23f-46f4-8064-9f016bb3ede9\n" +
            "DTSTART;TZID=Europe/Berlin:20201203T100000\n" +
            "DTEND;TZID=Europe/Berlin:20201203T110000\n" +
            "SUMMARY:Test 1\n" +
            "DESCRIPTION:insert link here\n" +
            "LOCATION:Online\n" +
            "END:VEVENT\n" +
            "BEGIN:VTIMEZONE\n" +
            "TZID:Europe/Berlin\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19700329T020000\n" +
            "RRULE:FREQ=YEARLY;BYMONTH=3;BYDAY=-1SU\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0100\n" +
            "TZNAME:CET\n" +
            "DTSTART:19701025T030000\n" +
            "RRULE:FREQ=YEARLY;BYMONTH=10;BYDAY=-1SU\n" +
            "END:STANDARD\n" +
            "END:VTIMEZONE\n" +
            "END:VCALENDAR</cal:calendar-data></d:prop><d:status>HTTP/1.1 200 OK</d:status></d:propstat></d:response><d:response><d:href>/remote.php/dav/calendars/Amos/test/aebd3d5bceaf89bd3d8235d5365540a83ac4d79c.ics</d:href><d:propstat><d:prop><d:getetag>&quot;bf320b3ace7d58ae6fa6f5de19490d0d&quot;</d:getetag><cal:calendar-data>BEGIN:VCALENDAR\n" +
            "CALSCALE:GREGORIAN\n" +
            "PRODID:-//Ximian//NONSGML Evolution Calendar//EN\n" +
            "VERSION:2.0\n" +
            "BEGIN:VTIMEZONE\n" +
            "TZID:/freeassociation.sourceforge.net/Europe/Berlin\n" +
            "X-LIC-LOCATION:Europe/Berlin\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19160428T230000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19160430T220000Z;BYDAY=-1SU;BYMONTH=4\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZNAME:CET\n" +
            "DTSTART:19161006T010000\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0100\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19160930T230000Z;BYDAY=1SU;BYMONTH=10\n" +
            "END:STANDARD\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19170415T020000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19180415T010000Z;BYDAY=3MO;BYMONTH=4\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZNAME:CET\n" +
            "DTSTART:19170916T030000\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0100\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19180916T010000Z;BYDAY=3MO;BYMONTH=9\n" +
            "END:STANDARD\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19400401T020000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19400401T010000Z;BYDAY=1MO;BYMONTH=4\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZNAME:CET\n" +
            "DTSTART:19421104T030000\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0100\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19421102T010000Z;BYDAY=1MO;BYMONTH=11\n" +
            "END:STANDARD\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19430325T020000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19430329T010000Z;BYDAY=-1MO;BYMONTH=3\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZNAME:CET\n" +
            "DTSTART:19431007T030000\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0100\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19441002T010000Z;BYDAY=1MO;BYMONTH=10\n" +
            "END:STANDARD\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19440401T020000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19450402T000000Z;BYDAY=1MO;BYMONTH=4\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEMT\n" +
            "DTSTART:19450523T020000\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0300\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19450523T230000Z;BYDAY=-2TH;BYMONTH=5\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19450930T030000\n" +
            "TZOFFSETFROM:+0300\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19450924T020000Z;BYDAY=-1MO;BYMONTH=9\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZNAME:CET\n" +
            "DTSTART:19451117T030000\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0100\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19451118T020000Z;BYDAY=3SU;BYMONTH=11\n" +
            "END:STANDARD\n" +
            "BEGIN:STANDARD\n" +
            "TZNAME:CET\n" +
            "DTSTART:19460101T000000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0100\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19451231T220000Z;BYDAY=1TU;BYMONTH=1\n" +
            "END:STANDARD\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19460414T020000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19460414T010000Z;BYDAY=2SU;BYMONTH=4\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZNAME:CET\n" +
            "DTSTART:19461007T030000\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0100\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19461007T010000Z;BYDAY=1MO;BYMONTH=10\n" +
            "END:STANDARD\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19470407T030000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19470406T010000Z;BYDAY=1SU;BYMONTH=4\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEMT\n" +
            "DTSTART:19470512T030000\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0300\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19470511T000000Z;BYDAY=2SU;BYMONTH=5\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19470630T030000\n" +
            "TZOFFSETFROM:+0300\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19470629T020000Z;BYDAY=-1SU;BYMONTH=6\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZNAME:CET\n" +
            "DTSTART:19471006T030000\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0100\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19491002T020000Z;BYDAY=1SU;BYMONTH=10\n" +
            "END:STANDARD\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19480421T020000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19480418T010000Z;BYDAY=3SU;BYMONTH=4\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19490414T020000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19490410T010000Z;BYDAY=2SU;BYMONTH=4\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZNAME:CET\n" +
            "DTSTART:19800101T000000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0100\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19791231T220000Z;BYDAY=1TU;BYMONTH=1\n" +
            "END:STANDARD\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19800407T020000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19800406T010000Z;BYDAY=1SU;BYMONTH=4\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZNAME:CET\n" +
            "DTSTART:19800929T030000\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0100\n" +
            "RRULE:FREQ=YEARLY;UNTIL=19950924T010000Z;BYDAY=-1SU;BYMONTH=9\n" +
            "END:STANDARD\n" +
            "BEGIN:DAYLIGHT\n" +
            "TZNAME:CEST\n" +
            "DTSTART:19810331T020000\n" +
            "TZOFFSETFROM:+0100\n" +
            "TZOFFSETTO:+0200\n" +
            "RRULE:FREQ=YEARLY;BYDAY=-1SU;BYMONTH=3\n" +
            "END:DAYLIGHT\n" +
            "BEGIN:STANDARD\n" +
            "TZNAME:CET\n" +
            "DTSTART:19961027T030000\n" +
            "TZOFFSETFROM:+0200\n" +
            "TZOFFSETTO:+0100\n" +
            "RRULE:FREQ=YEARLY;BYDAY=-1SU;BYMONTH=10\n" +
            "END:STANDARD\n" +
            "END:VTIMEZONE\n" +
            "BEGIN:VEVENT\n" +
            "UID:aebd3d5bceaf89bd3d8235d5365540a83ac4d79c\n" +
            "DTSTAMP:20201212T225340Z\n" +
            "SUMMARY:Test Event\\, loll\n" +
            "DTSTART;TZID=/freeassociation.sourceforge.net/Europe/Berlin:\n" +
            " 20201211T230040\n" +
            "DTEND;TZID=/freeassociation.sourceforge.net/Europe/Berlin:\n" +
            " 20201212T000040\n" +
            "X-LIC-ERROR;X-LIC-ERRORTYPE=VALUE-PARSE-ERROR:No value for DESCRIPTION \n" +
            " property. Removing entire property:\n" +
            "X-LIC-ERROR;X-LIC-ERRORTYPE=VALUE-PARSE-ERROR:No value for LOCATION \n" +
            " property. Removing entire property:\n" +
            "CREATED:20201212T225354Z\n" +
            "LAST-MODIFIED:20201212T225354Z\n" +
            "END:VEVENT\n" +
            "END:VCALENDAR\n" +
            "</cal:calendar-data></d:prop><d:status>HTTP/1.1 200 OK</d:status></d:propstat></d:response></d:multistatus>\n" +
            "\n" +
            "\n" +
            "Process finished with exit code 0\n";

    @Test
    void gets_correct_number_of_events(){
        assertEquals(5, CalDavParser.parse(testRequest).size());
    }
}