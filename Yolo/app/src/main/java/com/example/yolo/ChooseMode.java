
/*
   This file implements the first start page to choose if the system
   will be used as demo or as used with the camera.
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

import androidx.appcompat.app.AppCompatActivity;

public class ChooseMode extends AppCompatActivity {

    public void Camera(View view){

        Intent intent = new Intent(this, Choose_Log_Nav.class);
        startActivity(intent);
    }

    public void Demo(View view){
        Intent intent = new Intent(this, Choose_File.class);
        startActivity(intent);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

}