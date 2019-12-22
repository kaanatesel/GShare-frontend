package com.example.gshare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        button1 = findViewById(R.id.regregisterButton);
        button1.setOnClickListener(this);
        //setContentView(R.layout.activity_register);

    }

    @Override
    public void onClick(View v) {
        setContentView(R.layout.activity_register);
    }
}
