
/*
   This file implements all methods regarding the databse of the app.
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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "fingerprints";
    private static final String TABLE_NAME = "table_fingerprints";
    private static List<String> cocoNames = null;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void CreateCocoList(List<String> cocoNames){ this.cocoNames = cocoNames; }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_FINGERPRINTS_TABLE = "CREATE TABLE "+ TABLE_NAME+" (\n" +
                "coordinateX float,\n" +
                "coordinateY float,\n";
        for(int i=0;i<cocoNames.size()-2;i++)
            CREATE_FINGERPRINTS_TABLE = CREATE_FINGERPRINTS_TABLE + cocoNames.get(i) +" int, \n";

        CREATE_FINGERPRINTS_TABLE = CREATE_FINGERPRINTS_TABLE + cocoNames.get(cocoNames.size()-2)+ " int\n"+");";

        db.execSQL(CREATE_FINGERPRINTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public void addLocation(int[] objects,ArrayList<Double> Coordinates){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("coordinateX",Coordinates.get(0));
        values.put("coordinateY",Coordinates.get(1));

        for(int i=0;i<cocoNames.size()-1;i++)
            values.put(cocoNames.get(i),objects[i]);



        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public boolean sameObjects(int[] seeing, int[] temp){
        int count=0;
        for(int i=0;i<temp.length;i++)
            if((seeing[i]!=0 && temp[i]==0) || (seeing[i]==0 && temp[i]!=0))
                return false;
        return true;
    }

    public ArrayList<Double> getLocation(int[] seeing){

        //HashMap<ArrayList<Double>, ArrayList<String>> data = new HashMap<>();
        ArrayList<Fingerprint> data = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        ArrayList<Double> coordinates = null;
        int[] objects = null;

        if(cursor.moveToFirst()){
            do{
                Fingerprint f = new Fingerprint();

                coordinates = new ArrayList<Double>();
                objects = new int[cocoNames.size()];

                f.setCoordinateX(Double.parseDouble(cursor.getString(0)));
                f.setCoordinateY(Double.parseDouble(cursor.getString(1)));

                for(int i=0;i<cocoNames.size()-1;i++)
                    objects[i] = Integer.parseInt(cursor.getString(i+2));

                f.setObjects(objects);
                data.add(f);

            }while(cursor.moveToNext());}

            double min = Double.MAX_VALUE;
            int minNo=0;
            double count=0;
            double sum=0;
            int flag = 0;

        for (int k=0;k<cocoNames.size();k++)
            if(seeing[k]!=0)  count++;
        if (count == 0 ) return null;
        Log.d("database","seeing count = "+count);

            for(int j=0;j<data.size();j++) {
                Log.d("database","NEW FINGERPRINT");
                sum =0;
                int[] temp = data.get(j).getObjects();//each fingerprint from database


                    for(int h=0;h<cocoNames.size();h++)
                        if(seeing[h]!=0 || temp[h]!=0) {
                            sum = sum + (1 / count) * Math.abs(seeing[h] - temp[h]);
                            Log.d("database","sum = "+sum + " h = "+h);
                        }

                if (sum<min){
                   flag = 1;
                    min = sum;
                    minNo=j;
                }
            }
                if (flag == 1){
                    ArrayList<Double> location = new ArrayList<>();
                    location.add(data.get(minNo).getCoordinateX());
                    location.add(data.get(minNo).getCoordinateY());
                    return location;}
                else
                    return null;
            }



        }


