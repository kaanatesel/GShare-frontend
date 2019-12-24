package com.example.gshare;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Chat.ChatsFragment;

public class ChatActivity extends AppCompatActivity {
    String userName;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        userName = getIntent().getStringExtra("USERNAME");
        password = getIntent().getStringExtra("PASSWORD");

        ChatsFragment chatsFragment = new ChatsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userName",userName);
        bundle.putString("password",password);
        chatsFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.chat_placeholder,chatsFragment);
        fragmentTransaction.commit();
    }
}
