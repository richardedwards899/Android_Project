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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.CheckBox;

import com.google.gson.Gson; //Needed to add 'compile 'com.google.code.gson:gson:2.3.1'' to build.gradle to get this to work...
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LaunchScreen extends AppCompatActivity {

    private SharedPreferences preferences;
    private Gson gson = new Gson();
    private User freya = new User("Freya");
    private User eliza = new User("Eliza");
    private User currentUser;
    private TextView nameBox;
    private CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        decideCurrentUser();

        //Creates a link to the toolbar on the layout.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //set a Toolbar to act as the ActionBar for this Activity window.
        setSupportActionBar(toolbar);
        //Creates a link to the button on the layout.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_button);
        //creates a link to the TextView on the layout.
        nameBox = (TextView) findViewById(R.id.child_name);

        //need to load each user with tasks from Json
        initialiseUserWithJSon(freya);
        initialiseUserWithJSon(eliza);

        displayContentFor(currentUser);
    }

    private void decideCurrentUser() {
        User passedInUser = (User) getIntent().getSerializableExtra("user");

        if ( passedInUser != null){
            if (passedInUser.getName().equals("Freya")){
                currentUser = freya;
            }
            else if (passedInUser.getName().equals("Eliza")){
                currentUser = eliza;
            }
        }
        else {
            currentUser = freya;
        }
    }

    private void setNameAndTotal(){
        nameBox.setText(currentUser.getName()+": £"+currentUser.valueOfCompletedTasks()/100.0);
    }

    private void initialiseUserWithJSon(User user){
        String userTasksJson = getTasksFromSharedPreferences(user);
        ArrayList<Task> userTasks = getTasksFromJSon(userTasksJson);
        user.setTasks(userTasks);
    }

    public void onFabClick(View view){
        //adds a user to the intent, so that we can access it on the other side!
        Intent intent = new Intent(this, AddTaskActivity.class);
        intent.putExtra("user", currentUser);
        startActivity(intent);
    }

    public void onCheckboxClicked(View view){
        View listItem = (View) view.getParent();  //gets the listItem on which the CheckBox sits
        int indexOfTask = (int) listItem.getTag(R.id.task_position);

        if (((CheckBox) view).isChecked()){
            currentUser.completeTask(indexOfTask);
        }
        else {
            currentUser.uncompleteTask(indexOfTask);
        }
        setNameAndTotal();
        saveTasks(currentUser.getTasks());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_Freya) {
            displayContentFor(freya);
        }
        if (id == R.id.action_Eliza) {
            displayContentFor(eliza);
        }
        if (id == R.id.menu_reset) {
            clearOutSharedPreferences();
            Intent intent = new Intent(this, LaunchScreen.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearOutSharedPreferences(){
        preferences = getSharedPreferences("Eliza_prefs", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
        preferences = getSharedPreferences("Freya_prefs", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }

    private void displayContentFor(User user){
        currentUser = user;
        displayTasks();
        setNameAndTotal();
    }

    private String getTasksFromSharedPreferences(User user) {
        //Creates link to the user's SharedPreferences
        String sharedPrefsKey = user.getName()+"_prefs";
        preferences = getSharedPreferences(sharedPrefsKey, Context.MODE_PRIVATE); //so only our app can use shared info

        ArrayList<String> tasksJSON = new ArrayList<>();
        for (Task task : user.getTasks()) {
            String tJson = gson.toJson(task); //converts a Task into JSON
            tasksJSON.add(tJson);
        }
        return preferences.getString("tasks", tasksJSON.toString());
    }

    private ArrayList<Task> getTasksFromJSon(String json){
        //we're going to use a library for constructing objects from Json
        gson = new Gson();
        //the type we're expecting to get back (involves something about anonymous inner classes!)
        TypeToken<ArrayList<Task>> taskArrayList = new TypeToken<ArrayList<Task>>(){};
        ArrayList<Task> userTasks = gson.fromJson(json, taskArrayList.getType());

        return userTasks;
    }

    private boolean displayTasks(){

        //Instead of using the default tasks, we want to read them in from SharedPreferences.
        String userTasksJson = getTasksFromSharedPreferences(currentUser);
        ArrayList<Task> userTasks = getTasksFromJSon(userTasksJson);

        //The following 3 lines have been adapted from TopMoviesActivity
        TaskAdapter taskAdapter = new TaskAdapter(this, userTasks);
        ListView listView = (ListView) findViewById(R.id.task_list);
        listView.setAdapter(taskAdapter);

        return true;
    }

    public void getTask(View listItem) {
        Task task = (Task) listItem.getTag();

        Intent intent = new Intent(this, TaskDetailActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
    }

    private void saveTasks(ArrayList<Task> tasks) {
        //Creates link to the user's SharedPreferences
        String sharedPrefsKey = currentUser.getName()+"_prefs";
        Log.d("SharedPrefences key:", sharedPrefsKey);
        preferences = getSharedPreferences(sharedPrefsKey, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        //Log.d("TASKS", gson.toJson(tasks));
        editor.putString("tasks", gson.toJson(tasks));
        editor.apply();
    }
}
