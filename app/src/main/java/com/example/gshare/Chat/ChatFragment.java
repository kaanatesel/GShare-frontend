package com.example.gshare.Chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ChatAdapter;
import com.example.gshare.HomePageFragment;
import com.example.gshare.ModelClasses.ChatModel.Chat;
import com.example.gshare.Notice.MyNoticesFragment;
import com.example.gshare.Profile.ProfileFragment;
import com.example.gshare.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ChatFragment extends Fragment implements View.OnClickListener {
private Context c;
private ListView chatList;
private UserChatsAdapter chatUsersAdapter;
private ArrayList<Chat> chats;

private Bundle bundle;
private String email;
    @Nullable
//We will change ChatTry to Chat
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chat, container,false);
        email = getArguments().getString("email");
        chatList = (ListView)view.findViewById(R.id.chatList);
        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

            }

        });
        chats = new ArrayList<Chat>();
        //chatUsersAdapter = new UserChatsAdapter();
        //chatList.setAdapter(chatUsersAdapter);
        ImageButton homeButton = (ImageButton)getView().findViewById(R.id.navigation_home);
        ImageButton mapButton = (ImageButton)getView().findViewById(R.id.navigation_Map);
        ImageButton myNoticesButton = (ImageButton)getView().findViewById(R.id.navigation_Notices);
        ImageButton chatButton = (ImageButton)getView().findViewById(R.id.navigation_Chat);
        ImageButton profileButton = (ImageButton)getView().findViewById(R.id.navigation_Profilet);
        homeButton.setOnClickListener(this);
        mapButton.setOnClickListener(this);
        myNoticesButton.setOnClickListener(this);
        chatButton.setOnClickListener(this);
        profileButton.setOnClickListener(this);
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


    @Override
    public void onClick(View view) {
        switch(view.getId()){
        case R.id.navigation_home:
        HomePageFragment fragment1 = new HomePageFragment();
        fragment1.setArguments(bundle);
        FragmentTransaction fragmentTransaction1 = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.main_layout, fragment1);
        fragmentTransaction1.commit();
        break;

        case R.id.navigation_Map:
        HomePageFragment fragment2 = new HomePageFragment();
        fragment2.setArguments(bundle);
        FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.main_layout, fragment2);
        fragmentTransaction2.commit();
        break;

        case R.id.navigation_Notices:
        MyNoticesFragment fragment3 = new MyNoticesFragment();
        fragment3.setArguments(bundle);
        FragmentTransaction fragmentTransaction3 = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction3.replace(R.id.main_layout, fragment3);
        fragmentTransaction3.commit();
        break;
        case R.id.navigation_Chat:
        ChatFragment fragment4 = new ChatFragment();
        fragment4.setArguments(bundle);
        FragmentTransaction fragmentTransaction4 = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction4.replace(R.id.main_layout, fragment4);
        fragmentTransaction4.commit();
        break;

        case R.id.navigation_Profilet:
        ProfileFragment fragment5 = new ProfileFragment();
        fragment5.setArguments(bundle);
        FragmentTransaction fragmentTransaction5 = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction5.replace(R.id.main_layout, fragment5);
        fragmentTransaction5.commit();
        break;
    }}

}
