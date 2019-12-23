package com.example.gshare;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

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


public class DBHelper {
    private static DBHelper instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context ctx;


    //******************
    private static User primaryUser;

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
                            createUser(response.getString("id"),
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

    //Helper Methods

    private static void createPrimaryUser(String id, String email, String password, String name, String createDate) {
        primaryUser =  createUser(id,name,password,email,createDate);

    }

    private static User createUser(String id, String name , String password, String email, String createDate){
        return new User(name,"userNameTODO",password,email,100);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////GET NOTICE DATA////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static ArrayList<Notice> getNotices(){

        final ArrayList<Notice> list = new ArrayList<Notice>();
        String url = "http://www.mocky.io/v2/5dfff58e2f00004b0013b24d";


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
                            callUserByID(jsonObject.getInt()), //TODO implement here
                            null );



                } catch (JSONException e) {
                    e.printStackTrace();
                }






                list.add( tempNotice );
            }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( "error JSON ARRAY" , error.toString() ) ;

            }
        });

         );



        instance.addToRequestQueue(jsonArrayRequest);

        return list;
    }

    }//DBHelper end



////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////Sending Data///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////

//Template for JSON file upload

//  final HashMap<Key,Value>{

//}

//    private void postUsingVolley() {
//        String tag_json_obj = "json_obj_req";
//
//        String postURL = "";
//
//        final HashMap<String, String> postParams = new HashMap<String, String>();
//        sendFeedbackParams.put("key1", value1);
//        sendFeedbackParams.put("key2", value2);
//        sendFeedbackParams.put("key3", value3);
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                postURL, new JSONObject(postParams),
//                new com.android.volley.Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        //Log.d("TAG", response.toString());
//                        try {
//                            //Toast.makeText(mContext, response.getString("message"), Toast.LENGTH_LONG).show();
//                            Toast.makeText(mContext, "Thank you for your post", Toast.LENGTH_LONG).show();
//
//                            if (response.getBoolean("status")) {
//                                pDialog.dismiss();
//                                finish();
//                            }
//                        } catch (JSONException e) {
//                            Log.e("TAG", e.toString());
//                        }
//                        pDialog.dismiss();
//                    }
//                }, new com.android.volley.Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //VolleyLog.d("TAG", "Error: " + error.getMessage());
//                pDialog.dismiss();
//                if (isNetworkProblem(error)) {
//                    Toast.makeText(mContext, "Internet Problem", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        }) {
//
//            @Override
//            public String getBodyContentType() {
//                return "application/json; charset=utf-8";
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                return getRequestHeaders();
//            }
//        };
//
//
//    }
//}