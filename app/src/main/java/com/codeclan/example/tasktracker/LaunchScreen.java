package com.codeclan.example.tasktracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson; //Needed to add 'compile 'com.google.code.gson:gson:2.3.1'' to build.gradle to get this to work...
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LaunchScreen extends AppCompatActivity {

    private SharedPreferences preferences;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        //this bit is new!  Creates a link to the toolbar on the layout.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //set a Toolbar to act as the ActionBar for this Activity window.
        setSupportActionBar(toolbar);

        //New! Creates a link to the button on the layout.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_button);
    }

    public void onFabClick(View view){
        //Code here to take you to an add task screen...

        Snackbar.make(view, "Trying to add a new Task!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_Freya) {
            //Display a list of all Freya's tasks!
            User freya = new User("Freya");
            displayTasks(freya);
        }
        if (id == R.id.action_Eliza) {
            //Display a list of all Eliza's tasks!
            User eliza = new User("Eliza");
            displayTasks(eliza);
        }
        return super.onOptionsItemSelected(item);
    }

    private String getTasksFromSharedPreferences(User user) {
        //Creates link to the user's SharedPreferences
        String sharedPrefsKey = user.getName()+"_prefs";
        preferences = getSharedPreferences(sharedPrefsKey, Context.MODE_PRIVATE); //so only our app can use shared info

        ArrayList<String> tasksJSON = new ArrayList<>();
        for (Task t : user.getTasks()) {
            String tJson = gson.toJson(t);
            tasksJSON.add(tJson);
        }
        //second parameter is what we get if the requested key isn't found
        return preferences.getString("tasks", tasksJSON.toString());
    }

    private ArrayList<Task> getTasksFromJSon(String json){
        //we're going to use a library for constructing objects from Json
        gson = new Gson();
        //the type we're expecting to get back
        TypeToken<ArrayList<Task>> taskArrayList = new TypeToken<ArrayList<Task>>(){};  //something about anonymous inner classes!
        ArrayList<Task> userTasks = gson.fromJson(json, taskArrayList.getType());

        return userTasks;
    }

    private boolean displayTasks(User user){
        //Instead of using the default tasks, we want to read them in from SharedPreferences.
        String userTasksJson = getTasksFromSharedPreferences(user);

        //1 - Load favourites
        ArrayList<Task> userTasks = getTasksFromJSon(userTasksJson);

        //The following 3 lines have been adapted from TopMoviesActivity
        TaskAdapter taskAdapter = new TaskAdapter(this, userTasks);
        ListView listView = (ListView) findViewById(R.id.task_list);
        listView.setAdapter(taskAdapter);

        return true;
    }
}
