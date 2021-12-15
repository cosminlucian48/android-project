package com.example.proiect_real.data;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class DataConverter{


    public static String fromListToJson(List<Integer> grades) {
        if (grades == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        String json = gson.toJson(grades, type);
        return json;
    }


    public static List<Integer> fromJsonToList(String gradesString) {
        if (gradesString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {
        }.getType();
        List<Integer> grades = gson.fromJson(gradesString, type);
        return grades;
    }

}