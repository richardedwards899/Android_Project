package com.codeclan.example.tasktracker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by richardedwards on 26/05/2017.
 */
public class UserTest {

    private User user;

    @Before
    public void setup(){
        user = new User("Freya");
    }

    @Test
    public void canGetName(){
        assertEquals("Freya", user.getName());
    }

    @Test
    public void checkforTasksOnCreate(){
        assertEquals(10, user.getTasks().size());
    }

    @Test
    public void checkforOneTaskExists(){
        Task task = new Task("Hoover dog", 1, "Remove all loose hair from dog.");
        user.addTask(task);
        assertEquals(11, user.getTasks().size());
    }

    @Test
    public void checkforElevenTasksExist__Duplicates(){
        Task task = new Task("Hoover dog", 1, "Remove all loose hair from dog.");
        user.addTask(task);
        assertEquals(11, user.getDuplicateTasks().size());
    }

    @Test
    public void checkGetTaskMethod(){
        String expected = "Make bed";
        assertEquals(expected, user.getTask(0).getName());
    }
}