package com.example.gshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Notice.ContactYellowNoticeFragment;
import com.example.gshare.Notice.NoticeTry;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomePageFragment extends Fragment {

ArrayList<NoticeTry> notices;

ListView listView = null;
Context c = null;

    com.example.gshare.ListViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_page,container,false);

        listView = (ListView) view.findViewById(R.id.list);
    notices = new ArrayList<NoticeTry>();
        notices.add(new NoticeTry("for Exception","12","1"));

onAttach(c);
        adapter = new com.example.gshare.ListViewAdapter(c,notices);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle args = new Bundle();
                try {
                    args.putString("NoticeName", notices.get(position).getName());
                    args.putString("NoticeDay", notices.get(position).getDay());
                    args.putString("NoticeG", notices.get(position).getG());
                }catch(NullPointerException e){

                }
                //if(notices.get(position).getNoticeType() =='L'){
                    ContactYellowNoticeFragment fragmentForLending = new ContactYellowNoticeFragment();
                    fragmentForLending.setArguments(args);
                    FragmentTransaction fragmentTransactionForLending = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransactionForLending.replace(R.id.main_layout,fragmentForLending);
                    fragmentTransactionForLending.commit();

                //}else if(notşces.get(position).getNoticeType() =='R'){
                //ContactPurpleNoticeFragment fragmentForBorrowing= new ContactPurpleNoticeFragment();
                //    fragmentForBorrowing.setArguments(args);
                //                    FragmentTransaction fragmentTransactionForBorrowing = getActivity().getSupportFragmentManager().beginTransaction();
                //                    fragmentTransactionForBorrowing.replace(R.id.main_layout,fragmentForBorrowing);
                //                    fragmentTransactionForBorrowing.commit();
                //
                //
                //
                // }
            }
        });
        SearchView searchView = (SearchView) view.findViewById(R.id.homepageSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                return true;
            }

            public void callSearch(String query) {
                //notices = DBHelper.getAllNotices();
                notices = new ArrayList<NoticeTry>();
                notices.add(new NoticeTry("elma","12","3"));
                notices.add(new NoticeTry("armut","25","2"));
    /*
    notices = DbHelper.getAllNotices().sort
     */

    ;
    listView.setAdapter(adapter.update(notices));

            }
        });

        ImageButton buttonTransport = (ImageButton) view.findViewById(R.id.transportButton);
        buttonTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // notices = Sort.sortByCategory(DBHelper.getAllNotices,3,getNoticeType());
                //adapter = new ListViewAdapter(getApplicationContext(),notices);
            }
        });
        ImageButton buttonEducation = (ImageButton) view.findViewById(R.id.schoolButton);
        buttonEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // notices = Sort.sortByCategory(DBHelper.getAllNotices,1,getNoticeType());
                //adapter = new ListViewAdapter(getApplicationContext(),notices);
            }
        });
        ImageButton buttonElectronics = (ImageButton) view.findViewById(R.id.laptopButton);
        buttonElectronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // notices = Sort.sortByCategory(DBHelper.getAllNotices,2,getNoticeType());
                //adapter = new ListViewAdapter(getApplicationContext(),notices);
            }
        });
        //Button should be changed later
        ImageButton buttonCamera = (ImageButton) view.findViewById(R.id.cameraButton);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // notices = Sort.sortByCategory(DBHelper.getAllNotices,3,getNoticeType());
                //adapter = new ListViewAdapter(getApplicationContext(),notices);
            }
        });
        ImageButton buttonStationary = (ImageButton) view.findViewById(R.id.stationaryButton);
        buttonStationary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // notices = Sort.sortByCategory(DBHelper.getAllNotices,4,getNoticeType());
                //adapter = new ListViewAdapter(getApplicationContext(),notices);
            }
        });
        ImageButton buttonPets = (ImageButton) view.findViewById(R.id.petsButton);
        buttonPets.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // notices = Sort.sortByCategory(DBHelper.getAllNotices,5,getNoticeType());
                //adapter = new ListViewAdapter(getApplicationContext(),notices);
            }
        });
        //button should be changed later
        ImageButton buttonSports = (ImageButton) view.findViewById(R.id.sportsButton);
        buttonSports.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // notices = Sort.sortByCategory(DBHelper.getAllNotices,3,getNoticeType());
                //adapter = new ListViewAdapter(getApplicationContext(),notices);
            }
        });

        return view;


    }
    @Override
    public void onAttach(Context con){
        super.onAttach(con);
        c = con;
    }

}
