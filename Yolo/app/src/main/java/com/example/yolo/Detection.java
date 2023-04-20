
/*
   This file implements the method of choosing items that are not going
   to be detected by the camera.
    Copyright (C) 2021  loukia Ioannou
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see https://www.gnu.org/licenses/gpl-3.0.html.
*/

package com.example.yolo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Detection extends AppCompatActivity {

    public boolean navigation=false;

    public void Continue(View view){
        ScrollView mitem = (ScrollView) findViewById(R.id.movingobjects);
        LinearLayout mlist = (LinearLayout) mitem.getChildAt(0);
        ArrayList<String> NotDetect = new ArrayList<String>();
        for (int i = 0 ; i<mlist.getChildCount(); i++) {
            CheckBox ch1= (CheckBox) mlist.getChildAt(i);
            if (!ch1.isChecked())
                NotDetect.add((String) ch1.getText());
        }

        ScrollView sitem = (ScrollView) findViewById(R.id.staticobjects);
        LinearLayout slist = (LinearLayout) sitem.getChildAt(0);
        for (int i = 0 ; i<slist.getChildCount(); i++) {
            CheckBox ch1= (CheckBox) slist.getChildAt(i);
            if (!ch1.isChecked())
                NotDetect.add((String) ch1.getText());
        }


        Intent intent = new Intent(this, CameraVision.class);

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("ArrayList",NotDetect);
        bundle.putBoolean("navigation", navigation);
        intent.putExtras(bundle);

        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection);

        Bundle bundle = getIntent().getExtras();
        navigation = bundle.getBoolean("navigation");

        ScrollView item = (ScrollView) findViewById(R.id.movingobjects);
        LinearLayout list = (LinearLayout) item.getChildAt(0);
        List<String> checkBox = Arrays.asList("a person", "a bicycle", "a motorbike", "an airplane", "a bus", "a train", "a truck", "a boat", "a car", "a bird", "a cat", "a dog", "a horse", "a sheep", "a cow", "an elephant", "a bear", "a zebra", "a giraffe", "a backpack", "an umbrella", "a handbag", "a tie", "a suitcase", "a frisbee", "skis", "a snowboard", "a sports ball", "a kite", "a baseball bat", "a baseball glove", "a skateboard", "a surfboard", "a tennis racket", "a bottle", "a wine glass", "a cup", "a fork", "a knife", "a spoon", "a bowl", "a banana", "an apple", "a sandwich", "an orange", "broccoli", "a carrot", "a hot dog", "a pizza", "a doughnut", "a cake", "a potted plant" , "a remote control", "a cell phone", "a book", "a clock", "a vase", "a pair of scissors", "a teddy bear", "a hair drier", "a toothbrush");
        for(int i=0;i<checkBox.size();i++){
            CheckBox ch = new CheckBox(this);
            ch.setText(checkBox.get(i));
            ch.setChecked(true);
            list.addView(ch);
        }

        ScrollView sitem = (ScrollView) findViewById(R.id.staticobjects);
        LinearLayout slist = (LinearLayout) sitem.getChildAt(0);
        List<String> scheckbox = Arrays.asList("a traffic light", "a fire hydrant", "a stop sign", "a parking meter", "a bench","a chair", "a sofa", "a microwave", "an oven", "a toaster", "a sink", "a refrigerator","a bed", "a dining table", "a toilet", "a TV monitor", "a laptop", "a computer mouse", "a keyboard");
        for(int i=0;i<scheckbox.size();i++){
            CheckBox ch = new CheckBox(this);
            ch.setChecked(true);
            ch.setText(scheckbox.get(i));
            slist.addView(ch);
        }

    }
}