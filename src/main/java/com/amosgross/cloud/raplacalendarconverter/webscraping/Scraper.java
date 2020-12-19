package com.amosgross.cloud.raplacalendarconverter.webscraping;

import com.amosgross.cloud.raplacalendarconverter.models.Lecture;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Scraper {
    Document document;
    LocalDate firstDateOfWeek;

    @Deprecated
    public Scraper(String url, LocalDate firstDateOfWeek) {
        this(url);
        System.out.println("WARNING:\tyour use of the date Parameter is Depreciated");
    }

    public Scraper(String url) {
        try {
            document = Jsoup.connect(url).get();
            this.firstDateOfWeek = getDate(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Lecture> getLectureDaysFromPage(){
        ArrayList<Element> lecturesAsHtmlElements = getAllLecturesAsHtmlElements();
        ArrayList<Lecture> lectures = new ArrayList<>();

        for (Element element : lecturesAsHtmlElements){
            boolean isKlausur = false;
            LocalDate dateOfLecture;
            LocalTime[] times = getTimesForLectureElement(element);
            if (element.attributes().get("style").equals("background-color:#F79F81")){
                dateOfLecture = firstDateOfWeek.plusDays(Long.parseLong(element.select("a[href]").attr("href").substring(1)));
                isKlausur = true;
            }
            else {
                dateOfLecture = firstDateOfWeek.plusDays(numberOfDaysFromMonday(getDayOfWeekFromLectureElement(element)));
            }
            Lecture lecture = new Lecture(
                    getTitleFromElement(element),
                    getLecturerFromElement(element),
                    times[0],
                    times[1],
                    dateOfLecture,
                    isKlausur
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

    private LocalDate getDate(Element element){
        int day = Integer.parseInt(element.select("select[name=day]").select("option[selected]").text());
        String monthName = element.select("select[name=month]").select("option[selected]").text();
        int year = Integer.parseInt(element.select("select[name=year]").select("option[selected]").text());
        int month;

        switch (monthName){
            case "Januar":
                month = 1;
                break;
            case "Februar":
                month = 2;
                break;
            case "März":
                month = 3;
                break;
            case "April":
                month = 4;
                break;
            case "Mai":
                month = 5;
                break;
            case "Juni":
                month = 6;
                break;
            case "Juli":
                month = 7;
                break;
            case "August":
                month = 8;
                break;
            case "September":
                month = 9;
                break;
            case "Oktober":
                month = 10;
                break;
            case "November":
                month = 11;
                break;
            case "Dezember":
                month = 12;
                break;
            default:
                throw new IllegalStateException("Month name not known: " + monthName);
        }

        LocalDate foundDate = LocalDate.of(year, month, day);

        // always returns the monday of that week, as it is needed for correct date assessment
        while (!(foundDate.getDayOfWeek() == DayOfWeek.MONDAY)){
            foundDate = foundDate.minusDays(1);
        }

        return foundDate;
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
