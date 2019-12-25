package com.example.gshare.Popup;

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

    Notice notice;
    Button yes;
    Button no;
    TextView gValue;
    TextView returnDateValue;
    Chat chat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_popup_doyouagree, container, false);

       notice = new Notice("bad",5,"dasdfa",0, new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 ),
               100,new LocationG());//DBHelper.getNotice( getArguments().getInt("noticeId"));

       yes = view.findViewById(R.id.agreeButton);
       no = view.findViewById(R.id.dontAgreeButton);
       gValue = view.findViewById(R.id.G);
       returnDateValue = view.findViewById(R.id.returndate);

       yes.setOnClickListener(this);
       no.setOnClickListener(this);

       if(notice.getNoticeType()==Notice.BORROW_NOTICE){
           gValue.setText(getArguments().getInt("g") + "");
       }
       else{
           gValue.setText(notice.getG()+"");
       }
       returnDateValue.setText("Return " + notice.getDay() + " days later"  );




       return view;
    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ){
            case R.id.agreeButton:
                if(notice.getNoticeType() == Notice.BORROW_NOTICE){
                    notice.agreeOnBorrowNotice( chat.getCustomer() ,getArguments().getInt("g"));
                }
                if(notice.getNoticeType() == Notice.LEND_NOTICE){
                    notice.agreeOnLendNotice( chat.getCustomer() );
                }
                ChatAgreedFragment fragment = new ChatAgreedFragment();
                fragment.setArguments(getArguments());
                FragmentTransaction fragmentTransaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.main_layout, fragment);
                fragmentTransaction1.commit();
                getDialog().dismiss();
                break;
            case R.id.dontAgreeButton:
                ChatNotAgreedFragment fragment2 = new ChatNotAgreedFragment();
                fragment2.setArguments(getArguments());
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
