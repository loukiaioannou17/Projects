

/*
   This file implements the screen with the camera that recognized
   and saves the objects that are recognized.
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

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.ArrayList;

public class CameraVision extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    CameraBridgeViewBase cameraBridgeViewBase;
    BaseLoaderCallback baseLoaderCallback;
    boolean startYolo = false;
    ArrayList<String> notDetect = new ArrayList<String>();

    private ArrayList<Double> coordinates = new ArrayList<Double>();
    private Yolo net = null;
    public DatabaseHandler db = null;
    public boolean logging = false;
    public boolean navigation = false;
    int[] objects;
    ArrayList<String> cocoNames = null;
    LocationManager locationManager = null;

    static final int PERMISSION_REQUEST_CODE = 200;

    public void DetectObjects(View Button) {
        Intent intent = new Intent(this, Detection.class);

        Bundle bundle = new Bundle();
        bundle.putBoolean("navigation",navigation);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void Back(View Button) {
        Intent intent = new Intent(this,Choose_Log_Nav.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void YOLO(View Button) throws IOException {
        if (startYolo == false) {
            startYolo = true;
            net.InitializeYolo();
            cocoNames = net.getCoco();

        } else {
            db = new DatabaseHandler(this);
            db.CreateCocoList(cocoNames);

            if (logging) {
                coordinates = new ArrayList<Double>();
                EditText input = (EditText) findViewById(R.id.editText);
                String position = input.getText().toString();
                double pos1 = Double.parseDouble(position);
                coordinates.add(pos1);
                coordinates.add(pos1);
                if (coordinates == null || coordinates.size()==0){
                    AlertDialog.Builder alert = new AlertDialog.Builder(CameraVision.this);
                    alert.setTitle("Cant add the location");
                    alert.setMessage("No GPS signal found");
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }

                    });
                    alert.show();}
                else {
                    objects=net.getObjects();
                    for(int j=0;j<objects.length;j++)
                        if(objects[j]>0)
                            objects[j]=1;

                    db.addLocation(objects, coordinates);
                    AlertDialog.Builder alert = new AlertDialog.Builder(CameraVision.this);
                    alert.setTitle("Alert");
                    alert.setMessage("Succesfully added the new location");
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                    });
                    alert.show();
                }

            } else {
                objects= net.getObjects();
                for(int j=0;j<objects.length;j++)
                   if(objects[j]>0)
                        objects[j]=1;

                ArrayList<Double> cord = db.getLocation(objects);
                AlertDialog.Builder alert = new AlertDialog.Builder(CameraVision.this);
                if(cord == null){
                    alert.setTitle("Error.");
                    alert.setMessage("No known location found");
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }

                    });
                }else {
                    alert.setTitle("Your location is ");
                    String msg = cord.get(0) +  "\n";
                   // for(int i=0;i<objects.length;i++)
                    //    msg = msg + cocoNames.get(i) + " " + objects[i] + "\n";

                    alert.setMessage(msg);

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }

                    });
                }
                alert.show();
            }
            TextView record = findViewById(R.id.textView2);
            record.setVisibility(View.INVISIBLE);
            Button Buttonyolo = findViewById(R.id.button3);
            Buttonyolo.setText("Start Recording");
            Buttonyolo.setBackgroundColor(Color.GREEN);
            startYolo = false;
        }
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED)
            // Permission is not granted
            return false;
        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                            }
                                        }
                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(CameraVision.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera);


        net = new Yolo(getApplicationContext());



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            notDetect = bundle.getStringArrayList("ArrayList");
            logging = !bundle.getBoolean("navigation");
            if (notDetect == null) {
                logging = bundle.getBoolean("logging");
                navigation = !logging;
            }
        }
        if (checkPermission()) {

            cameraBridgeViewBase = (CameraBridgeViewBase) findViewById(R.id.CameraView);
            cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);

            cameraBridgeViewBase.setMinimumHeight(1920);
            cameraBridgeViewBase.setMinimumWidth(1080);
            cameraBridgeViewBase.setMaxFrameSize(1080, 1920);

            cameraBridgeViewBase.setCvCameraViewListener(this);
            //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            baseLoaderCallback = new BaseLoaderCallback(this) {
                @Override
                public void onManagerConnected(int status) {
                    super.onManagerConnected(status);
                    switch (status) {
                        case BaseLoaderCallback.SUCCESS:
                            cameraBridgeViewBase.enableView();
                            break;
                        default:
                            super.onManagerConnected(status);
                            break;
                    }
                }
            };

        } else
            requestPermission();
    }

    public ArrayList<Double> getGPSlocation() {

            locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
            if (ContextCompat.checkSelfPermission(CameraVision.this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(CameraVision.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(CameraVision.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {

                    Double lon = location.getLongitude();
                    Double lat = location.getLatitude();
                    coordinates.add(lon);
                    coordinates.add(lat);

                }
            });

            return coordinates;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        Mat temporary = inputFrame.rgba();
        Mat frame = temporary.t();

        Core.flip(frame, frame, 1);
        Imgproc.resize(frame,frame,temporary.size());

        if (startYolo == true){
            TextView record = findViewById(R.id.textView2);
            record.setVisibility(View.INVISIBLE);
            Button Buttonyolo =  findViewById(R.id.button3);
            Buttonyolo.setText("Stop Recording");
            Buttonyolo.setBackgroundColor(Color.RED);

            return net.ProcessFrame(frame);

        }
        return frame;
    }

    @Override
    public void onCameraViewStarted(int width, int height) { }

    @Override
    public void onCameraViewStopped() { }

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug())
            Toast.makeText(getApplicationContext(),"There's a problem, yo!", Toast.LENGTH_SHORT).show();
        else
            baseLoaderCallback.onManagerConnected(baseLoaderCallback.SUCCESS);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(cameraBridgeViewBase!=null)
            cameraBridgeViewBase.disableView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraBridgeViewBase!=null){
            cameraBridgeViewBase.disableView();
        }
    }
}