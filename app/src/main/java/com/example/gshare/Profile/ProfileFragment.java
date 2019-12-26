package com.example.gshare.Profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.example.gshare.Notice.MyNoticesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    User user;
    ImageButton editButton;
    TextView nameAndSurname;
    TextView username;

    ImageButton home;
    ImageButton map;
    ImageButton noticeNav;
    ImageButton chat;
    ImageButton profile;

    Notice notice;
    ListView listView;
        ArrayList<Notice> notices;
    String email;
    ProfileTransactionAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            email = bundle.getString("email");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //user = DBHelper.getUser(email);

        editButton = (ImageButton) view.findViewById(R.id.editProfileButton);
        nameAndSurname = (TextView) view.findViewById(R.id.nameTextView);
        username = (TextView) view.findViewById(R.id.usernameTextView);

        home = view.findViewById(R.id.navigationHome);
        map = view.findViewById(R.id.navigationMap);
        noticeNav = view.findViewById(R.id.navigationMyNotices);
        chat = view.findViewById(R.id.navigationChat);
        profile = view.findViewById(R.id.navigationProfile);

        //username.setText(user.getUserName());
        //nameAndSurname.setText(user.getNameAndSurname());

        //notices = getTransactions(user);
        //listView = (ListView) view.findViewById(R.id.listViewProfile);
        //adapter = new ProfileTransactionAdapter(getContext(),notices);
        //listView.setAdapter(adapter);


        editButton.setOnClickListener(this);

        home.setOnClickListener(this);
        map.setOnClickListener(this);
        noticeNav.setOnClickListener(this);
        chat.setOnClickListener(this);
        profile.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = getArguments();
        switch (v.getId()) {
            case R.id.editProfileButton:
                ProfileEditFragment profileEditFragment = new ProfileEditFragment();
                profileEditFragment.setArguments(bundle);
                profileEditFragment.show(getFragmentManager(), "EditPopUp");
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
}
