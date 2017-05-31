package com.codeclan.example.tasktracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Task taskToView = (Task) getIntent().getSerializableExtra("task");

        //create handles to the boxes on the layout and assign values
        TextView textView = (TextView) findViewById(R.id.taskname_detail);
        TextView pointsView = (TextView) findViewById(R.id.taskpence_detail);
        TextView descriptionBox = (TextView) findViewById(R.id.task_description_detail);

        textView.setText(taskToView.getName());
        pointsView.setText(new Integer(taskToView.getPence()).toString());
        descriptionBox.setText(taskToView.getDescription());

        Toast.makeText(this, "Task details", Toast.LENGTH_LONG).show();
    }
}
