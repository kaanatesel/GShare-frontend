package com.example.gshare.Chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ChatAdapter;
import com.example.gshare.ModelClasses.ChatModel.Chat;
import com.example.gshare.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ChatFragment extends Fragment {
private Context c;
private ListView chatList;
private UserChatsAdapter chatUsersAdapter;
private ArrayList<Chat> chats;

private String email;
    @Nullable
//We will change ChatTry to Chat
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chat, container,false);
        email = getArguments().getString("email");
        chatList = (ListView)view.findViewById(R.id.chatList);
        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClickListener(AdapterView<?> parent, View view, int position, long id){

            }

        });
        chats = new ArrayList<Chat>();
        chatUsersAdapter = new UserChatsAdapter();
        chatList.setAdapter(chatUsersAdapter);

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
