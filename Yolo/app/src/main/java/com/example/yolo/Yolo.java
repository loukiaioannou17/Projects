
/*
   This file implements all methods regarding the YOLO aspect of the
   application
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

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Yolo {

    boolean firstTimeYolo = false;
    Net tinyYolo;
    Context ctx = null;
    ArrayList<String> coco = null;
    int[] objects=null;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Yolo(Context ctx){
        this.ctx = ctx;
    }

    public ArrayList<String> getCoco(){ return coco;}

    public int[] getObjects(){return objects;}

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void InitializeYolo() throws IOException, IOException {
       this.coco = coco;

                String YoloCfg = null, YoloWeights = null;
                File cfg = new File(ctx.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "/yolov3-tiny.cfg");
                File weights = new File(ctx.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "/yolov3-tiny.weights");

                if (!cfg.exists() && !weights.exists()) {
                    InputStream incfg = ctx.getAssets().open("yolov3-tiny.cfg");
                    int sizeCfg = incfg.available();
                    byte[] buffercfg = new byte[sizeCfg];
                    incfg.read(buffercfg);
                    incfg.close();

                    FileOutputStream outcgf = new FileOutputStream(cfg);
                    outcgf.write(buffercfg);
                    outcgf.close();
                    YoloCfg = cfg.getAbsolutePath();

                    InputStream isW = ctx.getAssets().open("yolov3-tiny.weights");
                    int sizeW = isW.available();
                    byte[] bufferW = new byte[sizeW];
                    isW.read(bufferW);
                    isW.close();

                    FileOutputStream fosW = new FileOutputStream(weights);
                    fosW.write(bufferW);
                    fosW.close();
                    YoloWeights = weights.getAbsolutePath();

                    File cocoFile = new File(ctx.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)+"/coco.names");

                    InputStream isCoco = ctx.getAssets().open("coco.names");
                    int sizeCoco = isCoco.available();
                    byte[] bufferCoco = new byte[sizeCoco];
                    isCoco.read(bufferCoco);
                    isCoco.close();

                    FileOutputStream fosCoco = new FileOutputStream(cocoFile);
                    fosCoco.write(bufferCoco);
                    fosCoco.close();




            }
        YoloCfg = ctx.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "/yolov3-tiny.cfg";
        YoloWeights = ctx.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "/yolov3-tiny.weights";
        tinyYolo = Dnn.readNetFromDarknet(YoloCfg, YoloWeights);

        FileReader fr = new FileReader(ctx.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "/coco.names");
        BufferedReader buff = new BufferedReader(fr);
        coco = new ArrayList<String>();
        String line ;

        do{
            line = buff.readLine();
            coco.add(line);
        }while(line!=null);
        objects = new int[coco.size()];

        for(int j=0;j<objects.length;j++)
            objects[j]=0;


    }

    public Mat ProcessFrame(Mat frame) {

        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGBA2RGB);
        Mat imageBlob = Dnn.blobFromImage(frame, 0.00392, new Size(288,288),new Scalar(0, 0, 0),false, false);
        tinyYolo.setInput(imageBlob);
        java.util.List<Mat> result = new java.util.ArrayList<Mat>(2);
        List<String> outBlobNames = new java.util.ArrayList<>();
        outBlobNames.add(0, "yolo_16");//TINYYOLO
        outBlobNames.add(1, "yolo_23");
        tinyYolo.forward(result,outBlobNames);
        float confThreshold = 0.3f;
        List<Integer> clsIds = new ArrayList<>();
        List<Float> confs = new ArrayList<>();
        List<Rect> rects = new ArrayList<>();
        for (int i = 0; i < result.size(); ++i) {
            Mat level = result.get(i);
            for (int j = 0; j < level.rows(); ++j) {
                Mat row = level.row(j);
                Mat scores = row.colRange(5, level.cols());
                Core.MinMaxLocResult mm = Core.minMaxLoc(scores);
                float confidence = (float)mm.maxVal;
                Point classIdPoint = mm.maxLoc;
                if (confidence > confThreshold)
                {
                    int centerX = (int)(row.get(0,0)[0] * frame.cols());
                    int centerY = (int)(row.get(0,1)[0] * frame.rows());
                    int width   = (int)(row.get(0,2)[0] * frame.cols());
                    int height  = (int)(row.get(0,3)[0] * frame.rows());
                    int left    = centerX - width  / 2;
                    int top     = centerY - height / 2;
                    clsIds.add((int)classIdPoint.x);
                    confs.add((float)confidence);
                    rects.add(new Rect(left, top, width, height));
                }
            }
        }
        int ArrayLength = confs.size();
        if (ArrayLength>=1) {
            // Apply non-maximum suppression procedure.
            float nmsThresh = 0.2f;
            MatOfFloat confidences = new MatOfFloat(Converters.vector_float_to_Mat(confs));
            Rect[] boxesArray = rects.toArray(new Rect[0]);
            MatOfRect boxes = new MatOfRect(boxesArray);
            MatOfInt indices = new MatOfInt();
            Dnn.NMSBoxes(boxes, confidences, confThreshold, nmsThresh, indices);
            // Draw result boxes:
            int[] ind = indices.toArray();
            for (int i = 0; i < ind.length; ++i) {
                int idx = ind[i];
                Rect box = boxesArray[idx];
                int idGuy = clsIds.get(idx);
                float conf = confs.get(idx);
               // List<String> coco = Arrays.asList("a person", "a bicycle", "a motorbike", "an airplane", "a bus", "a train", "a truck", "a boat", "a traffic light", "a fire hydrant", "a stop sign", "a parking meter", "a car", "a bench", "a bird", "a cat", "a dog", "a horse", "a sheep", "a cow", "an elephant", "a bear", "a zebra", "a giraffe", "a backpack", "an umbrella", "a handbag", "a tie", "a suitcase", "a frisbee", "skis", "a snowboard", "a sports ball", "a kite", "a baseball bat", "a baseball glove", "a skateboard", "a surfboard", "a tennis racket", "a bottle", "a wine glass", "a cup", "a fork", "a knife", "a spoon", "a bowl", "a banana", "an apple", "a sandwich", "an orange", "broccoli", "a carrot", "a hot dog", "a pizza", "a doughnut", "a cake", "a chair", "a sofa", "a potted plant", "a bed", "a dining table", "a toilet", "a TV monitor", "a laptop", "a computer mouse", "a remote control", "a keyboard", "a cell phone", "a microwave", "an oven", "a toaster", "a sink", "a refrigerator", "a book", "a clock", "a vase", "a pair of scissors", "a teddy bear", "a hair drier", "a toothbrush","door","elevator door","stairs","exit sign","no smoking","fire drill switch","fire hose","fire extinguisher","cone");
                int intConf = (int) (conf * 100);
                Imgproc.putText(frame, coco.get(idGuy) + " " + intConf + "%", box.tl(), Core.FONT_HERSHEY_SIMPLEX, 2, new Scalar(255, 255, 0), 2);
                Imgproc.rectangle(frame, box.tl(), box.br(), new Scalar(255, 0, 0), 2);
                objects[idGuy] = objects[idGuy] + 1;

            }
        }
        return frame;
    }

}
