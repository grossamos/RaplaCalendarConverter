package com.amosgross.cloud.raplacalendarconverter.calendar.caldav;

import com.amosgross.cloud.raplacalendarconverter.models.Lecture;
import org.apache.commons.httpclient.Credentials;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Base64;

public class CalDavRequestBuilder {
    private static final Base64.Encoder base64Encoder = Base64.getEncoder();
    public static HttpUriRequest buildReportRequest(CalDavCredentials credentials){
        String requestBody =
                "<c:calendar-query xmlns:d=\"DAV:\" xmlns:c=\"urn:ietf:params:xml:ns:caldav\">\n" +
                        "    <d:prop>\n" +
                        "        <d:getetag />\n" +
                        "        <c:calendar-data />\n" +
                        "    </d:prop>\n" +
                        "    <c:filter>\n" +
                        "        <c:comp-filter name=\"VCALENDAR\" />\n" +
                        "    </c:filter>\n" +
                        "</c:calendar-query>";


        return RequestBuilder.create("REPORT")
                .setUri(credentials.getUrl())
                .setHeader("Authorization", "Basic " + base64Encoder.encodeToString((credentials.getServerUserName() + ":" + credentials.getServerPassword()).getBytes(StandardCharsets.UTF_8)))
                .setHeader("Depth", "1")
                .setHeader("Prefer", "return-minimal")
                .setHeader("Content", "application/xml; charset=utf-8")
                .setEntity(new ByteArrayEntity(requestBody.getBytes(StandardCharsets.UTF_8)))
                .build();
    }
    public static HttpUriRequest buildCreateRequest(CalDavCredentials credentials, Lecture lecture){
        LocalDate date = lecture.getDate();
        LocalTime startTime = lecture.getStartTime();
        LocalTime endTime = lecture.getEndTime();
        String requestBody =
                "BEGIN:VCALENDAR\n" +
                "BEGIN:VEVENT\n" +
                "SUMMARY:" + lecture.getTitle() + "\n" +
                "DESCRIPTION:" + lecture.getLecturer() + "\n" +
                "DTSTART;TZID=Europe/Berlin:" + generateTimeString(startTime, date) +
                "DTEND;TZID=Europe/Berlin:" + generateTimeString(endTime, date)  +
                "LOCATION:online\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR";

        return RequestBuilder.create("PUT")
                .setUri(credentials.getUrl() + lecture.hashCode() + ".ics")
                .setHeader("Authorization", "Basic " + base64Encoder.encodeToString((credentials.getServerUserName() + ":" + credentials.getServerPassword()).getBytes(StandardCharsets.UTF_8)))
                .setHeader("Content", "text/calendar; charset=utf-8")
                .setEntity(new ByteArrayEntity(requestBody.getBytes(StandardCharsets.UTF_8)))
                .build();
    }

    private static String generateTimeString(LocalTime startTime, LocalDate date){
        return "" + date.getYear() +
                (date.getMonthValue() >= 10? date.getMonthValue(): "0" + date.getMonthValue()) +
                (date.getDayOfMonth() >= 10? date.getDayOfMonth(): "0" + date.getDayOfMonth()) +
                "T" +
                ((startTime.getHour() >= 10)? startTime.getHour(): "0" + startTime.getHour()) +
                (startTime.getMinute() >= 10? startTime.getMinute(): "0" + startTime.getMinute()) +
                (startTime.getSecond() >= 10? startTime.getSecond(): "0" + startTime.getSecond()) + "\n";
    }

    public static HttpUriRequest buildDeleteRequest(CalDavCredentials credentials, Lecture lecture){
        return RequestBuilder.create("DELETE")
                .setUri(credentials.getUrl() + lecture.hashCode() + ".ics")
                .setHeader("Authorization", "Basic " + base64Encoder.encodeToString((credentials.getServerUserName() + ":" + credentials.getServerPassword()).getBytes(StandardCharsets.UTF_8)))
                .setEntity(new ByteArrayEntity("".getBytes(StandardCharsets.UTF_8)))
                .build();
    }
}
