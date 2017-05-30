package com.codeclan.example.tasktracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    private User currentUser;
    private Gson gson = new Gson();
    private SharedPreferences currentUserPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        currentUser = (User) getIntent().getSerializableExtra("user");
    }

    public void onAddButtonClick(View view){
        //Create links to the input fields on the layout
        EditText taskNameBox = (EditText) findViewById(R.id.task_name);
        EditText pointsBox = (EditText) findViewById(R.id.points);

        //Grab their contents
        String taskName = taskNameBox.getText().toString();
        String pointsString = pointsBox.getText().toString();

        //validate input
        if (taskName.length()==0 || pointsString.length()==0){
            Snackbar.make(view, "Please enter a task description and number of points", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        int points = Integer.parseInt(pointsString);

        //Create a new Task
        Task task = new Task(taskName, points, "");
        currentUser.addTask(task);

        //Save updated Task list to SharedPreferences
        saveFavourites(currentUser.getTasks());

        //Move to a display (list) layout. For now, let's move back to the LaunchScreen...
        Intent intent = new Intent(this, LaunchScreen.class);
        startActivity(intent);
    }

    private void saveFavourites(ArrayList<Task> tasks) {
        //Creates link to the user's SharedPreferences
        String sharedPrefsKey = currentUser.getName()+"_prefs";
        currentUserPreferences = getSharedPreferences(sharedPrefsKey, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = currentUserPreferences.edit();
        editor.putString("tasks", gson.toJson(tasks));
        editor.apply();
    }
}
