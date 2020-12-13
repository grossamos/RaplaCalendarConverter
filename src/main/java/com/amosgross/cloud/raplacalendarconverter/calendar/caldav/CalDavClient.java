package com.amosgross.cloud.raplacalendarconverter.calendar.caldav;

import com.amosgross.cloud.raplacalendarconverter.models.Lecture;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

public class CalDavClient {

    private final CalDavCredentials davCredentials;

    public CalDavClient(CalDavCredentials davCredentials) {
        this.davCredentials = davCredentials;
    }

    private CloseableHttpClient buildHttpClient(){
        return HttpClients.custom().build();
    }

    public ArrayList<Lecture> getCalendarItems(){
        CloseableHttpClient httpClient = buildHttpClient();
        HttpUriRequest request = CalDavRequestBuilder.buildReportRequest(davCredentials);
        ArrayList<Lecture> lectures = null;

        try {
            CloseableHttpResponse response = httpClient.execute(request);
//            System.out.println(EntityUtils.toString(response.getEntity()));
            lectures = CalDavParser.parse(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lectures;
    }

    public void createCalendarItem(Lecture lecture){
        CloseableHttpClient httpClient = buildHttpClient();
        HttpUriRequest request = CalDavRequestBuilder.buildCreateRequest(davCredentials, lecture);
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            // System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeCalendarItem(Lecture lecture){
        CloseableHttpClient httpClient = buildHttpClient();
        HttpUriRequest request = CalDavRequestBuilder.buildDeleteRequest(davCredentials, lecture);
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            // System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
