package com.amosgross.cloud.raplacalendarconverter.calendar.caldav;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CalDavRequestBuilder {
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

        Base64.Encoder base64Encoder = Base64.getEncoder();

        return RequestBuilder.create("REPORT")
                .setUri("https://cloud.amosgross.com/remote.php/dav/calendars/Amos/test/")
//                .setUri("https://nextcloud.dhbw-stuttgart.de/remote.php/dav/calendars/inf20038")
                .setHeader("Authorization", "Basic " + base64Encoder.encodeToString((credentials.getServerUserName() + ":" + credentials.getServerPassword()).getBytes(StandardCharsets.UTF_8)))
                .setHeader("Depth", "1")
                .setHeader("Prefer", "return-minimal")
                .setHeader("Content", "application/xml; charset=utf-8")
                .setEntity(new ByteArrayEntity(requestBody.getBytes(StandardCharsets.UTF_8)))
                .build();
    }
}
