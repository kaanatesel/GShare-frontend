package com.example.gshare;
import java.io.*;
import okhttp3.*;

public class NoticePuller {

        public static void pullAllActiveLending() throws IOException{
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
            System.out.println(response.body().string());
        }

}
