package com.example.gshare;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.Sort.Sort;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.Notice.ContactPurpleNoticeFragment;
import com.example.gshare.Notice.CreatePurpleNoticeFragment;
import com.example.gshare.Notice.CreateYellowNoticeFragment;
import com.example.gshare.Popup.PopupSortByFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.graphics.Color;

import java.util.ArrayList;

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
    ArrayList<Notice> notices;
    char sortMode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container,false);
        sortMode = LENDING_MODE;

        if( getArguments().getString("userName")!= null ){
            String userName = getArguments().getString("userName");
            String password = getArguments().getString("password");
            user = new User( "Cagri Eren", "asdas","asdfasdf","sdfasdf",100);//DBHelper.getUser(userName,password);
            //notices = DBHelper.getAllNotices();
        }
        Bundle bundle = getArguments();

        notices = new ArrayList<Notice>();
        notices = sort( notices , bundle.getBoolean("ACCEPTED" , false), bundle.getBoolean("G"),bundle.getInt("MIN"),
                bundle.getInt("MAX"),bundle.getBoolean("NEWEST"),bundle.getBoolean("ALPHABETICAL"));

        sortButton = view.findViewById(R.id.sortby_button);
        lendModeButton = view.findViewById(R.id.lendingSortiButton);
        borrowModeButton = view.findViewById(R.id.borrowingSortButton);
        addNoticeButton = view.findViewById(R.id.addNoticeButton);
        gText = view.findViewById(R.id.moneyTextView);
        button = view.findViewById(R.id.button13);
        addNoticeButton.setOnClickListener(this);
        borrowModeButton.setOnClickListener(this);
        lendModeButton.setOnClickListener(this);
        sortButton.setOnClickListener(this);
        button.setOnClickListener(this);

        // just setting colors and fonts
        lendModeButton.setBackgroundColor(Color.parseColor("#FFFF00") );
        borrowModeButton.setBackgroundColor(Color.parseColor("#B201D8"));
        addNoticeButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF681F")));
        lendModeButton.setTypeface(null, Typeface.BOLD_ITALIC);
        borrowModeButton.setTypeface(null , Typeface.NORMAL);
        // end

        gText.setText( user.getG() + "" );
        bundle.putBoolean("ACCEPTED",false);
        return view;
    }
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
                    fragmentTransaction.replace( R.id.homepage_placeholder,fragmentLend);
                    fragmentTransaction.commit();
                }
                if( sortMode == BORROWING_MODE ){
                    Bundle bundle = new Bundle();
                    bundle.putString("userName",user.getUserName());
                    bundle.putString("password",user.getPassword());
                    CreatePurpleNoticeFragment fragmentBorrow = new CreatePurpleNoticeFragment();
                    fragmentBorrow.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.homepage_placeholder,fragmentBorrow);
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
                notices = Sort.getBorrowings( notices);
                borrowModeButton.setBackgroundColor(Color.parseColor("#F000FF"));
                lendModeButton.setBackgroundColor(Color.parseColor("#FF9800") );
                addNoticeButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#492A4B")));
                lendModeButton.setTypeface(null, Typeface.NORMAL);
                borrowModeButton.setTypeface(null , Typeface.BOLD_ITALIC);
                //CONNECT TO THE ADAPTER
                break;
            case R.id.lendingSortiButton:
                sortMode = LENDING_MODE;
                notices = Sort.getLendings( notices);
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
