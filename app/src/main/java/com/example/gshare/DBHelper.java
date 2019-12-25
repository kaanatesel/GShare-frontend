package com.example.gshare;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LogPrinter;
import android.util.LruCache;
import android.widget.EditText;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

import com.example.gshare.ModelClasses.ChatModel.Chat;
import com.example.gshare.ModelClasses.ChatModel.Message;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.*;

public class DBHelper {
    private static DBHelper instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context ctx;

    EditText userName;
    EditText password;
    TextView err;
    //OkHttpClient httpClient;
    String passDB;


    //******
    private static User primaryUser; //This is the client User OBJ
    private static User tempUser = null;
    private static int primaryUserID;
    private static int tempUserID;
    ArrayList<User> userList;

    private DBHelper(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////GET USER DATA//////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**Works partially. Returns null in the first time. Returns obj in second time.
     *
     * @param email The users email
     * @return User Object
     */
    public static User getUser(String email) { //void for now
        return instance.callUserByEmail(email);

    }

    private User callUserByEmail(String email){


        String url = "http://35.246.134.158/member/getMyEmail/" + email + "/";

        OkHttpClient client = new OkHttpClient();

        userList = new ArrayList<User>();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                String mMessage = response.body().string();
                try {
                    JSONObject jsonOBJ = new JSONObject(mMessage);


                    JSONObject json = jsonOBJ;
                    User p1 = new User(
                            json.getString("id"),           //Name and Surname
                            json.getString("email"),        //userName
                            json.getString("password"),     //Password
                            json.getString("email"),        //email
                            json.getInt("g")         );     //g

                    userList.add( p1);

                } catch (JSONException e) { e.printStackTrace(); }

            }

        });
        try {
            return userList.get(0);
        }catch (java.lang.IndexOutOfBoundsException e){
            return null;
        }

    }

    /**Depricated and redundent but intuitive naming for user object
     *
     * @param ID user ID
     * @return The User
     */
    public static User getUser(int ID){
        return instance.callUserByID(ID);
    }

    private User callUserByID(int ID) {

        OkHttpClient client = new OkHttpClient();
        String url = "http://35.242.192.20/member/" + ID + "/";
        userList = new ArrayList<User>();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {

                String mMessage = response.body().string();
                try {
                    JSONObject jsonOBJ = new JSONObject(mMessage);


                    JSONObject json = jsonOBJ;
                    User p1 = new User(
                            json.getString("id"),           //Name and Surname
                            json.getString("email"),        //userName
                            json.getString("password"),     //Password
                            json.getString("email"),        //email
                            json.getInt("g")         );     //g

                    userList.add( p1);

                } catch (JSONException e) { e.printStackTrace(); }

            }

        });
        try {
            return userList.get(0);
        }catch (java.lang.IndexOutOfBoundsException e){
            return null;
        }
    }

    //Helper Methods for Users

    private static void createUserOBJ(int id, String name, String password, String email, int g) {


        tempUser = new User(name, email, password, email, g);
        tempUser.setID(id);

    }

    @Deprecated
    /**Gets all messages that belong to a notice
     * Potantial bugs!!! For every message a seperate user object is created in  getUser(receiverMemberId) , getUser( senderMemberId)
     * WARNING!!! NOT TESTED
     * @param borrowingNoticeID
     * @return messages Messages ArrayList
     * */
    public static ArrayList<Message> getMessages(int borrowingNoticeID){

        String url = "http://35.242.192.20//message/findByProductRequestId/" + borrowingNoticeID;

        RequestQueue mQueue = Volley.newRequestQueue(ctx);
        final ArrayList<Message> messages = new ArrayList<Message>();

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {


                            for(int i = 0; i < response.length(); i++){
                                JSONObject jresponse = response.getJSONObject(i);

                                String message  = jresponse.getString("message");
                                int receiverMemberId = jresponse.getInt("receiverMemberId");
                                int senderMemberId = jresponse.getInt("senderMemberId");
                                Message msg = new Message( message, getUser(receiverMemberId) , getUser( senderMemberId) );
                                messages.add(msg);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

        return messages;
    }

    private static  ArrayList<Notice> noticeArrayList = new ArrayList<Notice>();

    public static ArrayList<Notice> getLendingNotices(){

        String url = "http://35.242.192.20/demand/findAll/";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url ,null,  new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response) {

                System.out.println(response.length());
                for(int i = 0 ; i< response.length() ; i++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        System.out.println( jsonObject.getString("productDescription" ) );
                        System.out.println( jsonObject.getInt("categoryId" ) );




                        User owner = getUser( jsonObject.getInt("requesterId") );

                        Notice tempNotice = new Notice(
                                jsonObject.getString("productDescription" ),                    //Name
                                10,                                                              //Day
                                jsonObject.getString("productDescription" ),                    //Note
                                jsonObject.getInt("categoryId"),                                //Catagory
                                owner,                                                                  //Owner
                                100,                                                              //g
                                null                                                          //Location
                        );

                        System.out.println( jsonObject.getString("productDescription" ) );

                        noticeArrayList.add( tempNotice );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "error JSON ARRAY" , error.toString() ) ;

            }
        });


        instance.addToRequestQueue(jsonArrayRequest);



        return noticeArrayList;
    }

}