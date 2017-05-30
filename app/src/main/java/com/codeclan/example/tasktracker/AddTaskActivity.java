package com.codeclan.example.tasktracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
        Log.d("CurrentUser's tasks:", new Integer(currentUser.getTasks().size()).toString());
    }

    public void onAddButtonClick(View view){
        //Create links to the input fields on the layout
        EditText taskNameBox = (EditText) findViewById(R.id.task_name);
        EditText penceBox = (EditText) findViewById(R.id.pence);

        //Grab their contents
        String taskName = taskNameBox.getText().toString();
        String penceString = penceBox.getText().toString();

        //validate input
        if (taskName.length()==0 ){
            Snackbar.make(view, "Please enter a task description", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        if (penceString.length()==0){
            Snackbar.make(view, "Please enter task's value in pence", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        int pence = Integer.parseInt(penceString);

        //Create a new Task
        Task task = new Task(taskName, pence, "");
        currentUser.addTask(task);

        //Save updated Task list to SharedPreferences
        saveTasks(currentUser.getTasks());

        //Move to a display (list) layout. For now, let's move back to the LaunchScreen...
        Intent intent = new Intent(this, LaunchScreen.class);
        startActivity(intent);
    }

    private void saveTasks(ArrayList<Task> tasks) {
        //Creates link to the user's SharedPreferences
        String sharedPrefsKey = currentUser.getName()+"_prefs";
        Log.d("SharedPrefences key:", sharedPrefsKey);
        currentUserPreferences = getSharedPreferences(sharedPrefsKey, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = currentUserPreferences.edit();
        editor.clear();
        Log.d("TASKS", gson.toJson(tasks));
        editor.putString("tasks", gson.toJson(tasks));
        editor.apply();
    }
}
