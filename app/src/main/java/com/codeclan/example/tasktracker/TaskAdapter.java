package com.codeclan.example.tasktracker;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by richardedwards on 26/05/2017.
 */

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(Context context, ArrayList<Task> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        Task currentTask = getItem(position);

        //creates a handle to the TextView on the layout
        TextView taskName = (TextView) listItemView.findViewById(R.id.task_text);
        taskName.setText(currentTask.getName()+" ("+currentTask.getPence()+"p)");

        //creates a handle to the CheckBox on the layout
        CheckBox checkbox = (CheckBox) listItemView.findViewById(R.id.check_box);
        //sets it to be checked or not depending on task status
        checkbox.setChecked(currentTask.completed());

        listItemView.setTag(currentTask);
        listItemView.setTag(R.id.pence, position); //bodge! We're borrowing the pence id to pass back a value

        return listItemView;
    }

}


