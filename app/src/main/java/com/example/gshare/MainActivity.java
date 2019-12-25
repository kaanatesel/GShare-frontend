package com.example.gshare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;

<<<<<<< Updated upstream
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.gshare.ModelClasses.User.User;

import org.json.JSONArray;
=======
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;

>>>>>>> Stashed changes
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;




public class MainActivity extends AppCompatActivity {


    Button buttonLogin;
    Button buttonRegister;
    EditText userName;
    EditText password;


    public static TextView err;
    OkHttpClient httpClient;
    String passDB;


    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        httpClient = new OkHttpClient();
      //  DBHelper.getInstance(this);


        buttonLogin = (Button) findViewById(R.id.regloginButton);
        buttonRegister = (Button) findViewById(R.id.regregisterButton);
        userName = (EditText) findViewById(R.id.loginUserName);
        password = (EditText) findViewById(R.id.loginPassword);


        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);

        mQueue = Volley.newRequestQueue(this);


    }

<<<<<<< Updated upstream



    public boolean tryLogin(String userName, String password) {//FIX THIS AT FINAL PRODUCT
        //if( DBHelper.getUser( userName , password) == null ){
            //return false;
        //}
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regloginButton:
                boolean result = tryLogin(userName.getText().toString(), password.getText().toString());
;
                if (result) {
                    openHomePage();
=======
    public void loginOnClick() {




        try {
            User user = CallUserByEmail.call("bora@gmail.com");

            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            System.out.println(user.getUserName());
            System.out.println(user.getID());
            System.out.println(user.getG());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        System.out.println( DBHelper.getUser(21) );
//        System.out.println( DBHelper.getUser("bora@gmail.com") );

//        ArrayList<Notice> noticeArrayList =  DBHelper.getLendingNotices();
//
//        for(int i = 0 ; i < noticeArrayList.size();i++ ){
//            System.out.println("notice array list size: " +  noticeArrayList.size() );
//            System.out.println( i + " " + noticeArrayList.get(i).getNote() );
//            System.out.println( i + " " + noticeArrayList.get(i).getCategory() );
//            System.out.println("*************************************");
//        }


        String url = "http://35.242.192.20/member/getByEmail/" + userName.getText().toString() ;
        final String uPass = password.getText().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mMessage = response.body().string();
                try {
                    JSONObject reader = new JSONObject(mMessage);
                    passDB = reader.getString("password");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(passDB == null)
                {
                    err.setText("Login Err");
>>>>>>> Stashed changes
                }
                else {
                    Toast.makeText(this, "Wrong password or username", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.regregisterButton:
                openRegister();
                break;
        }

    public void loginOnClick() {

        DBHelper.getInstance(this);
//        User temp = DBHelper.getUser(21);
//        if (temp == null) {
//                System.out.println("null");
//        } else {
//                System.out.println(temp.getEmail());
//                System.out.println(temp.getID());
//                System.out.println(temp.getPassword());
//                System.out.println(temp.getUserName());
//        }
//
//        temp = DBHelper.getUser("bora@gmail.com");
//
//        if (temp == null) {
//            System.out.println("null");
//        } else {
//            System.out.println(temp.getEmail());
//            System.out.println(temp.getID());
//            System.out.println(temp.getPassword());
//            System.out.println(temp.getUserName());
//        }
//    }
        try {
            DBHelper.createUser(new User("asd qwe", "username", "n-word", "asdfg@bilkent.edu.tr", 200));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//        String mEmail = DBHelper.getInstance(this).callUserByID(21).getEmail();
//
//        String url = "http://35.242.192.20/member/getByEmail/" +userName.getText().toString() ;
//        final String uPass = password.getText().toString();
//
//        Request request = new Request.Builder()
//                .url(url)
//                .header("Accept", "application/json")
//                .header("Content-Type", "application/json")
//                .build();
//
//        httpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                String mMessage = e.getMessage().toString();
//                Log.w("failure Response", mMessage);
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String mMessage = response.body().string();
//                try {
//                    JSONObject reader = new JSONObject(mMessage);
//                    passDB = reader.getString("password");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                if(passDB == null)
//                {
//                    err.setText("Login Err");
//                }
//                else{
//                    openHomePage(passDB.equals(uPass));
//                }
//
//            }
//        });
 //   }

    public void openRegister(){
        Intent intent = new Intent( this, RegisterActivity.class);
        startActivity(intent);
    }

    public void openHomePage(){
        String u_name = userName.getText().toString();
        String p_word = password.getText().toString();
        Intent intent = new Intent( this, HomePageActivity.class);
        intent.putExtra( "USERNAME" , u_name );
        intent.putExtra("PASSWORD", p_word );
        startActivity(intent);
    }


}

