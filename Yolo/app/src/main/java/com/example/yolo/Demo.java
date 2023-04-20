

/*
   This file implements the mode of the app that shows a video with the
   recognized position.
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

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.OpenCVLoader;

import java.io.IOException;


public class Demo extends AppCompatActivity {


    private static final String TAG = "anyplace-cv";
    BaseLoaderCallback baseLoaderCallback;
    ImageView IM = null;
    String filename = "";
    ProcessVideoFrames task = null;
    Button hello = null;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        IM = findViewById(R.id.ivYOLO);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            filename = (String) bundle.get("filename");
        }

        //final TextView tvTest = findViewById(R.id.tvTest);
        // todo1 : assign letter a to background
        // todo2 = assign letter b to background without wait
        initializePlayer();
        baseLoaderCallback = new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                super.onManagerConnected(status);
                switch (status) {
                    case BaseLoaderCallback.SUCCESS:

                        break;
                    default:
                        super.onManagerConnected(status);
                        break;
                }
            }
        };
    }

    private Uri getMedia(String mediaName) {
        return Uri.parse(mediaName);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initializePlayer() {

        Uri video = getMedia(getExternalFilesDir(Environment.DIRECTORY_MOVIES) + "/" + filename);
        task = new ProcessVideoFrames(IM,video,this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    task.ProcessVideo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void PlayButton(View Button){
        Button play  =  findViewById(R.id.play);

        if (task.pauseVideo == true){
            task.pauseVideo = false;
            play.setBackgroundResource(android.R.drawable.ic_media_play);
            }
        else{
            task.pauseVideo = true;
            play.setBackgroundResource(android.R.drawable.ic_media_pause);

        }



    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onStart() {
        super.onStart();
        //initializePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //releasePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
           // mVideoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug())
            Toast.makeText(getApplicationContext(),"There's a problem, yo!", Toast.LENGTH_SHORT).show();
        else
            baseLoaderCallback.onManagerConnected(baseLoaderCallback.SUCCESS);
        }



}