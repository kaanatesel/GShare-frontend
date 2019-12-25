package com.example.gshare;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LogPrinter;
import android.util.LruCache;
import android.widget.EditText;
import android.widget.TextView;

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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.*;

import static com.android.volley.Request.Method.HEAD;

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


    //******************
    private static User primaryUser; //This is the client User OBJ
    private static User tempUser = null;
    private static int primaryUserID;
    private static int tempUserID;

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

    /**
     * Works partially. Returns null in the first time. Returns obj in second time.
     *
     * @param email The users email
     * @return User Object
     */
    public static User getUser(String email) { //void for now
        instance.callUserByEmail(email);
        return tempUser;
    }

    private void callUserByEmail(String email) {
        RequestQueue mQueue = Volley.newRequestQueue(ctx);

        String url = "http://35.246.134.158/member/getMyEmail/" + email + "/";

        User tempUser;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            DBHelper.createUserOBJ(
                                    response.getInt("id"),
                                    response.getString("name"),
                                    response.getString("password"),
                                    response.getString("email"),
                                    response.getInt("g"));

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
    }

    /**
     * Dikkat ilk seferinde null dönüyor 2. seferinde doluyor. Çözemedim
     *
     * @param ID user ID
     * @return The User
     */
    public static User getUser(int ID) {
        instance.callUserByID(ID);
        return tempUser;
    }

    private void callUserByID(int ID) {

        RequestQueue mQueue = Volley.newRequestQueue(ctx);

        String url = "http://35.242.192.20/member/" + ID + "/";

        User tempUser;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            DBHelper.createUserOBJ(
                                    response.getInt("id"),
                                    response.getString("name"),
                                    response.getString("password"),
                                    response.getString("email"),
                                    response.getInt("g"));

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
    public static ArrayList<Message> getMessages(int borrowingNoticeID) {

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


                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jresponse = response.getJSONObject(i);

                                String message = jresponse.getString("message");
                                int receiverMemberId = jresponse.getInt("receiverMemberId");
                                int senderMemberId = jresponse.getInt("senderMemberId");
                                Message msg = new Message(message, getUser(receiverMemberId), getUser(senderMemberId));
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


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////CREATE NOTICE IN DB////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static void createLendingNotice(Notice notice) {

        String postURL = "http://35.242.192.20/demand/create/";

        int requesterId = primaryUserID;
        String productDescription = notice.getName();
        int categoryId = notice.getCategory();


        Gson gson = new Gson();
        gson.toJson(categoryId);
        gson.toJson(productDescription);
        gson.toJson(requesterId);
        String postParams = gson.toString();
        //TODO write json by hand

        JsonObjectRequest jsonObjReq = null;
        try {
            jsonObjReq = new JsonObjectRequest(
                    Request.Method.POST,
                    postURL, new JSONObject(postParams),

        }


    }
},


        new Response.ErrorListener()
        {
@Override
public void onErrorResponse(VolleyError error){

        }
        }
        );
        }catch(JSONException e){
        e.printStackTrace();
        }

        instance.addToRequestQueue(jsonObjReq);
        }//Lending notice end

public static void createBorrowingNotice(Notice notice){

        String postURL="http://35.242.192.20/product/create/";

        String description=notice.getNote();
        int memberId=primaryUserID;
        String name=notice.getName();
        int price=notice.getG();
        int productCategory=notice.getCategory();
""
        Gson gson=new Gson();
        gson.toJson(description);
        gson.toJson(memberId);
        gson.toJson(name);
        gson.toJson(price);
        gson.toJson(productCategory);
        String postParams=gson.toString();

        JsonObjectRequest jsonObjReq=null;
        try{
        jsonObjReq=new JsonObjectRequest(
        Request.Method.POST,
        postURL,new JSONObject(postParams),


        new Response.Listener<JSONObject>()
        {
@Override
public void onResponse(JSONObject response){

        }
        },


        new Response.ErrorListener()
        {
@Override
public void onErrorResponse(VolleyError error){

        }
        }
        );
        }catch(JSONException e){
        e.printStackTrace();
        }

        instance.addToRequestQueue(jsonObjReq);
        }//Borrowing notice end

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
        >>>>>>>24c7a7ef5edcb816b251e6873821c4bf6da5ada3

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
