package com.amosgross.cloud.raplacalendarconverter.calendar.caldav;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

public class CalDavClient {

    private final CalDavCredentials davCredentials;

    public CalDavClient(CalDavCredentials davCredentials) {
        this.davCredentials = davCredentials;
    }

    private CloseableHttpClient buildHttpClient(){
        return HttpClients.custom().build();
    }

    public void getCalendarItems(){
        CloseableHttpClient httpClient = buildHttpClient();
        HttpUriRequest request = CalDavRequestBuilder.buildReportRequest(davCredentials);

        System.out.println(Arrays.toString(request.getAllHeaders()));

        try {
            CloseableHttpResponse response = httpClient.execute(request);
            System.out.println(EntityUtils.toString(response.getEntity()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
