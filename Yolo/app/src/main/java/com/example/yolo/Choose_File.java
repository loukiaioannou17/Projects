
/*
   This file implements the screen which the user selects which video file
   to be choosen.
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
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class Choose_File extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_file);

        File folder = new File(getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString());
        File [] listofFiles = folder.listFiles();

        ScrollView scroll = (ScrollView) findViewById(R.id.Scroll);
        LinearLayout list = (LinearLayout) scroll.getChildAt(0);
        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);
        RadioButton[] rb = new RadioButton[listofFiles.length];

        for (int i=0;i<listofFiles.length;i++)
            if (listofFiles[i].isFile()){
                rb[i] = new RadioButton(this);
                rb[i].setText(listofFiles[i].getName());
                rb[i].setId(i);
                rg.addView(rb[i]);
            }
        list.addView(rg);


    }

    public void ContinueDemo(View view){
        String filename= "";
        ScrollView scroll = (ScrollView) findViewById(R.id.Scroll);
        LinearLayout list = (LinearLayout) scroll.getChildAt(0);
        RadioGroup rg = (RadioGroup) list.getChildAt(0);
        for (int i=0;i<rg.getChildCount();i++)
            if(rg.getCheckedRadioButtonId() == -1)
                Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
            else{
                int selectedId = rg.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = (RadioButton)findViewById(selectedId);
                filename = (String) selectedRadioButton.getText();
                Intent intent = new Intent(this, Demo.class);

                Bundle bundle = new Bundle();
                bundle.putString("filename",filename);

                intent.putExtras(bundle);

                startActivity(intent);
            }






    }


}
