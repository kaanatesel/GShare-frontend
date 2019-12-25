package com.example.gshare;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Chat.ChatFragment;


import static com.example.gshare.R.id.linearLayoutForChatsFragment;

public class ChatActivity extends AppCompatActivity {
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        email = getIntent().getStringExtra("email");

        ChatFragment chatsFragment = new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putString("email",email);
        chatsFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.linearLayoutForChatsFragment,chatsFragment);
        fragmentTransaction.commit();
    }
}
