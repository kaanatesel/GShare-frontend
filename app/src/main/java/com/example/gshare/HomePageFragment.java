package com.example.gshare;

import android.app.Activity;

import android.content.Context;


import android.content.res.ColorStateList;

import android.graphics.Typeface;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.SearchView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.gshare.ModelClasses.Location.LocationG;


import java.io.IOException;
import java.util.ArrayList;


import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.Sort.Sort;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.Notice.ContactPurpleNoticeFragment;
import com.example.gshare.Notice.ContactYellowNoticeFragment;
import com.example.gshare.Notice.CreatePurpleNoticeFragment;
import com.example.gshare.Notice.CreateYellowNoticeFragment;
import com.example.gshare.Notice.ListViewAdapter;
import com.example.gshare.Notice.ProductHomeListDisplayModel;
import com.example.gshare.Popup.PopupSortByFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonObject;

import android.graphics.Color;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomePageFragment extends Fragment implements View.OnClickListener {

    public final static char LENDING_MODE = 'L';
    public final static char BORROWING_MODE = 'B';

    User user;
    TextView gText;
    FloatingActionButton addNoticeButton;
    Button lendModeButton;
    Button borrowModeButton;
    Button button;
    ImageButton sortButton;
    String userName;
    String password;
    OkHttpClient httpClient;
    ArrayList<ProductHomeListDisplayModel> notices;
    char sortMode;

    ListView listView = null;
    Context c = null;

    ListViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_page,container,false);

        httpClient = new OkHttpClient();
        sortMode = LENDING_MODE;

        if( getArguments() != null ){
            userName = getArguments().getString("userName");
            password = getArguments().getString("password");
        }
        Bundle bundle = getArguments();

        sortButton = view.findViewById(R.id.sortby_button);
        lendModeButton = view.findViewById(R.id.lendingSortiButton);
        borrowModeButton = view.findViewById(R.id.borrowingSortButton);
        addNoticeButton = view.findViewById(R.id.addNoticeButton);
        gText = view.findViewById(R.id.moneyTextView);

        addNoticeButton.setOnClickListener(this);
        borrowModeButton.setOnClickListener(this);
        lendModeButton.setOnClickListener(this);
        sortButton.setOnClickListener(this);

        // just setting colors and fonts
        lendModeButton.setBackgroundColor(Color.parseColor("#FFFF00") );
        borrowModeButton.setBackgroundColor(Color.parseColor("#B201D8"));
        addNoticeButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF681F")));
        lendModeButton.setTypeface(null, Typeface.BOLD_ITALIC);
        borrowModeButton.setTypeface(null , Typeface.NORMAL);
        // end

        // gText.setText( user.getG() + "" );

        listView = (ListView) view.findViewById(R.id.list);
        notices = getNotices();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter = new ListViewAdapter(c,  notices);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Bundle args = new Bundle();
                        args.putInt("noticeId",notices.get(position).getId());

                        ContactYellowNoticeFragment fragmentForLending = new ContactYellowNoticeFragment();
                        fragmentForLending.setArguments(args);
                        FragmentTransaction fragmentTransactionForLending = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransactionForLending.replace(R.id.main_layout,fragmentForLending);
                        fragmentTransactionForLending.commit();
                    }
                });
            }

        }, 1000);

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
                //notices = new ArrayList<Notice>();
                //notices.add(new  Notice("bad",5,"dasdfa",0, new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 ),
                //        100,new LocationG()));
               // notices.add(new Notice("bad",5,"dasdfa",0, new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 ),
                 //       100,new LocationG()));

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
        ImageButton buttonSports = (ImageButton) view.findViewById(R.id.othersButton);
        buttonSports.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // notices = Sort.sortByCategory(DBHelper.getAllNotices,3,getNoticeType());
                //adapter = new ListViewAdapter(getApplicationContext(),notices);
            }
        });



        bundle.putBoolean("ACCEPTED",false);
        return view;

    }

    public ArrayList<ProductHomeListDisplayModel> getNotices(){
        String url = "http://35.242.192.20/product/getAllActive/";
        notices = new ArrayList<ProductHomeListDisplayModel>();
        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mMessage = response.body().string();
                try {
                    JSONArray jsonArray = new JSONArray(mMessage);

                    for(int i = 0; i < jsonArray.length();i++)
                    {
                        JSONObject json = jsonArray.getJSONObject(i);
                        ProductHomeListDisplayModel p1 = new ProductHomeListDisplayModel(
                                json.getInt("id"),
                                json.getInt("price"),
                                json.getString("name"),
                                json.getString("description"));
                        notices.add(p1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.e("adapeter", mMessage);
            }
        });
        return notices;
    }

    @Override
    public void onAttach(Context con){
        super.onAttach(con);
        c = con;}

    @Override
    public void onClick(View v ){
        switch ( v.getId() ){
            case R.id.addNoticeButton:
                if( sortMode == LENDING_MODE ){

                    Bundle bundle = new Bundle();
                    bundle.putString("userName",user.getUserName());
                    bundle.putString("password",user.getPassword());
                    CreateYellowNoticeFragment fragmentLend = new CreateYellowNoticeFragment();
                    fragmentLend.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace( R.id.main_layout,fragmentLend);
                    fragmentTransaction.commit();
                }
                if( sortMode == BORROWING_MODE ){
                    Bundle bundle = new Bundle();
                    bundle.putString("userName",user.getUserName());
                    bundle.putString("password",user.getPassword());
                    CreatePurpleNoticeFragment fragmentBorrow = new CreatePurpleNoticeFragment();
                    fragmentBorrow.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_layout,fragmentBorrow);
                    fragmentTransaction.commit();
                }
                break;

            case R.id.sortby_button:
                Bundle bundle = new Bundle();
                bundle.putString("userName",user.getUserName());
                bundle.putString("password",user.getPassword());
                PopupSortByFragment popupSortByFragment = new PopupSortByFragment();
                popupSortByFragment.setArguments(bundle);
                popupSortByFragment.show( getFragmentManager(), "SortPopUp");
                break;

            case R.id.borrowingSortButton:
                sortMode = BORROWING_MODE;
                //   notices = Sort.getBorrowings( notices);
                borrowModeButton.setBackgroundColor(Color.parseColor("#F000FF"));
                lendModeButton.setBackgroundColor(Color.parseColor("#FF9800") );
                addNoticeButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#492A4B")));
                lendModeButton.setTypeface(null, Typeface.NORMAL);
                borrowModeButton.setTypeface(null , Typeface.BOLD_ITALIC);
                //CONNECT TO THE ADAPTER
                break;
            case R.id.lendingSortiButton:
                sortMode = LENDING_MODE;
                // notices = Sort.getLendings( notices);
                lendModeButton.setBackgroundColor(Color.parseColor("#FFFF00") );
                borrowModeButton.setBackgroundColor(Color.parseColor("#B201D8"));
                addNoticeButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF681F")));
                lendModeButton.setTypeface(null, Typeface.BOLD_ITALIC);
                borrowModeButton.setTypeface(null , Typeface.NORMAL);
                //CONNECT TO THE ADAPTER
                break;
        }

    }

    public ArrayList<Notice> sort( ArrayList<Notice> list ,boolean accepted , boolean g, int min, int max, boolean newest, boolean alphabetical ){
        if( accepted ){
            if(sortMode==LENDING_MODE) {
                if (g) {
                    list = Sort.sortByGInterval(list, min, max);
                }
            }
            if(newest){
                list = Sort.sortByPostTime(list,sortMode);
            }
            if(alphabetical){
                list = Sort.sortByLexiography(list,sortMode);
            }
        }
        return list;
    }



}