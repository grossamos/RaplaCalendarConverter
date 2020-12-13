package com.amosgross.cloud.raplacalendarconverter.calendar.caldav;

import com.amosgross.cloud.raplacalendarconverter.models.Lecture;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalDavParser {
    public static ArrayList<Lecture> parse(String responseToParse){
        Pattern pattern = Pattern.compile("BEGIN:VEVENT.*?END:VEVENT", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(responseToParse);

        ArrayList<Lecture> lectures = new ArrayList<>();

        while (matcher.find()){
            LocalTime startTime = LocalTime.of(0, 0);
            LocalTime endTime = LocalTime.of(0, 0);
            LocalDate date = LocalDate.of(0, 1, 1);
            String title = "";
            String descriptionLecturer = "";

            String calendarEvent = matcher.group().replace("\\", "");

            Matcher summaryMatcher = Pattern.compile("SUMMARY:[^\n]*").matcher(calendarEvent);
            if (summaryMatcher.find()) {
                title = summaryMatcher.group().substring(8);
            }

            Matcher descriptionMatcher = Pattern.compile("DESCRIPTION:[^\n]*").matcher(calendarEvent);
            if (descriptionMatcher.find()) {
                descriptionLecturer = descriptionMatcher.group().substring(12);
            }

            Matcher startTimeMatcher = Pattern.compile("(DTSTART[^\n]*)", Pattern.DOTALL).matcher(calendarEvent);
            if (startTimeMatcher.find()) {
                String startTimeSting = getDateTimeFromString(startTimeMatcher.group());
                if (startTimeSting.length() == 15) {
                    startTime = getTimeFromString(startTimeSting);
                    date = getDateFromString(startTimeSting);
                }
            }
            Matcher endTimeMatcher = Pattern.compile("(DTEND[^\n]*)", Pattern.DOTALL).matcher(calendarEvent);
            if (endTimeMatcher.find()) {
                String endTimeString = getDateTimeFromString(endTimeMatcher.group());
                if (endTimeString.length() == 15) {
                    endTime = getTimeFromString(endTimeString);
                }
            }

            lectures.add(new Lecture(title, descriptionLecturer, startTime, endTime, date));
        }

        return lectures;
    }

    private static LocalDate getDateFromString(String stringIncludingDate){
        if (stringIncludingDate.length() != 15){
            throw new IllegalStateException("Hey, your sting doesn't contain a time! string:" + stringIncludingDate);
        }
        return LocalDate.of(Integer.parseInt(stringIncludingDate.substring(0, 4)), Integer.parseInt(stringIncludingDate.substring(4, 6)), Integer.parseInt(stringIncludingDate.substring(6, 8)));
    }

    private static LocalTime getTimeFromString(String stringIncludingTime){
        if (stringIncludingTime.length() != 15){
            throw new IllegalStateException("Hey, your sting doesn't contain a time! string:" + stringIncludingTime);
        }
        return LocalTime.of(Integer.parseInt(stringIncludingTime.substring(9, 11)), Integer.parseInt(stringIncludingTime.substring(11, 13)));
    }

    private static String getDateTimeFromString(String stringIncludingDateTime){
        Matcher calDavDateTimeMatcher = Pattern.compile("\\d+[T]*\\d*").matcher(stringIncludingDateTime);
        if (calDavDateTimeMatcher.find())
            return calDavDateTimeMatcher.group();
        else
            return "";
    }
}
