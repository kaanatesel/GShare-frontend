/*package com.example.gshare;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

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

import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DBHelper {
    private static DBHelper instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context ctx;


    //******************
    private static User primaryUser; //This is the client User OBJ
    private static User tempUser;    //This is for temp user objects. Helps callUserByID

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

    public static User getUser() {

        String url = "http://www.mocky.io/v2/5dfff58e2f00004b0013b24d";


        //Create a JSON OBJ request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            createPrimaryUser(
                                    response.getString("id"),
                                    response.getString("email"),
                                    response.getString("password"),
                                    response.getString("name"),
                                    response.getString("createDate")
                            );
                        } catch (JSONException e) {

                            Log.e("exception: ", "DBHelper JSON Exception");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e( "error" , error.toString() ) ;

                    }
                });

        // Access the RequestQueue through your singleton class.
        instance.addToRequestQueue(jsonObjectRequest);

        return primaryUser;
    }

    public static User callUserByID(int ID){

        String url = "http://35.246.134.158/member/" + ID + "/";


        //Create a JSON OBJ request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            tempUser = createUser(response.getString("id"),
                                    response.getString("email"),
                                    response.getString("password"),
                                    response.getString("name"),
                                    response.getString("createDate")
                            );
                        } catch (JSONException e) {

                            Log.e("exception: ", "DBHelper JSON Exception");
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e( "error" , error.toString() ) ;

                    }
                });

        return  tempUser;
    }

//  public static void register(String email, String name, String password)

//  public static void validateLogin()


    //Helper Methods for Users

    private static void createPrimaryUser(String id, String email, String password, String name, String createDate) {
        primaryUser =  createUser(id,name,password,email,createDate);

    }

    private static User createUser(String id, String name , String password, String email, String createDate){
        return new User(name,"userNameTODO",password,email,100); //TODO
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////GET NOTICE DATA////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static ArrayList<Notice> getLendingNotices(){

        final ArrayList<Notice> list = new ArrayList<Notice>();
        String url = "http://35.246.134.158/demand/findAll/";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url ,null,  new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0 ; i< response.length() ; i++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Notice tempNotice = new Notice(
                                jsonObject.getString("name" ),
                                jsonObject.getInt("day " ),
                                jsonObject.getString("note"),
                                jsonObject.getInt("catagory "),
                                callUserByID( jsonObject.getInt("id") ),
                                500,                                            //TODO replace with response
                                null );


                        list.add( tempNotice );
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

        return list;
    }

    public static ArrayList<Notice> getBorrowingNotices(){

        final ArrayList<Notice> list = new ArrayList<Notice>();
        String url = "http://35.246.134.158/demand/findAll/"; //TODO replace the url


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url ,null,  new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0 ; i< response.length() ; i++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Notice tempNotice = new Notice(
                                jsonObject.getString("name" ),
                                jsonObject.getInt("day " ),
                                jsonObject.getString("note"),
                                jsonObject.getInt("catagory "),
                                callUserByID( jsonObject.getInt("id") ),
                                null );


                        list.add( tempNotice );
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

        return list;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////CREATE NOTICE IN DB////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void createLendingNotice(Notice notice) {

        String tag_json_obj = "json_obj_req";

        String postURL = "";

        int requesterId ; //TODO notice objesinden parametreleri al ve bunları gir. Ayrıca registiration methodundan ID al buraya gir.
        String productDescription;
        int categoryId;


        //TODO write json by hand
//        final HashMap<String, String> postParams = new HashMap<String, String>();
//            postParams.put("requesterId", value1);
//            postParams.put("productDescription", value2);
//            postParams.put("categoryId", value3);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                postURL, new JSONObject(postParams),
                new com.android.volley.Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        instance.addToRequestQueue(jsonObjReq);
    }//Lending notice end








}//DBHelper end

// POST isteği gönderildikten sonra response alıyor JSON id name email password createDATE

// validateLogin() PU ın id sini kaydetsin

////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////Sending Data///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////

//Template for JSON file upload



//}*/