package com.amosgross.cloud.raplacalendarconverter.webscraping;

import java.time.LocalDate;

public class UrlBuilder {
    private static final String baseUrl = "https://rapla.dhbw-stuttgart.de/rapla";
    private static final String key20D = "txB1FOi5xd1wUJBWuX8lJrog1ZNPBrE7IVuW7j2dZDqY8f6xFPQs3yRuH0p5-aHv";

    public static String getUrlFromKey(String key, LocalDate dateToSearchFor){
        String base = baseUrl + "?key="+ key;

        base = appendParameter(base, "day", String.valueOf(dateToSearchFor.getDayOfMonth()));
        base = appendParameter(base, "month", String.valueOf(dateToSearchFor.getMonthValue()));
        base = appendParameter(base, "year", String.valueOf(dateToSearchFor.getYear()));

        return base;
    }

    public static String getUrl(String courseName, LocalDate dateToSearchFor){

        if (courseName.equals("20D")){
            return getUrlFromKey(key20D, dateToSearchFor);
        }
        else {
            throw new IllegalStateException("Hey, we don't support that course yet");
        }

    }

    private static String appendParameter(String previousURL, String key, String value){
        return previousURL + "&" + key + "=" + value;
    }
}
