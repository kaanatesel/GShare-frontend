package com.example.gshare.Popup;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Chat.ChatAgreedFragment;
import com.example.gshare.Chat.ChatNotAgreedFragment;
import com.example.gshare.DBHelper;
import com.example.gshare.ModelClasses.ChatModel.Chat;
import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PopupDoYouAgreeFragment extends DialogFragment implements View.OnClickListener {

    Notice chatNotice;
    Button yes;
    Button no;
    TextView gValue;
    TextView returnDateValue;
    Chat chat;
    Context con;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popup_doyouagree, container, false);


        final User  userTry = new User("OnurKorkmaz", "qwerty", "123456", "qwerty", 6);
        final User  userTry2 = new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 );

        //chat = DBHelper.getChat();
        chatNotice = new Notice("bad",5,"dasdfa",0,userTry,
                100,new LocationG());//DBHelper.getNotice(noticeId);
        chat = new Chat(chatNotice,chatNotice.getNoticeOwner(),userTry2);

       yes = view.findViewById(R.id.agreeButton);
       no = view.findViewById(R.id.dontAgreeButton);
       gValue = view.findViewById(R.id.G);
       returnDateValue = view.findViewById(R.id.returndate);

       yes.setOnClickListener(this);
       no.setOnClickListener(this);

       if(chatNotice.getNoticeType()==Notice.BORROW_NOTICE){
           gValue.setText(getArguments().getInt("g") + "");
       }
       else{
           gValue.setText(chatNotice.getG()+"");
       }
       returnDateValue.setText("Return " + chatNotice.getDay() + " days later"  );






       return view;

    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ){
            case R.id.agreeButton:
                if(chatNotice.getNoticeType() == Notice.BORROW_NOTICE){
                    chatNotice.agreeOnBorrowNotice( chat.getCustomer() ,getArguments().getInt("g"));
                }
                if(chatNotice.getNoticeType() == Notice.LEND_NOTICE){

                    chatNotice.agreeOnLendNotice( chat.getCustomer() );

                }
                getActivity().setContentView(R.layout.fullyblanklayout);
                ChatAgreedFragment fragment = new ChatAgreedFragment();
                fragment.setArguments(getArguments());
                FragmentTransaction fragmentTransaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.main_biglayout, fragment);
                fragmentTransaction1.commit();
                getDialog().dismiss();
                break;
            case R.id.dontAgreeButton:
                ChatNotAgreedFragment fragment2 = new ChatNotAgreedFragment();
                fragment2.setArguments(getArguments());
                getActivity().setContentView(R.layout.blank_layout);
                FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.main_layout, fragment2);
                fragmentTransaction2.commit();
                getDialog().dismiss();
                break;
        }

    }

    public static String getReturnDate( long dayMilli ) {
        long currentDateTime = System.currentTimeMillis();
        Date currentDate = new Date(currentDateTime + dayMilli);
        DateFormat df = new SimpleDateFormat("dd:MM:yy:HH:mm:ss");
        return df.format(currentDate);
    }



}
