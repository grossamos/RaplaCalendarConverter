package com.amosgross.cloud.raplacalendarconverter.examples;

import com.amosgross.cloud.raplacalendarconverter.models.Lecture;
import com.amosgross.cloud.raplacalendarconverter.models.LectureDay;
import com.amosgross.cloud.raplacalendarconverter.webscraping.Scraper;
import com.amosgross.cloud.raplacalendarconverter.webscraping.UrlBuilder;

import java.time.LocalDate;
import java.util.ArrayList;

public class WebScraperExample {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2020, 11, 30);
        Scraper scraper = new Scraper(UrlBuilder.getUrl("20D", localDate), localDate);
        ArrayList<LectureDay> lectureDays = scraper.getLectureDaysFromPage();

        for (LectureDay lectureDay : lectureDays){
            System.out.println("-----------------------------------------------------");
            System.out.println("Day: " + lectureDay.getDate().toString());
            for (Lecture lecture : lectureDay.getLectures()){
                System.out.println("Lecture: ");
                System.out.println("\t Time: " + lecture.getStartTime() + "-" + lecture.getEndTime());
                System.out.println("\t Title: " + lecture.getTitle());
                System.out.println("\t Lecturer: " + lecture.getLecturer());
            }
        }
        System.out.println("-----------------------------------------------------");
    }
}
