package com.example.gshare.Notice;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import com.example.gshare.CallUserByEmail;
import com.example.gshare.Chat.ChatFragment;
import com.example.gshare.DBHelper;
import com.example.gshare.HomePageFragment;
import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.Profile.ProfileFragment;
import com.example.gshare.R;


import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MyNoticesFragment extends Fragment implements View.OnClickListener {


    Context c;


    Button lendingMode;
    Button borrowingMode;
    TextView gView;

    ArrayList<Notice> notices;

    ListView listView;
    MyNoticesAdapter adapter;
    Bundle bundle;

    String email;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_my_notices, container, false);


        bundle = getArguments();
        if(bundle != null) {
            email = bundle.getString("email");
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

        try {
            gView.setText(CallUserByEmail.call(email).getG() + "");
        }
        catch( Exception e ){
            e.printStackTrace();
        }
        //gView.setText(DBHelper.getUser(userName).getG() + "");
        gView.setText("100");
        //notices = DBHelper.getLendingNotices();
        notices = new ArrayList<Notice>();
        notices.add( new Notice("bad", 5, "dasdfa", 0, new User("Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100),
                100, new LocationG()));//DBHelper.getNotice(noticeId);)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                Bundle bundleNotice = new Bundle();
                if(notices.get(position).getNoticeType() == 'L'){
                   NoticeViewLending fragment = new NoticeViewLending();
                   bundle = getArguments();
                   bundleNotice.putString("NoticeName",notices.get(position).getName());
                   bundleNotice.putString("NoticeDay",notices.get(position).getDay() + "");
                   bundleNotice.putString("NoticeG",notices.get(position).getG() + "");
                   bundleNotice.putString("NoticeNote", notices.get(position).getNote());
                   FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                   fragment.setArguments(bundle);
                   fragment.setArguments(bundleNotice);
                   transaction.replace(R.id.main_layout,fragment);
                   transaction.commit();
               }else{
                    NoticeViewLending fragment = new NoticeViewLending();
                    bundle = getArguments();
                    bundleNotice.putString("NoticeName",notices.get(position).getName());
                    bundleNotice.putString("NoticeDay",notices.get(position).getDay() + "");
                    bundleNotice.putString("NoticeG",notices.get(position).getG() + "");
                    bundleNotice.putString("NoticeNote", notices.get(position).getNote());
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragment.setArguments(bundle);
                    fragment.setArguments(bundleNotice);
                    transaction.replace(R.id.main_layout,fragment);
                    transaction.commit();



                }
            }
        });

        //notices = DBHelper.getLendingNotices();
        notices = new ArrayList<Notice>();
        notices.add( new Notice("bad", 5, "dasdfa", 0, new User("Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100),
                100, new LocationG()));
        notices.add( new Notice("bad", 5, "dasdfa", 0, new User("Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100),
                100, new LocationG()));


        adapter = new MyNoticesAdapter(c, notices);
        listView.setAdapter(adapter);


        try {
            gView.setText(CallUserByEmail.call(email).getG() + "");
        }
        catch( Exception e ){
            e.printStackTrace();
        }





        return view;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString("email", email);

        switch (v.getId()) {
            case R.id.lendingButton:
                try {
                    notices = getUserNotices(CallUserByEmail.call(email), 'L');
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.borrowingButton:
                try {
                    notices = getUserNotices(CallUserByEmail.call(email), 'B');
                }
                catch (Exception e){
                    e.printStackTrace();
                }
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

    }




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