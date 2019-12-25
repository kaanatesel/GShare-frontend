package com.example.gshare.Chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ChatAdapter;
import com.example.gshare.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ChatFragment extends Fragment {
private Context c;
private ListView chatList;
private ChatAdapter chatAdapter;
private ArrayList<ChatTry> chats;
    @Nullable
//We will change ChatTry to Chat
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @SuppressLint("ResourceType") View view = inflater.inflate(R.layout.activity_chat, container,false);
        chatList = (ListView)view.findViewById(R.id.chatList);

        chats = new ArrayList<ChatTry>();
        chats.add(new ChatTry("message 1",false));
        onAttach(c);
        chatAdapter = new ChatAdapter(c,chats);
        chatList.setAdapter(chatAdapter);

    return view;
    }
    @Override
    public void onAttach(Context con){
        super.onAttach(con);
        c = con;
    }

    public void updateFragment() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Bundle bundle = getArguments();
                ChatFragment chatFragment = new ChatFragment();
                chatFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_biglayout,chatFragment);
            }
        }, 0, 10000);
    }


}
