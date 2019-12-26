package com.example.gshare;
import android.os.StrictMode;

import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

import okhttp3.*;

public class NoticePuller {

        public static void pullAllActiveLending() throws IOException, JSONException {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://35.242.192.20/product/getAllActive/")
                    .method("GET", null)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("User-Agent", "PostmanRuntime/7.21.0")
                    .addHeader("Accept", "*/*")
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Postman-Token", "ba41310a-9f82-4076-a202-e5723d389baa")
                    .addHeader("Host", "35.242.192.20")
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Connection", "keep-alive")
                    .build();
            Response response = client.newCall(request).execute();

            ArrayList<Notice> noticeArrayList = new ArrayList<Notice>();
            JSONArray jsonArray = new JSONArray(response.body());

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject jresponse = jsonArray.getJSONObject(i);

                System.out.println(jresponse.getString("name") );
            }

            System.out.println(response.body().string());
        }

}
