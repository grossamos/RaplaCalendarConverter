package com.amosgross.cloud.raplacalendarconverter.webscraping;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UrlBuilderTest {
    @Test
    void can_get_url_for_course_20D_on_christmas(){
        assertEquals("https://rapla.dhbw-stuttgart.de/rapla?key=txB1FOi5xd1wUJBWuX8lJrog1ZNPBrE7IVuW7j2dZDqY8f6xFPQs3yRuH0p5-aHv&day=24&month=12&year=2020",
                UrlBuilder.getUrl("20D", LocalDate.of(2020, 12, 24)));
    }
}