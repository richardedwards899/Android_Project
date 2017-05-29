package com.codeclan.example.tasktracker;

/**
 * Created by richardedwards on 26/05/2017.
 */

public class Task {

    private String taskName;
    private int points;
    private String taskDescription;

    public Task(String taskName, int points, String taskDescription) {
        this.taskName = taskName;
        this.points = points;
        this.taskDescription = taskDescription;
    }


    public String getName() {
        return taskName;
    }

    public int getPoints() {
        return points;
    }

    public String getDescription() {
        return taskDescription;
    }
}