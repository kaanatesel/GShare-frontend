package com.example.gshare;

import android.os.StrictMode;

import com.example.gshare.ModelClasses.User.User;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class CallUserByEmail {

        public static User call(String email) throws IOException, JSONException {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://35.242.192.20/member/getByEmail/" + email)
                    .method("GET", null)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("User-Agent", "PostmanRuntime/7.21.0")
                    .addHeader("Accept", "*/*")
                    .addHeader("Cache-Control", "no-cache")
                    .addHeader("Postman-Token", "572694fe-7ade-4caf-9f9d-5b95f69019cf")
                    .addHeader("Host", "35.242.192.20")
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Connection", "keep-alive")
                    .build();

            ResponseBody responseBody = client.newCall(request).execute().body();
            Gson gson = new Gson();
            User user = gson.fromJson(responseBody.string(),User.class);
            user.setUserName(user.getEmail());

            return user;
        }
    }

