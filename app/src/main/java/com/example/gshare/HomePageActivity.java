package com.example.gshare;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Chat.ChatFragment;

import com.example.gshare.Notice.ListViewAdapter;
import com.example.gshare.Notice.MyNoticesFragment;

import com.example.gshare.Profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * This class is not final and will be changed by ONUR
 */
public class HomePageActivity extends AppCompatActivity {

    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        email = getIntent().getStringExtra("email");
        setContentView(R.layout.blank_layout);
        HomePageFragment fragmentForBeginning = new HomePageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        fragmentForBeginning.setArguments(bundle);
        FragmentTransaction fragmentTransactionForBeginning = getSupportFragmentManager().beginTransaction();
        fragmentTransactionForBeginning.replace(R.id.main_layout, fragmentForBeginning);
        fragmentTransactionForBeginning.commit();

/*
        BottomNavigationView bottomNav = findViewById(R.id.nav_view);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

                                                          @Override
                                                          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                              Bundle bundle = new Bundle();
                                                              bundle.putString("email", email);
                                                              try {

                                                                  switch (item.getItemId()) {

                                                                      case R.id.navigation_home:
                                                                         HomePageFragment fragment1 = new HomePageFragment();
                                                                          fragment1.setArguments(bundle);
                                                                          FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                                                                          fragmentTransaction1.replace(R.id.main_layout, fragment1);
                                                                          fragmentTransaction1.commit();
                                                                          return true;

                                                                      case R.id.navigation_Map:
                                                                          HomePageFragment fragment2 = new HomePageFragment();
                                                                          fragment2.setArguments(bundle);
                                                                          FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                                                                          fragmentTransaction2.replace(R.id.main_layout, fragment2);
                                                                          fragmentTransaction2.commit();
                                                                          return true;

                                                                      case R.id.navigation_Notices:
                                                                          MyNoticesFragment fragment3 = new MyNoticesFragment();
                                                                          fragment3.setArguments(bundle);
                                                                          FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                                                                          fragmentTransaction3.replace(R.id.main_layout, fragment3);
                                                                          fragmentTransaction3.commit();
                                                                          return true;
                                                                      case R.id.navigation_Chat:
                                                                          ChatFragment fragment4 = new ChatFragment();
                                                                          fragment4.setArguments(bundle);
                                                                          FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                                                                          fragmentTransaction4.replace(R.id.main_layout, fragment4);
                                                                          fragmentTransaction4.commit();
                                                                          return true;


                                                                      case R.id.navigation_Profilet:
                                                                          ProfileFragment fragment5 = new ProfileFragment();
                                                                          fragment5.setArguments(bundle);
                                                                          FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                                                                          fragmentTransaction5.replace(R.id.main_layout, fragment5);
                                                                          fragmentTransaction5.commit();
                                                                          return true;
                                                                  }
                                                                  return false;

                                                              } catch (NullPointerException e) {
                                                                  return false;
                                                              }

                                                          }

                                                      }
        );*/
    }


}

