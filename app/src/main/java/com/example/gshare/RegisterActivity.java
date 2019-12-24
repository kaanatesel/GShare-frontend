package com.example.gshare;

import com.example.gshare.ModelClasses.User.User;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonRegister;
    Button buttonLogin;
    EditText userName;
    EditText password;
    EditText email;
    TextView registerMessage;
    OkHttpClient httpClient;
    String err;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        httpClient = new OkHttpClient();

        buttonRegister = findViewById(R.id.regregisterButton);
        buttonLogin = findViewById(R.id.regloginButton);
        userName = findViewById(R.id.registerUsername);
        registerMessage = findViewById(R.id.registerMessage);
        password = findViewById(R.id.registerPassword);
        email = findViewById(R.id.registerEmail);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });
    }

    public void register(){
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        //String url = "http://10.0.2.2:8090/member/create/";
        String url = "http://35.242.192.20/member/create/";
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", userName.getText().toString())
                .addFormDataPart("email", email.getText().toString())
                .addFormDataPart("password", password.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
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
                    err = reader.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(err != null)
                {
                    registerMessage.setText("Your acount is created, You can login now.");
                }
                else
                {
                    registerMessage.setText("Somethinl went wrong");
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        /*switch(v.getId()){
            case R.id.regregisterButton:
                if( userName.getText().toString().length() != 0 && password.getText().toString().length()
                        != 0 && email.getText().toString().length() != 0 ){
                    if(true){

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
        }*/
    }

    public void openLogin(){
        Intent intent = new Intent( this , MainActivity.class );
        startActivity(intent);
    }
}
