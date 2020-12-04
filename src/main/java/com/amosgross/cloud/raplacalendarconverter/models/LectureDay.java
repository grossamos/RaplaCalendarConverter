package com.amosgross.cloud.raplacalendarconverter.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class LectureDay {
    private ArrayList<Lecture> lectures;
    private LocalDate date;

    public LectureDay(LocalDate date) {
        this.date = date;
        this.lectures = new ArrayList<>();
    }

    public void addLecture(Lecture lecture){
        lectures.add(lecture);
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public LocalDate getDate() {
        return date;
    }
}
