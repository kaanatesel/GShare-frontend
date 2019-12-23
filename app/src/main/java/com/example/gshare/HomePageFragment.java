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

public class HomePageFragment extends Fragment implements View.OnClickListener {

    public final static int LENDING_MODE = 1;
    public final static int BORROWING_MODE = 0;

    User user;
    TextView gText;
    FloatingActionButton addNoticeButton;
    Button lendModeButton;
    Button borrowModeButton;
    ImageButton sortButton;
    Notice[] notices;
    int sortMode;

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
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace( R.id.homepage_placeholder, popupSortByFragment);
                fragmentTransaction.commit();
                break;

            case R.id.borrowingSortButton:
                sortMode = BORROWING_MODE;
                notices = HomePageActivity.toArray( Sort.getBorrowings( HomePageActivity.toArrayList( notices) ) );
                //CONNECT TO THE ADAPTER
                break;
            case R.id.lendingSortiButton:
                sortMode = LENDING_MODE;
                notices = HomePageActivity.toArray( Sort.getLendings( HomePageActivity.toArrayList( notices) ) );
                //CONNECT TO THE ADAPTER
                break;
        }
    }



}
