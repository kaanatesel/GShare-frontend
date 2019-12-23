package com.example.gshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gshare.Notice.NoticeTry;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class HomePageFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // setupBottomNavigationView();
        return inflater.inflate(R.layout.fragment_home_page, container,false);
        ListView listView = (ListView) findViewById(R.id.list);
        NoticeTry[] notices = new NoticeTry[2];
        notices[0] = new NoticeTry("kitap", "3", "1");
        notices[1] = new NoticeTry("elma", "4", "2");
        com.example.gshare.ListViewAdapter adapter = new com.example.gshare.ListViewAdapter(getApplicationContext(), notices);
    }



}
