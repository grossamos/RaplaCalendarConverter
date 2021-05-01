package com.amosgross.cloud.raplacalendarconverter.webscraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ScraperTest {
    Scraper scraper = new Scraper("https://rapla.dhbw-stuttgart.de/rapla?key=txB1FOi5xd1wUJBWuX8lJrog1ZNPBrE7IVuW7j2dZDqY8f6xFPQs3yRuH0p5-aHv&day=1&month=5&year=2021&next=%3E%3E#5");

//    @Test
//    void can_get_something_from_a_url(){
//        assertNotNull(scraper.getFullHTML());
//    }
//
//    @Test
//    void can_get_time_from_element_in_first_week() {
//        assertArrayEquals(new LocalTime[] {LocalTime.of(9, 0), LocalTime.of(10, 30)}, scraper.getTimesForLectureElement(getAnElement()));
//    }
//
//    @Test
//    void can_get_day_from_element_in_first_week(){
//        assertEquals("Mo", scraper.getDayOfWeekFromLectureElement(getAnElement()));
//    }
//
//    @Test
//    void getTitleFromElement() {
//        assertEquals("Einführungsveranstaltung", scraper.getTitleFromElement(getAnElement()));
//    }
//
//    @Test
//    void testGetTitleFromElement() {
//        assertEquals("Stockmayer, Friedemann", scraper.getLecturerFromElement(getAnElement()));
//    }
//
//    private Element getAnElement(){
//        // kinda bad testing, but meh
//        Document jsoup = new Document("lol");
//        try {
//            jsoup = Jsoup.connect("https://rapla.dhbw-stuttgart.de/rapla?key=txB1FOi5xd1wUJBWuX8lJrog1ZNPBrE7IVuW7j2dZDqY8f6xFPQs3yRuH0p5-aHv&day=1&month=12&year=2020").get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return jsoup.select("td[class=week_block]").get(1);
//    }

    @Test
    void works(){
        System.out.println(scraper.getLectureDaysFromPage());
    }
}