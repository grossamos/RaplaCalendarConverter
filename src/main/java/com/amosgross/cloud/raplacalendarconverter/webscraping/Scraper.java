package com.amosgross.cloud.raplacalendarconverter.webscraping;

import com.amosgross.cloud.raplacalendarconverter.models.Lecture;
import com.amosgross.cloud.raplacalendarconverter.models.LectureDay;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

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

    public ArrayList<LectureDay> getLectureDaysFromPage(){
        ArrayList<Element> lecturesAsHtmlElements = getAllLecturesAsHtmlElements();
        HashMap<String, LectureDay> lectureDays = new HashMap<>();


        // populates Arraylist
        lectureDays.put("Mo", new LectureDay(firstDateOfWeek.plusDays(0)));
        lectureDays.put("Di", new LectureDay(firstDateOfWeek.plusDays(1)));
        lectureDays.put("Mi", new LectureDay(firstDateOfWeek.plusDays(2)));
        lectureDays.put("Do", new LectureDay(firstDateOfWeek.plusDays(3)));
        lectureDays.put("Fr", new LectureDay(firstDateOfWeek.plusDays(4)));
        lectureDays.put("Sa", new LectureDay(firstDateOfWeek.plusDays(5)));
        lectureDays.put("So", new LectureDay(firstDateOfWeek.plusDays(6)));

        for (Element element : lecturesAsHtmlElements){
            LocalTime[] times = getTimesForLectureElement(element);
            Lecture lecture = new Lecture(
                    getTitleFromElement(element),
                    getLecturerFromElement(element),
                    times[0],
                    times[1]
            );
            lectureDays.get(getDayOfWeekFromLectureElement(element)).addLecture(lecture);
        }

        ArrayList<LectureDay> resultArray = new ArrayList<>();

        resultArray.add(lectureDays.get("Mo"));
        resultArray.add(lectureDays.get("Di"));
        resultArray.add(lectureDays.get("Mi"));
        resultArray.add(lectureDays.get("Do"));
        resultArray.add(lectureDays.get("Fr"));
        resultArray.add(lectureDays.get("Sa"));
        resultArray.add(lectureDays.get("So"));

        return resultArray;
    }

    private String getTitleFromElement(Element element){
        return element.select("a[href^=#]").text().split(" erstellt am")[0].substring(13);

    }


    private String getLecturerFromElement(Element element){
        String lecturer = element.select("span.person").text();
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


    private String getFullHTML(){
        return document.html();
    }
}
