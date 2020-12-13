package com.amosgross.cloud.raplacalendarconverter.webscraping;

import com.amosgross.cloud.raplacalendarconverter.models.Lecture;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Scraper {
    Document document;
    LocalDate firstDateOfWeek;

    public Scraper(String url, LocalDate firstDateOfWeek) {
        this.firstDateOfWeek = firstDateOfWeek;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Lecture> getLectureDaysFromPage(){
        ArrayList<Element> lecturesAsHtmlElements = getAllLecturesAsHtmlElements();
        ArrayList<Lecture> lectures = new ArrayList<>();

        for (Element element : lecturesAsHtmlElements){
            LocalTime[] times = getTimesForLectureElement(element);
            Lecture lecture = new Lecture(
                    getTitleFromElement(element),
                    getLecturerFromElement(element),
                    times[0],
                    times[1],
                    firstDateOfWeek.plusDays(numberOfDaysFromMonday(getDayOfWeekFromLectureElement(element)))
            );
            lectures.add(lecture);
        }

        return lectures;
    }

    private int numberOfDaysFromMonday(String day){
        switch (day){
            case "Mo":
                return 0;
            case "Di":
                return 1;
            case "Mi":
                return 2;
            case "Do":
                return 3;
            case "Fr":
                return 4;
            case "Sa":
                return 5;
            case "So":
                return 6;
            default:
                throw new IllegalStateException("This date is unknown: " + day);
        }
    }

    private String getTitleFromElement(Element element){
        return element.select("a[href^=#]").text().split(" erstellt am")[0].substring(13);

    }


    private String getLecturerFromElement(Element element){
        String lecturer = element.select("span.person").text();
        if (lecturer.equals(""))
            return lecturer;
        lecturer = lecturer.substring(0, lecturer.length() - 1);
        return lecturer;
    }

    private String getDayOfWeekFromLectureElement(Element element){
        return element.select("div").get(1).text().substring(0, 2);
    }

    private LocalTime[] getTimesForLectureElement(Element element){
        String timeText = element.select("a[href^=#]").text();
        String startTimeText = timeText.substring(0, 5);
        String endTimeText = timeText.substring(7, 12);
        return new LocalTime[]{LocalTime.of(Integer.parseInt(startTimeText.substring(0, 2)),
                                            Integer.parseInt(startTimeText.substring(3, 5))),
                               LocalTime.of(Integer.parseInt(endTimeText.substring(0, 2)),
                                            Integer.parseInt(endTimeText.substring(3, 5)))};
    }

    private ArrayList<Element> getAllLecturesAsHtmlElements(){
        return document.select("td[class=week_block]");
    }

}
