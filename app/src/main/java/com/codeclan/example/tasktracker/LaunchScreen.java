package com.codeclan.example.tasktracker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class LaunchScreen extends AppCompatActivity {

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do some stuff here

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Freya) {
            //Display a list of all Freya's tasks!
            User freya = new User("Freya");
            ArrayList<Task> freyaTasks = freya.getTasks();

            //The following 3 lines have been adapted from TopMoviesActivity
            TaskAdapter taskAdapter = new TaskAdapter(this, freyaTasks);
            ListView listView = (ListView) findViewById(R.id.task_list);
            listView.setAdapter(taskAdapter);

            return true;
        }
        if (id == R.id.action_Eliza) {
            //Display a list of all Eliza's tasks!
            User eliza = new User("Eliza");
            ArrayList<Task> elizaTasks = eliza.getTasks();

            //The following 3 lines have been adapted from TopMoviesActivity
            TaskAdapter taskAdapter = new TaskAdapter(this, elizaTasks);
            ListView listView = (ListView) findViewById(R.id.task_list);
            listView.setAdapter(taskAdapter);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
