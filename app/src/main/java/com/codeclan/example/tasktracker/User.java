package com.codeclan.example.tasktracker;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by richardedwards on 26/05/2017.
 **/

public class User implements Serializable {

    private String name;
    private ArrayList<Task> tasks;

    public User(String name) {
        this.name = name;
        tasks = new ArrayList<Task>();
        tasks.add(new Task("Make bed",1, "Flump pillows and duvet, tuck in sheet."));
        tasks.add(new Task("Tidy toys away",1, "Make sure no toys are lying on the floor."));
        tasks.add(new Task("Get dressed",1, "Get ready for school!  Don't forget your socks!"));
        tasks.add(new Task("Lay table",1, "Forks for everyone and knives for the grown-ups."));
        tasks.add(new Task("Clear table",1, "Scraps in the bin and dishes in the dishwasher."));
        tasks.add(new Task("Eat nicely",1, "Eat most of what you have on your plate."));
        tasks.add(new Task("Be helpful",1, "Try to help other people."));
        tasks.add(new Task("Be polite",2, "Talk nicely to your sister and the grown-ups."));
        tasks.add(new Task("Get washed",1, "Scrub up nice and clean!"));
        tasks.add(new Task("No fighting",1, "No fightin', no cussin', no mussin'."));
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> getDuplicateTasks() {
        return new ArrayList<Task>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
