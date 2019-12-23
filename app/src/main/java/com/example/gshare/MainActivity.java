package com.example.gshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;


import com.example.gshare.Chat.ChatFragment;
import com.example.gshare.Chat.ChatTry;


import com.example.gshare.Notice.NoticeTry;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.gshare.R.layout.blank_layout;
import static com.example.gshare.R.layout.fragment_home_page;
import static com.example.gshare.R.layout.fragment_profile;


public class MainActivity extends AppCompatActivity {

    // bora
    // return bora
    // sa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_chat);

      /*ListView list = (ListView) findViewById(R.id.chatList);
        ChatTry[] chats = new ChatTry[2];
        chats[0] = new ChatTry("first message");
        chats[1] = new ChatTry("second message");
        ChatAdapter chatAdapter = new ChatAdapter(getApplicationContext(),chats);
        try {
            list.setAdapter(chatAdapter);
        }catch(NullPointerException e){
            setContentView(R.layout.activity_main);
        }*/

      setContentView(blank_layout);
        BottomNavigationView bottomNav = findViewById(R.id.nav_view);

        bottomNav.setOnNavigationItemSelectedListener(navListener);

        HomePageFragment fragmentHome = new HomePageFragment();
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.main_layout,fragmentHome);
        fragmentTransaction1.commit();
       /* ListView listView = (ListView) findViewById(R.id.list);
        NoticeTry[] notices = new NoticeTry[2];
        notices[0] = new NoticeTry("kitap", "3", "1");
        notices[1] = new NoticeTry("elma", "4", "2");
        com.example.gshare.ListViewAdapter adapter = new com.example.gshare.ListViewAdapter(getApplicationContext(), notices);
        try {
            listView.setAdapter(adapter);
        } catch (NullPointerException e) {
            setContentView(R.layout.activity_main);

        }*/
    }
    @NonNull
        private BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener(){
                @SuppressLint("ResourceType")
                @Override

                public boolean onNavigationItemSelected(@NonNull MenuItem item){
              try {

                            switch (item.getItemId()) {

                                case R.id.navigation_home:

                                    HomePageFragment fragment1 = new HomePageFragment();
                                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction1.replace(R.id.main_layout,fragment1);
                                    fragmentTransaction1.commit();
                                    return true;

                                case R.id.navigation_Map:
                                    HomePageFragment fragment2 = new HomePageFragment();
                                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction2.replace(R.id.main_layout,fragment2);
                                    fragmentTransaction2.commit();
                                    return true;

                                case R.id.navigation_Notices:
                                    MyNoticesFragment fragment3 = new MyNoticesFragment();
                                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction3.replace(R.id.main_layout,fragment3);
                                    fragmentTransaction3.commit();
                                    return true;
                                case R.id.navigation_Chat:
                                    ChatFragment fragment4 = new ChatFragment();
                                    FragmentTransaction fragmentTransaction4 = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction4.replace(R.id.main_layout,fragment4);
                                    fragmentTransaction4.commit();
                                    return true;


                                case R.id.navigation_Profilet:
                                    ProfileFragment fragment5 = new ProfileFragment();
                                    FragmentTransaction fragmentTransaction5 = getSupportFragmentManager().beginTransaction();
                                    fragmentTransaction5.replace(R.id.main_layout,fragment5);
                                    fragmentTransaction5.commit();
                                    return true;
                            }
                            return false;

                        }catch(NullPointerException e){
                    return false;
                        }

                }
        };

                }





