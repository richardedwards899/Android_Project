package com.codeclan.example.tasktracker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by richardedwards on 26/05/2017.
 */
public class TaskTest {

    @Test
    public void canCreateTask(){

        Task task = new Task("Make bed", 1, "Flump pillows and duvet, tuck in sheet.");

        assertEquals("Make bed", task.getName());
        assertEquals(1, task.getPoints());
        assertEquals("Flump pillows and duvet, tuck in sheet.", task.getDescription());
    }


}