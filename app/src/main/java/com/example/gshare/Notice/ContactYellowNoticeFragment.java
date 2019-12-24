package com.example.gshare.Notice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gshare.ChatActivity;
import com.example.gshare.ModelClasses.ChatModel.Chat;
import com.example.gshare.ModelClasses.ChatModel.ChatCollection;
import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.R;

/**
 * Fix these when DBHElper added
 */
public class ContactYellowNoticeFragment extends Fragment implements View.OnClickListener {
    Notice notice;
    int noticeId;
    TextView title;
    TextView days;
    TextView note;
    TextView g;
    Button contactButton;

    String userName;
    String password;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticecontactyellow, container, false);
        Bundle bundle = getArguments();

        noticeId = bundle.getInt("notice_id");
        userName = bundle.getString("userName");
        password = bundle.getString("password");

        notice = new Notice("bad",5,"dasdfa",0, new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 ),
                100,new LocationG());//DBHelper.getNotice(noticeId);

        g = view.findViewById(R.id.noticeg);
        title = view.findViewById(R.id.noticeTitle);
        days = view.findViewById(R.id.noticedays);
        note = view.findViewById(R.id.noticenote);
        contactButton = view.findViewById(R.id.contactButton);

        title.setText(notice.getName());
        days.setText(notice.getDay() + "");
        note.setText(notice.getNote());
        g.setText(notice.getG());

        contactButton.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {
        User user = new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 );//DBHelper.getUser( userName , password);
        Chat chat = new Chat( notice , notice.getNoticeOwner(), user );
        //ChatCollection chatCollection = DBHelper.getChatCollection( DBHelper.getUserId( user ) );

        /*
        if( !chatCollection.getAllChat().contains(chat)) {
            //chatCollection.addChat( chat );
            //DBHelper.addChat( chat );
            //DBHelper.updateChatCollection( chatCollection );
        }*/

        Intent intent = new Intent(getActivity(), ChatActivity.class );
        intent.putExtra("USERNAME" , user.getUserName() );
        intent.putExtra("PASSWORD", user.getPassword());
        startActivity(intent);
    }
}
