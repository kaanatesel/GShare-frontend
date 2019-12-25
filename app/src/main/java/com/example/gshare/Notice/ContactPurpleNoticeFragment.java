package com.example.gshare.Notice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Chat.ChatNotAgreedFragment;
import com.example.gshare.ChatActivity;
import com.example.gshare.HomePageFragment;
import com.example.gshare.ModelClasses.ChatModel.Chat;
import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.Profile.ProfilePublicFragment;
import com.example.gshare.R;

/**
 * Fix the DBHElper parts when it is added
 */
public class ContactPurpleNoticeFragment extends Fragment implements View.OnClickListener {

    Notice notice;
    int noticeId;
    TextView title;
    TextView days;
    TextView note;
    Button contactButton;
    Button goToUser;
    ImageButton backButton;

    String email;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticecontactpurple, container, false);

        Bundle bundle = getArguments();

        noticeId = bundle.getInt("notice_id");
        email = bundle.getString("email");

        notice = new Notice("bad", 5, "dasdfa", 0, new User("Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100),
                new LocationG());//DBHelper.getNotice(noticeId);

        title = view.findViewById(R.id.noticeTitle);
        days = view.findViewById(R.id.noticedays);
        note = view.findViewById(R.id.noticenote);
        contactButton = view.findViewById(R.id.contactButton);
        backButton = view.findViewById(R.id.backButton);
        goToUser = view.findViewById(R.id.UserButton);

        title.setText(notice.getName());
        days.setText(notice.getDay() + "");
        note.setText(notice.getNote());
        goToUser.setText( "Go to " + notice.getNoticeOwner().getNameAndSurname() + " 's profile");

        contactButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        goToUser.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contactButton:
                User user = new User("Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100);//DBHelper.getUser( userName , password);
                Chat chat = new Chat(notice, notice.getNoticeOwner(), user);
                // ChatCollection chatCollection = DBHelper.getChatCollection( DBHelper.getUserId( user ) );

        /*
        if( !chatCollection.getAllChat().contains(chat)) {
            //chatCollection.addChat( chat );
            //DBHelper.addChat( chat );
            //DBHelper.updateChatCollection( chatCollection );
        }*/
        /*
        Intent intent = new Intent(getActivity(), ChatActivity.class );
        intent.putExtra("USERNAME" , user.getUserName() );
        intent.putExtra("PASSWORD", user.getPassword());
        startActivity(intent);*/

                Bundle bundle;
                bundle = getArguments();

                ChatNotAgreedFragment fragmentChatNotAgreed = new ChatNotAgreedFragment();
                fragmentChatNotAgreed.setArguments(bundle);
                FragmentTransaction fragmentManagerForNotAgreedChat = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentManagerForNotAgreedChat.replace(R.id.main_biglayout, fragmentChatNotAgreed);
                fragmentManagerForNotAgreedChat.commit();
                break;
            case R.id.backButton:
                Bundle bundle2;
                bundle2 = getArguments();
                HomePageFragment homePageFragment = new HomePageFragment();
                homePageFragment.setArguments(bundle2);
                FragmentTransaction fragmentManagerForHomePage = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentManagerForHomePage.replace(R.id.main_biglayout, homePageFragment);
                fragmentManagerForHomePage.commit();
                break;
            case R.id.userButton:
                Bundle bundle3;
                bundle3 = getArguments();
                bundle3.putString("userEmail",notice.getNoticeOwner().getEmail());
                ProfilePublicFragment profilePublicFragment = new ProfilePublicFragment();
                profilePublicFragment.setArguments(bundle3);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_biglayout, profilePublicFragment);
                break;
        }
    }

}

