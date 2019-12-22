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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonLogin;
    Button buttonRegister;
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin = (Button) findViewById(R.id.regloginButton);
        buttonRegister = (Button) findViewById(R.id.regregisterButton);
        userName = (EditText) findViewById(R.id.loginUserName);
        password = (EditText) findViewById(R.id.loginPassword);

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }



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
                }
                else {
                    Toast.makeText(this, "Wrong password or username", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.regregisterButton:
                openRegister();
                break;
        }
    }

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

