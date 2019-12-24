package com.example.gshare;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Notice.MyNoticesFragment;

public class MyNoticesActivity extends AppCompatActivity {

    String userName;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynotices);

        //Wrote for test purposes only
        userName = getIntent().getStringExtra("USERNAME");
        password = getIntent().getStringExtra("PASSWORD");

        MyNoticesFragment myNoticesFragment = new MyNoticesFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userName",userName);
        bundle.putString("password",password);
        myNoticesFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mynotices_placeholder,myNoticesFragment);
        fragmentTransaction.commit();
    }
    
}
