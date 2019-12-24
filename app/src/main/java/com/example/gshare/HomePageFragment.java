package com.example.gshare;

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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.Sort.Sort;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.Notice.CreatePurpleNoticeFragment;
import com.example.gshare.Notice.CreateYellowNoticeFragment;
import com.example.gshare.Popup.PopupSortByFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomePageFragment extends Fragment implements View.OnClickListener {

    public final static char LENDING_MODE = 'L';
    public final static char BORROWING_MODE = 'B';

    User user;
    TextView gText;
    FloatingActionButton addNoticeButton;
    Button lendModeButton;
    Button borrowModeButton;
    ImageButton sortButton;
    ArrayList<Notice> notices;
    char sortMode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container,false);
        sortMode = LENDING_MODE;

        if( getArguments()!= null ){
            String userName = getArguments().getString("userName");
            String password = getArguments().getString("password");
            user = new User( "Cagri Eren", "asdas","asdfasdf","sdfasdf",100);//DBHelper.getUser(userName,password);
            //notices = DBHelper.getAllNotices();
        }
        Bundle bundle = getArguments();

        notices = new ArrayList<Notice>();
        notices = sort( notices , bundle.getBoolean("ACCEPTED"), bundle.getBoolean("G"),bundle.getInt("MIN"),
                bundle.getInt("MAX"),bundle.getBoolean("NEWEST"),bundle.getBoolean("ALPHABETICAL"));

        sortButton = view.findViewById(R.id.sortby_button);
        lendModeButton = view.findViewById(R.id.lendingSortiButton);
        borrowModeButton = view.findViewById(R.id.borrowingSortButton);
        addNoticeButton = view.findViewById(R.id.addNoticeButton);
        gText = view.findViewById(R.id.moneyTextView);
        addNoticeButton.setOnClickListener(this);
        borrowModeButton.setOnClickListener(this);
        lendModeButton.setOnClickListener(this);
        sortButton.setOnClickListener(this);

        gText.setText( user.getG() + "" );
        bundle.putBoolean("ACCEPTED",false);
        return view;
    }
    @Override
    public void onClick(View v ){
        switch ( v.getId() ){
            case R.id.addNoticeButton:
                if( sortMode == LENDING_MODE ){
                    CreateYellowNoticeFragment fragmentLend = new CreateYellowNoticeFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace( R.id.homepage_placeholder,fragmentLend);
                    fragmentTransaction.commit();
                }
                if( sortMode == BORROWING_MODE ){
                    CreatePurpleNoticeFragment fragmentBorrow = new CreatePurpleNoticeFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.homepage_placeholder,fragmentBorrow);
                    fragmentTransaction.commit();
                }
                break;

            case R.id.sortby_button:
                PopupSortByFragment popupSortByFragment = new PopupSortByFragment();
                popupSortByFragment.show( getFragmentManager(), "SortPopUp");
                break;

            case R.id.borrowingSortButton:
                sortMode = BORROWING_MODE;
                notices = Sort.getBorrowings( notices);
                //CONNECT TO THE ADAPTER
                break;
            case R.id.lendingSortiButton:
                sortMode = LENDING_MODE;
                notices = Sort.getLendings( notices);
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
