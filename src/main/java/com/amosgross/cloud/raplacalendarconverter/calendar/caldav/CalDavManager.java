package com.amosgross.cloud.raplacalendarconverter.calendar.caldav;

import com.amosgross.cloud.raplacalendarconverter.models.Lecture;
import com.amosgross.cloud.raplacalendarconverter.models.LectureDifferences;
import com.amosgross.cloud.raplacalendarconverter.webscraping.Scraper;
import com.amosgross.cloud.raplacalendarconverter.webscraping.UrlBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class CalDavManager {
    private final CalDavClient client;

    public CalDavManager(CalDavClient client) {
        this.client = client;
    }

    public LectureDifferences getNeededLectureUpdates(){
        LectureDifferences differences = new LectureDifferences();
        ArrayList<Lecture> lecturesInCalendar = client.getCalendarItems();
        ArrayList<Lecture> lecturesInRapla = new ArrayList<>();

        // gets all events for next 12 weeks
        LocalDate dateToScrape = LocalDate.now();
        for (int i = 0; i < 12; i++) {
            Scraper scraper = new Scraper(UrlBuilder.getUrl("20D", dateToScrape.plusDays(i * 7)));
            lecturesInRapla.addAll(scraper.getLectureDaysFromPage());
        }

        // generate Hashmaps of hashCodes, with according lectures
        HashMap<Integer, Lecture> lectureInCalendarHashCodes = new HashMap<>();
        for (Lecture lecture : lecturesInCalendar) {
            lectureInCalendarHashCodes.put(lecture.hashCode(), lecture);
        }
        HashMap<Integer, Lecture> lectureInRaplaHashCodes = new HashMap<>();
        for (Lecture lecture : lecturesInRapla){
            lectureInRaplaHashCodes.put(lecture.hashCode(), lecture);
        }

        // cross reference hashCodes
        for (Lecture lecture : lecturesInRapla){
            if (!lectureInCalendarHashCodes.containsKey(lecture.hashCode())){
                differences.addCreateLecture(lecture);
            }
        }
        for (Lecture lecture : lecturesInCalendar){
            if (!lectureInRaplaHashCodes.containsKey(lecture.hashCode()) && lecture.getDate().isAfter(LocalDate.now())){
                differences.addDeleteLecture(lecture);
            }
        }

        return differences;
    }

    public void updateCalendar(){
        LectureDifferences differences = getNeededLectureUpdates();

        for (Lecture lecture : differences.getLecturesToCreate()){
            client.createCalendarItem(lecture);
        }

        for (Lecture lecture : differences.getLecturesToDelete()){
            client.removeCalendarItem(lecture);
        }
    }
}
