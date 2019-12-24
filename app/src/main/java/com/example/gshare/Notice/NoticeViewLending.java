package com.example.gshare.Notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.R;

public class NoticeViewLending extends Fragment implements View.OnClickListener {

    Notice notice;
    int noticeId;

    EditText name;
    EditText days;
    EditText note;
    EditText g;
    Button editNoticeButton;
    ImageButton backButton;

    String userName;
    String password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice_view_lending, container, false);

        Bundle bundle = getArguments();
        userName = bundle.getString("userName");
        password = bundle.getString("password");
        noticeId = bundle.getInt("notice_id");

        notice = new Notice("bad",5,"dasdfa",0, new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 ),
                100,new LocationG());//DBHelper.getNotice(noticeId);

        name = view.findViewById(R.id.itemNameinput);
        days = view.findViewById(R.id.daysinput);
        note = view.findViewById(R.id.noteinput);
        g = view.findViewById(R.id.ginput);
        editNoticeButton = view.findViewById(R.id.EditNoticeLending);
        backButton = view.findViewById(R.id.backButton);

        editNoticeButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        name.setText(notice.getName());
        days.setText(notice.getDay()+"");
        note.setText(notice.getNote());
        g.setText( notice.getG() );

        return view;

    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.EditNoticeLending:
                NoticeEditLendingFragment noticeEditLendingFragment = new NoticeEditLendingFragment();
                noticeEditLendingFragment.setArguments( getArguments() );
                FragmentTransaction fragmentTransaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.mynotices_placeholder, noticeEditLendingFragment);
                fragmentTransaction1.commit();
                break;
            case R.id.backButton:
                MyNoticesFragment myNoticesFragment = new MyNoticesFragment();
                myNoticesFragment.setArguments( getArguments() );
                FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.mynotices_placeholder,myNoticesFragment);
                fragmentTransaction2.commit();
                break;

        }
    }
}
