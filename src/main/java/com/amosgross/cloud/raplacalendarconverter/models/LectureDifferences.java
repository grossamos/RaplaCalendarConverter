package com.amosgross.cloud.raplacalendarconverter.models;

import java.util.ArrayList;

public class LectureDifferences {
    private final ArrayList<Lecture> lecturesToDelete = new ArrayList<>();
    private final ArrayList<Lecture> lecturesToCreate = new ArrayList<>();

    public void addDeleteLecture(Lecture lecture){
        lecturesToDelete.add(lecture);
    }

    public void addCreateLecture(Lecture lecture){
        lecturesToCreate.add(lecture);
    }

    public ArrayList<Lecture> getLecturesToDelete() {
        return lecturesToDelete;
    }

    public ArrayList<Lecture> getLecturesToCreate() {
        return lecturesToCreate;
    }
}
