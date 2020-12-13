package com.amosgross.cloud.raplacalendarconverter.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Lecture {
    private final String title;
    private final String lecturer;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final LocalDate date;

    public Lecture(String title, String lecturer, LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.title = title;
        this.lecturer = lecturer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getLecturer() {
        return lecturer;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalDate getDate() {
        return date;
    }
}
