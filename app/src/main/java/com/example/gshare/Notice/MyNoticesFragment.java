package com.example.gshare.Notice;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Chat.ChatFragment;
import com.example.gshare.DBHelper;
import com.example.gshare.HomePageFragment;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.Profile.ProfileFragment;
import com.example.gshare.R;



import java.util.ArrayList;

public class MyNoticesFragment extends Fragment implements View.OnClickListener {


    Context c;


    Button lendingMode;
    Button borrowingMode;
    TextView gView;

    ArrayList<Notice> notices;

    ListView listView;
    String userName;
    String password;
    MyNoticesAdapter adapter;
    Bundle bundle;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_notices, container, false);




        bundle = getArguments();
        if(bundle != null) {
            userName = bundle.getString("userName");
            password = bundle.getString("password");
        }

        listView = (ListView) view.findViewById(R.id.listmynotices);

        lendingMode = view.findViewById(R.id.lendingButton);
        borrowingMode = view.findViewById(R.id.borrowingButton);
        gView = view.findViewById(R.id.moneyTextView);
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

        gView.setText(DBHelper.getUser(userName).getG() + "");

        notices = DBHelper.getLendingNotices();
        adapter = new MyNoticesAdapter(c, notices);
        listView.setAdapter(adapter);

        gView.setText( DBHelper.getUser(userName).getG() + "" );

        notices = DBHelper.getLendingNotices();



        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.lendingButton:
                notices = getUserNotices(DBHelper.getUser(userName), 'L');
                break;
            case R.id.borrowingButton:
                notices = getUserNotices(DBHelper.getUser(userName), 'B');

        switch (v.getId()){
            case R.id.lendingButton:
                notices = getUserNotices(DBHelper.getUser(userName) , 'L');
                break;
            case R.id.borrowingButton:
                notices = getUserNotices(DBHelper.getUser(userName),'B');

                break;
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



        }

    }}




    public static ArrayList<Notice> getUserNotices(User user , char type){
        ArrayList<Notice> userList = new ArrayList<Notice>();
        ArrayList<Notice> allNotice = new ArrayList<>();
        if(type == 'L') {
            allNotice = DBHelper.getLendingNotices();
        }
        if( type == 'B'){
            //allNotice = DBHelper.getBorrowingNotices();
        }

        for( int i = 0; i< allNotice.size(); i++){
            if(allNotice.get(i).getNoticeOwner().equals(user)){

                userList.add(allNotice.get(i));
            }
        }
        return userList;



    }
    @Override
    public void onAttach (Context con){
        super.onAttach(con);
        c = con;


    }
}