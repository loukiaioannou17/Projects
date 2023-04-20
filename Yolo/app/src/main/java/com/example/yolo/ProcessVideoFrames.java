
/*
   This file implements the method that process the video frames
   that would be shown in the video frames.
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

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.IOException;
import java.util.concurrent.Executor;

public class ProcessVideoFrames implements Executor{

    private static final String TAG = "anyplace-cv-task";
    private ImageView IM;
    private Uri videoPath;
    private Context ctx;
    private int frameCount = 0;
    private Activity activity;
    public boolean pauseVideo = false;


    public ProcessVideoFrames(ImageView IM, Uri videoPath, Activity activity){
        this.videoPath = videoPath;
        this.IM = IM;
        this.ctx = activity.getApplicationContext();
        this.activity = activity;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void execute(Runnable r) {
        OpenCVLoader.initDebug();
        if (Looper.myLooper() == null)
            Looper.prepare();
        new Thread(r).start();
        try {
            ProcessVideo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void ProcessVideo() throws  IOException {
        final MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(ctx,videoPath);
        String METADATA_KEY_DURATION = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        final int duration = (int) Long.parseLong(METADATA_KEY_DURATION);
        //final int stepping = 1000 / 24;
        //final int stepping = duration / 30;
        Log.d(TAG,"duration : " + duration);
        int count = 0;
        Yolo net = new Yolo(ctx);
        net.InitializeYolo();
        for(int i=0;i<=duration;i++) {
            if (pauseVideo)
                count = i;
            else
                i = count;
            Bitmap bmp = mmr.getFrameAtTime(i * 1000000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
            Log.d(TAG, "processed : " + frameCount);
            Mat mat = new Mat();
            Bitmap bmp32 = bmp.copy(Bitmap.Config.ARGB_8888, true);
            Utils.bitmapToMat(bmp32, mat);
            Mat frame = net.ProcessFrame(mat);
            bmp = Bitmap.createBitmap(frame.cols(), frame.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(frame, bmp);
            UpdateUI(bmp);
            frameCount++;
            /*try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            Log.d(TAG,"frame : " + frameCount);

        }
        Log.d(TAG,"total processed : " + frameCount);

    }

    public void UpdateUI(final Bitmap bmp){
        activity.runOnUiThread(new Runnable(){
            @Override
            public void run() {
                IM.setImageBitmap(bmp);
            }
        });

    }


}

