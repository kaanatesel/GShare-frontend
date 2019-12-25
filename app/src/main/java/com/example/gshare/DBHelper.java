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
import okhttp3.*;
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



<<<<<<< Updated upstream
    //******************
    private static User primaryUser; //This is the client User OBJ
    public static User tempUser = null;
    private static int primaryUserID;
    private static int tempUserID;
=======
    private static ArrayList<User> userList = new ArrayList<User>();
>>>>>>> Stashed changes

    private DBHelper(Context context) {
        ctx = context;

    }

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

//    public RequestQueue getRequestQueue() {
//        if (requestQueue == null) {
//            // getApplicationContext() is key, it keeps you from leaking the
//            // Activity or BroadcastReceiver if someone passes one in.
//            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
//        }
//        return requestQueue;
//    }
//
////    public <T> void addToRequestQueue(Request<T> req) {
////        getRequestQueue().add(req);
////    }
//
//    public ImageLoader getImageLoader() {
//        return imageLoader;
//    }

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


        String url = "http://35.242.192.20/member/getMyEmail/" + email + "/";

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
                    userList = new ArrayList<User>();
                    userList.add( p1);

                } catch (JSONException e) { e.printStackTrace(); }

            }

        });
        try {
            return userList.get( userList.size()-1 );
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

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://35.242.192.20/member/21/")
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

        okhttp3.Response response = null;


        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }


<<<<<<< Updated upstream
    public static void getMessages(int borrowingNoticeID){

        String url = "";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("employees");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                String firstName = employee.getString("firstname");
                                int age = employee.getInt("age");
                                String mail = employee.getString("mail");

                                mTextViewResult.append(firstName + ", " + String.valueOf(age) + ", " + mail + "\n\n");
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
=======
        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
>>>>>>> Stashed changes


<<<<<<< Updated upstream
=======
        try {
            return userList.get( userList.size()-1 );
        }catch (java.lang.IndexOutOfBoundsException e){
            return null;
        }
>>>>>>> Stashed changes
    }


//    @Deprecated
//    /**Gets all messages that belong to a notice
//     * Potantial bugs!!! For every message a seperate user object is created in  getUser(receiverMemberId) , getUser( senderMemberId)
//     * WARNING!!! NOT TESTED
//     * @param borrowingNoticeID
//     * @return messages Messages ArrayList
//     * */
//    public static ArrayList<Message> getMessages(int borrowingNoticeID){
//
//        String url = "http:/35.242.192.20/message/findByProductRequestId/" + borrowingNoticeID;
//
//        RequestQueue mQueue = Volley.newRequestQueue(ctx);
//        final ArrayList<Message> messages = new ArrayList<Message>();
//
//        JsonArrayRequest request = new JsonArrayRequest(
//                Request.Method.GET,
//                url,
//                null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//
//
//                            for(int i = 0; i < response.length(); i++){
//                                JSONObject jresponse = response.getJSONObject(i);
//
//                                String message  = jresponse.getString("message");
//                                int receiverMemberId = jresponse.getInt("receiverMemberId");
//                                int senderMemberId = jresponse.getInt("senderMemberId");
//                                Message msg = new Message( message, getUser(receiverMemberId) , getUser( senderMemberId) );
//                                messages.add(msg);
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//
//        mQueue.add(request);
//
//        return messages;
//    }
//
//    private static  ArrayList<Notice> noticeArrayList = new ArrayList<Notice>();
//
//    public static ArrayList<Notice> getLendingNotices(){
//
//        String url = "http://35.242.192.20/demand/findAll/";
//
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url ,null,  new Response.Listener<JSONArray>(){
//
//            @Override
//            public void onResponse(JSONArray response) {
//
//                System.out.println(response.length());
//                for(int i = 0 ; i< response.length() ; i++){
//
//                    try {
//                        JSONObject jsonObject = response.getJSONObject(i);
//
//                        System.out.println( jsonObject.getString("productDescription" ) );
//                        System.out.println( jsonObject.getInt("categoryId" ) );
//
//
//
//
//                        User owner = getUser( jsonObject.getInt("requesterId") );
//
//                        Notice tempNotice = new Notice(
//                                jsonObject.getString("productDescription" ),                    //Name
//                                10,                                                              //Day
//                                jsonObject.getString("productDescription" ),                    //Note
//                                jsonObject.getInt("categoryId"),                                //Catagory
//                                owner,                                                                  //Owner
//                                100,                                                              //g
//                                null                                                          //Location
//                        );
//
//                        System.out.println( jsonObject.getString("productDescription" ) );
//
//                        noticeArrayList.add( tempNotice );
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e( "error JSON ARRAY" , error.toString() ) ;
//
//            }
//        });
//
//
//        instance.addToRequestQueue(jsonArrayRequest);
//
//
//
//        return noticeArrayList;
//    }

}

////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////GET NOTICE DATA////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////
//    public static ArrayList<Notice> getLendingNotices(){
//
//        final ArrayList<Notice> list = new ArrayList<Notice>();
//        String url = "http://35.246.134.158/demand/findAll/";
//
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url ,null,  new Response.Listener<JSONArray>(){
//
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for(int i = 0 ; i< response.length() ; i++){
//
//                    try {
//                        JSONObject jsonObject = response.getJSONObject(i);
//                        Notice tempNotice = new Notice(
//                                jsonObject.getString("name" ),
//                                jsonObject.getInt("day " ),
//                                jsonObject.getString("note"),
//                                jsonObject.getInt("catagory "),
//                                instance.callUserByID( jsonObject.getInt("id") ),
//                                500,                                            //TODO replace with response
//                                null );
//
//
//                        list.add( tempNotice );
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e( "error JSON ARRAY" , error.toString() ) ;
//
//            }
//        });
//
//
//        instance.addToRequestQueue(jsonArrayRequest);
//
//        return list;
//    }
//
//    public static ArrayList<Notice> getBorrowingNotices(){
//
//        final ArrayList<Notice> list = new ArrayList<Notice>();
//        String url = "http://35.246.134.158/demand/getAll/"; //TODO replace the url
//
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url ,null,  new Response.Listener<JSONArray>(){
//
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for(int i = 0 ; i< response.length() ; i++){
//
//                    try {
//                        JSONObject jsonObject = response.getJSONObject(i);
//                        Notice tempNotice = new Notice(
//                                jsonObject.getString("name" ),
//                                jsonObject.getInt("day " ),
//                                jsonObject.getString("note"),
//                                jsonObject.getInt("catagory "),
//                                instance.callUserByID( jsonObject.getInt("id") ),
//                                null );
//
//
//                        list.add( tempNotice );
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e( "error JSON ARRAY" , error.toString() ) ;
//
//            }
//        });
//
//
//        instance.addToRequestQueue(jsonArrayRequest);
//
//        return list;
//    }
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////
//    /////////////////////////////////CREATE NOTICE IN DB////////////////////////////////////////////
//    ////////////////////////////////////////////////////////////////////////////////////////////////
//
//    public void createLendingNotice(Notice notice) {
//
//        String postURL = "http://35.242.192.20/demand/create/";
//
//        int requesterId = primaryUserID;
//        String productDescription = notice.getName();
//        int categoryId = notice.getCategory();
//
//
//        Gson gson = new Gson();
//        gson.toJson(categoryId);
//        gson.toJson(productDescription);
//        gson.toJson(requesterId);
//        String postParams = gson.toString();
//        //TODO write json by hand
//
//        JsonObjectRequest jsonObjReq = null;
//        try {
//            jsonObjReq = new JsonObjectRequest(
//                    Request.Method.POST,
//                    postURL, new JSONObject(postParams),
//
//
//                    new Response.Listener<JSONObject>()
//                    {
//                        @Override
//                        public void onResponse(JSONObject response) {
//
//                        }
//                    },
//
//
//                    new Response.ErrorListener()
//                    {
//                         @Override
//                          public void onErrorResponse(VolleyError error) {
//
//                     }
//            }
//            );
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        instance.addToRequestQueue(jsonObjReq);
//    }//Lending notice end
//
//    public void createBorrowingNotice(Notice notice) {
//
//        String postURL = "http://35.242.192.20/product/create/";
//
//        String description = notice.getNote();
//        int memberId = primaryUserID;
//        String name = notice.getName();
//        int price = notice.getG();
//        int productCategory = notice.getCategory();
//
//        Gson gson = new Gson();
//        gson.toJson(description);
//        gson.toJson(memberId);
//        gson.toJson(name);
//        gson.toJson(price);
//        gson.toJson(productCategory);
//        String postParams = gson.toString();
//
//        JsonObjectRequest jsonObjReq = null;
//        try {
//            jsonObjReq = new JsonObjectRequest(
//                    Request.Method.POST,
//                    postURL, new JSONObject(postParams),
//
//
//                    new Response.Listener<JSONObject>()
//                    {
//                        @Override
//                        public void onResponse(JSONObject response) {
//
//                        }
//                    },
//
//
//                    new Response.ErrorListener()
//                    {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    }
//            );
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        instance.addToRequestQueue(jsonObjReq);
//}//Borrowing notice end

////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////GET CHAT COLLECTION////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////

//    public static Chat getChat(int otherUserID){
//
//    }
//
//    public static ArrayList<Message> getChatMessages(int msgSenderId , int msgRecieverId ){
//
//    }
//
//    public static void submitMessage(Message message, Notice notice){
//
//        String postURL = "http://35.242.192.20/demand/create/";
//
//
//        String msg = message.getMessage();
//        callUserByEmail( message.getReciever().getEmail() ); //After that tempUserID will set to receiver ID
//        int recieverID = tempUserID;
//        int senderID = primaryUserID;
//
//
//
//        Gson gson = new Gson();
//
//        gson.toJson(productDescription);
//        gson.toJson(requesterId);
//        String postParams = gson.toString();
//        //TODO write json by hand
//
//        JsonObjectRequest jsonObjReq = null;
//        try {
//            jsonObjReq = new JsonObjectRequest(
//                    Request.Method.POST,
//                    postURL, new JSONObject(postParams),
//
//
//                    new Response.Listener<JSONObject>()
//                    {
//                        @Override
//                        public void onResponse(JSONObject response) {
//
//                        }
//                    },
//
//
//                    new Response.ErrorListener()
//                    {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    }
//            );
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        instance.addToRequestQueue(jsonObjReq);
//    }//Lending notice end


//}

//}//DBHelper end

// POST isteği gönderildikten sonra response alıyor JSON id name email password createDATE

// validateLogin() PU ın id sini kaydetsin

//    public static User callUserByEmail(String email) {

//    String url = "http://35.246.134.158/member/getMyEmail/" + email + "/";


//    //Create a JSON OBJ request
//    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//                @Override
//                public void onResponse(JSONObject response) {
//
//                    try {
//                        createUserOBJ(response.getInt("id"),
//                                response.getString("email"),
//                                response.getString("password"),
//                                response.getString("name"),
//                                response.getInt("g")
//                        );
//                    } catch (JSONException e) {
//
//                        Log.e("exception: ", "DBHelper JSON Exception");
//
//                    }
//
//                }
//            }, new Response.ErrorListener() {
//
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e("error", error.toString());
//
//                }
//            });
//
//        instance.addToRequestQueue(jsonObjectRequest);
//
//                return tempUser;
//                }
//RequestQueue mQueue = Volley.newRequestQueue(ctx);
//
//        String url = "http://35.242.192.20/member/" + ID + "/";
//
//        User tempUser;
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//
//
//                        try {
//                            DBHelper.createUserOBJ(
//                                    response.getInt("id"),
//                                    response.getString("name"),
//                                    response.getString("password"),
//                                    response.getString("email"),
//                                    response.getInt("g") );
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//
//
//        mQueue.add(request);
//

//public void onResponse(Call call, okhttp3.Response response) throws IOException {
//
//        String mMessage = response.body().string();
//        try {
//        JSONArray jsonArray = new JSONArray(mMessage);
//
//        for(int i = 0; i < jsonArray.length();i++)
//        {
//        JSONObject json = jsonArray.getJSONObject(i);
//        User p1 = new User(
//        json.getString("id"),           //Name and Surname
//        json.getString("email"),        //userName
//        json.getString("password"),     //Password
//        json.getString("email"),        //email
//        json.getInt("g")         );     //g
//
//        notices.add(p1);
//        }