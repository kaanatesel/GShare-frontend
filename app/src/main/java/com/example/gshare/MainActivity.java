package com.example.gshare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button buttonLogin;
    Button buttonRegister;
    EditText userName;
    EditText password;
    TextView err;
    OkHttpClient httpClient;
    String passDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        httpClient = new OkHttpClient();

        buttonLogin = (Button) findViewById(R.id.regloginButton);
        buttonRegister = (Button) findViewById(R.id.regregisterButton);
        userName = (EditText) findViewById(R.id.loginUserName);
        password = (EditText) findViewById(R.id.loginPassword);
        err = (TextView) findViewById(R.id.err);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginOnClick();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });

    }

    public void loginOnClick() {
        String url = "http://35.242.192.20/member/getByEmail/" +userName.getText().toString() ;
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
                }
                else{
                    openHomePage(passDB.equals(uPass));
                }
            }
        });
    }

    public void openRegister(){
        Intent intent = new Intent( this, RegisterActivity.class);
        startActivity(intent);
    }

    public void openHomePage( boolean check ){

        if(!check)
        {
            err.setText("Login Err");
        }
        else{
            String u_name = userName.getText().toString();
            String p_word = password.getText().toString();
            Intent intent = new Intent( this, HomePageActivity.class);
            intent.putExtra( "USERNAME" , u_name );
            intent.putExtra("PASSWORD", p_word );
            startActivity(intent);
        }

    }


}

