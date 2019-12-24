package com.example.gshare.Notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    Button lendingMode;
    Button borrowingMode;
    TextView gView;

    ArrayList<Notice> notices;

    String userName;
    String password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_notices, container,false);

        Bundle bundle = getArguments();
        userName = bundle.getString("userName");
        password = bundle.getString("password");

        lendingMode = view.findViewById(R.id.lendingButton);
        borrowingMode = view.findViewById(R.id.borrowingButton);
        gView = view.findViewById(R.id.moneyTextView);

        gView.setText( DBHelper.getUser().getG() + "" );

        notices = DBHelper.getLendingNotices();



        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lendingButton:
                notices = DBHelper.getLendingNotices();
        }

    }

    public static ArrayList<Notice> getUserNotices(User user ){
        ArrayList<Notice> userList = new ArrayList<Notice>;
        ArrayList<Notice> allNotice = DBHelper.getLendingNotices();

        for( int i = 0; i<allNotice.size(); i++){
            if(allNotice.get(i).getNoticeOwner().equals(user)){
                userList.add(allNotice.get(i));
            }
        }
        return userList;
    }
}
