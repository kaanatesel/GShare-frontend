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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ChatAdapter;
import com.example.gshare.HomePageFragment;
import com.example.gshare.ModelClasses.ChatModel.Chat;
import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;
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
    private String email;
    @Nullable
//We will change ChatTry to Chat
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chat, container,false);
        if(getArguments() != null) {
            email = getArguments().getString("email");
        }
        final User userTry2 = new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 );
        final User userTry = new User("OnurKorkmaz", "qwerty", "123456", "qwerty", 6);
        Notice chatNotice = new Notice("bad",5,"dasdfa",0,userTry,
                100,new LocationG());//DBHelper.getNotice(noticeId);
        chats = new ArrayList<Chat>();
        chats.add( new Chat(chatNotice,chatNotice.getNoticeOwner(),userTry2) );
        chats.add( new Chat(chatNotice,chatNotice.getNoticeOwner(),userTry2) );
        chats.add( new Chat(chatNotice,chatNotice.getNoticeOwner(),userTry2) );
        chatList = (ListView)view.findViewById(R.id.chatList);
        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Bundle bundle = new Bundle();
                bundle.putString("email",email);
                if(chats.get(position).getStatus()==Chat.NOT_AGREED){
                    getActivity().setContentView(R.layout.fullyblanklayout);
                    ChatNotAgreedFragment fragmentChatNotAgreed = new ChatNotAgreedFragment();
                    fragmentChatNotAgreed.setArguments(bundle);
                    FragmentTransaction fragmentManagerForNotAgreedChat = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentManagerForNotAgreedChat.replace(R.id.main_biglayout, fragmentChatNotAgreed);
                    fragmentManagerForNotAgreedChat.commit();

                }
                if(chats.get(position).getStatus()==Chat.AGREED){
                    getActivity().setContentView(R.layout.fullyblanklayout);
                    ChatAgreedFragment chatAgreedFragment = new ChatAgreedFragment();
                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    chatAgreedFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.main_biglayout,chatAgreedFragment);
                    fragmentTransaction.commit();
                }
                if(chats.get(position).getStatus()==Chat.RETURNED){
                    getActivity().setContentView(R.layout.fullyblanklayout);
                    ChatReturnedFragment chatReturnedFragment = new ChatReturnedFragment();
                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    chatReturnedFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.main_biglayout,chatReturnedFragment);
                    fragmentTransaction.commit();
                }
                if(chats.get(position).getStatus()==Chat.WAITING_FOR_RETURN){
                    getActivity().setContentView(R.layout.fullyblanklayout);
                    ChatDoneFragment chatDoneFragment = new ChatDoneFragment();
                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    chatDoneFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.main_biglayout,chatDoneFragment);
                    fragmentTransaction.commit();
                }
            }

        });

        chatUsersAdapter = new UserChatsAdapter(getContext(),chats,email);
        chatList.setAdapter(chatUsersAdapter);
        ImageButton homeButton = (ImageButton)view.findViewById(R.id.navigationHome);
        ImageButton mapButton = (ImageButton)view.findViewById(R.id.navigationMap);
        ImageButton myNoticesButton = (ImageButton)view.findViewById(R.id.navigationMyNotices);
        ImageButton chatButton = (ImageButton)view.findViewById(R.id.navigationChat);
        ImageButton profileButton = (ImageButton)view.findViewById(R.id.navigationProfile);

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
        Bundle bundle = new Bundle();
        bundle.putString("email",email);
        switch(view.getId()){
            case R.id.navigationHome:
                HomePageFragment fragment1 = new HomePageFragment();
                fragment1.setArguments(bundle);
                FragmentTransaction fragmentTransaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.main_layout, fragment1);
                fragmentTransaction1.commit();
                break;

            case R.id.navigationMap:
                HomePageFragment fragment2 = new HomePageFragment();
                fragment2.setArguments(bundle);
                FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.main_layout, fragment2);
                fragmentTransaction2.commit();
                break;

            case R.id.navigationMyNotices:
                MyNoticesFragment fragment3 = new MyNoticesFragment();
                fragment3.setArguments(bundle);
                FragmentTransaction fragmentTransaction3 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction3.replace(R.id.main_layout, fragment3);
                fragmentTransaction3.commit();
                break;
            case R.id.navigationChat:
                ChatFragment fragment4 = new ChatFragment();
                fragment4.setArguments(bundle);
                FragmentTransaction fragmentTransaction4 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction4.replace(R.id.main_layout, fragment4);
                fragmentTransaction4.commit();
                break;

            case R.id.navigationProfile:
                ProfileFragment fragment5 = new ProfileFragment();
                fragment5.setArguments(bundle);
                FragmentTransaction fragmentTransaction5 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction5.replace(R.id.main_layout, fragment5);
                fragmentTransaction5.commit();
                break;
        }}

}