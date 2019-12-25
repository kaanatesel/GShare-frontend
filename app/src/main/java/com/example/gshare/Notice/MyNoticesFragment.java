package com.example.gshare.Notice;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gshare.DBHelper;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.R;



import java.util.ArrayList;

public class MyNoticesFragment extends Fragment implements View.OnClickListener {


    Context c;


    Button lendingMode;
    Button borrowingMode;
    TextView gView;

    ArrayList<Notice> notices;

    ListView listView;
    String userName;
    String password;
    MyNoticesAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_notices, container, false);




        Bundle bundle = getArguments();
        userName = bundle.getString("userName");
        password = bundle.getString("password");


        listView = (ListView) view.findViewById(R.id.listmynotices);

        lendingMode = view.findViewById(R.id.lendingButton);
        borrowingMode = view.findViewById(R.id.borrowingButton);
        gView = view.findViewById(R.id.moneyTextView);


        gView.setText(DBHelper.getUser(userName).getG() + "");

        notices = DBHelper.getLendingNotices();
        adapter = new MyNoticesAdapter(c, notices);
        listView.setAdapter(adapter);

        gView.setText( DBHelper.getUser(userName).getG() + "" );

        notices = DBHelper.getLendingNotices();



        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.lendingButton:
                notices = getUserNotices(DBHelper.getUser(userName), 'L');
                break;
            case R.id.borrowingButton:
                notices = getUserNotices(DBHelper.getUser(userName), 'B');

        switch (v.getId()){
            case R.id.lendingButton:
                notices = getUserNotices(DBHelper.getUser(userName) , 'L');
                break;
            case R.id.borrowingButton:
                notices = getUserNotices(DBHelper.getUser(userName),'B');

                break;

        }

    }}




    public static ArrayList<Notice> getUserNotices(User user , char type){
        ArrayList<Notice> userList = new ArrayList<Notice>();
        ArrayList<Notice> allNotice = new ArrayList<>();
        if(type == 'L') {
            allNotice = DBHelper.getLendingNotices();
        }
        if( type == 'B'){
            //allNotice = DBHelper.getBorrowingNotices();
        }

        for( int i = 0; i< allNotice.size(); i++){
            if(allNotice.get(i).getNoticeOwner().equals(user)){

                userList.add(allNotice.get(i));
            }
        }
        return userList;



    }
    @Override
    public void onAttach (Context con){
        super.onAttach(con);
        c = con;


    }
}