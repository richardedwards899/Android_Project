package com.codeclan.example.tasktracker;

import java.io.Serializable;

/**
 * Created by richardedwards on 26/05/2017.
 */

public class Task implements Serializable {

    private String taskName;
    private int pence;
    private String taskDescription;
    private boolean completed;
    private int countCompleted;

    public Task(String taskName, int pence, String taskDescription) {
        this.taskName = taskName;
        this.pence = pence;
        this.taskDescription = taskDescription;
        completed = false;
        countCompleted = 0;
    }

    public String getName() {
        return taskName;
    }

    public int getPence() {
        return pence;
    }

    public String getDescription() {
        return taskDescription;
    }

    public void markAsComplete() {
        completed = true;
        countCompleted++;
    }

    public boolean completed(){
        return completed;
    }

    public int timesCompleted(){
        return countCompleted;
    }
}
