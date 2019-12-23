package com.example.gshare;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;

import java.util.ArrayList;

/**
 * This class is not final and will be changed by ONUR
 */
public class HomePageActivity extends AppCompatActivity {

    String userName = "a";
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //Wrote for test purposes only
        userName = getIntent().getStringExtra("USERNAME");
        password = getIntent().getStringExtra("PASSWORD");

        HomePageFragment homePageFragment = new HomePageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userName",userName);
        bundle.putString("password",password);
        homePageFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.homepage_placeholder,homePageFragment);
        fragmentTransaction.commit();
    }

    public static ArrayList<Notice> toArrayList( Notice[] notices ){
        ArrayList<Notice> noticeList = new ArrayList<Notice>();
        for( int i = 0; i < notices.length; i++ ){
            noticeList.add( notices[i] );
        }
        return noticeList;
    }
    public static Notice[] toArray( ArrayList<Notice> list ){
        Notice[] noticeList = new Notice[list.size()];
        for(int i = 0; i < noticeList.length; i++ ){
            noticeList[i] = list.get(i);
        }
        return noticeList;
    }

}
