package com.example.gshare;

import com.example.gshare.ModelClasses.User.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonRegister;
    Button buttonLogin;
    EditText userName;
    EditText password;
    EditText email;
    EditText nameAndSurname;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonRegister = findViewById(R.id.regregisterButton);
        buttonLogin = findViewById(R.id.regloginButton);
        userName = findViewById(R.id.registerUsername);
        password = findViewById(R.id.registerPassword);
        email = findViewById(R.id.registerEmail);
        nameAndSurname = findViewById(R.id.registerNameSurname);

        buttonRegister.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
        userName.setOnClickListener(this);
        password.setOnClickListener(this);
        email.setOnClickListener(this);
        nameAndSurname.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.regregisterButton:
                if( userName.getText().toString().length() != 0 && password.getText().toString().length()
                        != 0 && email.getText().toString().length() != 0 && nameAndSurname.getText().toString().length() != 0){
                    /*
                    User user = new User( nameAndSurname.getText().toString(), userName.getText().toString(),
                            password.getText().toString(), email.getText().toString(), 0 );

                    boolean added = tryRegister( user );*/

                    if(true){//added) {//FIX THIS AT FINAL PRODUCT

                        openLogin();
                    }
                    else{
                        Toast.makeText( this, "Please try again, inappropriate information", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText( this, "Please try again, inappropriate information", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.regloginButton:
                openLogin();
                break;
        }

    }

    public void openLogin(){
        Intent intent = new Intent( this , MainActivity.class );
        startActivity(intent);
    }

    /*//FIX THIS AT FINAL PRODUCT
    public boolean tryRegister( User user ){
        boolean added = DBHelper.addUser( user );
        return added;
    }
    */

}
