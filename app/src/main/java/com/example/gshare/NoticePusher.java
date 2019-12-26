package com.example.gshare;

import android.os.StrictMode;
import com.example.gshare.ModelClasses.NoticeModel.Notice;

import org.json.JSONException;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NoticePusher {


    public static void push(Notice notice) throws IOException {

        if(notice.getNoticeType() == Notice.BORROW_NOTICE){
            pushBorrowing(notice);
        }
        else{
            pushLending(notice);
        }
    }

    private static void pushLending(Notice notice) throws IOException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        String description  =     notice.getNote();
        int memberId        =     notice.getNoticeOwner().getID();
        String name         =     notice.getName();
        int price           =     notice.getG();
        int productCategory =     notice.getCategory();


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType,
                "description="      + description       +
                        "&memberId="        + memberId          +
                        "&name="            + name              +
                        "&price="           + price             +
                        "&productCategory=" + productCategory   );


        Request request = new Request.Builder()
                .url("http://35.242.192.20/product/create/")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("User-Agent", "PostmanRuntime/7.21.0")
                .addHeader("Accept", "*/*")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "572694fe-7ade-4caf-9f9d-5b95f69019cf")
                .addHeader("Host", "35.242.192.20")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "keep-alive")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    private static void pushBorrowing(Notice notice) throws IOException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        String productDescription    =     notice.getNote();
        int requesterId              =     notice.getNoticeOwner().getID();
        int categoryId               =     notice.getCategory();


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType,
                "categoryId="            + categoryId               +
                        "&productDescription="   + productDescription       +
                        "&requesterId="          + requesterId              );


        Request request = new Request.Builder()
                .url("http://35.242.192.20/demand/create/")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("User-Agent", "PostmanRuntime/7.21.0")
                .addHeader("Accept", "*/*")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "572694fe-7ade-4caf-9f9d-5b95f69019cf")
                .addHeader("Host", "35.242.192.20")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "keep-alive")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

}
