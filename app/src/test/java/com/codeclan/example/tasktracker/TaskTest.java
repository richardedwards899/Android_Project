package com.codeclan.example.tasktracker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by richardedwards on 26/05/2017.
 */
public class TaskTest {

    @Test
    public void canCreateTask(){

        Task task = new Task("Make bed", 15, "Flump pillows and duvet, tuck in sheet.");

        assertEquals("Make bed", task.getName());
        assertEquals(15, task.getPence());
        assertEquals("Flump pillows and duvet, tuck in sheet.", task.getDescription());
    }

    @Test
    public void canMarkAsDone(){
        Task task = new Task("Make bed", 16, "Flump pillows and duvet, tuck in sheet.");
        task.markAsComplete();
        assertEquals(true, task.completed());
        assertEquals(1, task.timesCompleted());
    }


}