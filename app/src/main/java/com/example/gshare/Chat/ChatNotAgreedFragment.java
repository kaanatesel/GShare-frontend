package com.example.gshare.Chat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ChatAdapter;
import com.example.gshare.DBHelper;
import com.example.gshare.HomePageActivity;
import com.example.gshare.MainActivity;
import com.example.gshare.ModelClasses.ChatModel.Chat;
import com.example.gshare.ModelClasses.ChatModel.Message;
import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.Popup.PopupDoYouAgreeFragment;
import com.example.gshare.Profile.ProfilePublicFragment;
import com.example.gshare.R;

import java.util.ArrayList;

public class ChatNotAgreedFragment extends Fragment {
    HomePageActivity a;
    ListView listView;
    ChatAdapter chatAdapter;
    String textBeSend;
    ArrayList<String> stringMessages;
    String user;
    //String user;
    String notice;
    ArrayList<ChatTry> chatFragmentTry;

    EditText editText;
    EditText editG;
    EditText editDay;
    TextView noticeName;
    Button userNumaAndSurname;

    Notice chatNotice;
    Chat chat;
    User recieverUser;

    String userName;
    int noticeId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_not_agreed, container, false);

        chatFragmentTry = new ArrayList<ChatTry>();
        /*
        chatFragmentTry.add(new ChatTry("message 1", false));
        chatFragmentTry.add(new ChatTry("message 2", true));
        chatFragmentTry.add(new ChatTry("message 1", false));
        chatFragmentTry.add(new ChatTry("message 2", true));
        chatFragmentTry.add(new ChatTry("message 1", false));
        chatFragmentTry.add(new ChatTry("message 2", true));
        chatFragmentTry.add(new ChatTry("message 1", false));
        chatFragmentTry.add(new ChatTry("message 2", true));
        chatFragmentTry.add(new ChatTry("message 1", false));
        chatFragmentTry.add(new ChatTry("message 2", true));
        chatFragmentTry.add(new ChatTry("message 1", false));
        chatFragmentTry.add(new ChatTry("message 2", true));
        chatFragmentTry.add(new ChatTry("message 1", false));
        chatFragmentTry.add(new ChatTry("message 2", true));*/

        userName = getArguments().getString("userName");
        noticeId = getArguments().getInt("noticeId");

        editG = view.findViewById(R.id.gEditText);
        editDay = view.findViewById(R.id.daysEditText);
        editText = view.findViewById(R.id.editText);
        noticeName = view.findViewById(R.id.itemName);
        userNumaAndSurname = view.findViewById(R.id.nameButton);

        chatNotice = new Notice("bad",5,"dasdfa",0, new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 ),
                100,new LocationG());//DBHelper.getNotice(noticeId);
        //chat = DBHelper.getChat();


        noticeName.setText(chatNotice.getName());
        editG.setText( chatNotice.getG() + "" );
        editDay.setText( chatNotice.getDay() + "");

        if( DBHelper.getUser().equals(chat.getCustomer()) ) {
            userNumaAndSurname.setText(chat.getNoticeOwner().getNameAndSurname());
            recieverUser = chat.getNoticeOwner();
        }
        if( DBHelper.getUser().equals(chat.getNoticeOwner() ) ) {
            userNumaAndSurname.setText(chat.getCustomer().getNameAndSurname());
            recieverUser = chat.getCustomer();
        }

        Button agreeButton = (Button) view.findViewById(R.id.terminateButton);
        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("userName",userName);
                bundle.putInt("noticeId",noticeId);

                if(chatNotice.getNoticeType() == Notice.BORROW_NOTICE){
                    bundle.putInt("g",chatNotice.getG());
                    chatNotice.setDay(Integer.parseInt(editDay.getText().toString()));
                    //DBHelper.updateNotice(chatNotice);
                }
                else {
                    try {
                        chatNotice.setG(Integer.parseInt(editG.getText().toString()));
                    } catch (IllegalArgumentException e) {
                        Toast.makeText(getActivity(), "You can't make g value higher", Toast.LENGTH_SHORT).show();
                    }
                    chatNotice.setDay(Integer.parseInt(editDay.getText().toString()));
                    //DBHelper.updateNotice(chatNotice);
                }
                PopupDoYouAgreeFragment agreePopUp = new PopupDoYouAgreeFragment();
                agreePopUp.setArguments(bundle);
                agreePopUp.show(getFragmentManager(), "AgreePopUp");
            }
        });

        userNumaAndSurname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                if( DBHelper.getUser().equals(chat.getCustomer()) ) {
                    bundle.putString("personUserName", chat.getNoticeOwner().getUserName());
                    bundle.putString("personPassword", chat.getNoticeOwner().getPassword());
                }
                if( DBHelper.getUser().equals(chat.getNoticeOwner()) ) {
                    bundle.putString("personUserName", chat.getCustomer().getUserName());
                    bundle.putString("personPassword", chat.getCustomer().getPassword());
                }
                ProfilePublicFragment publicProfile = new ProfilePublicFragment();
                publicProfile.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, publicProfile);

            }
        });


        listView = (ListView) view.findViewById(R.id.chatListView);
        listView.setAdapter(new ChatAdapter(a, chatFragmentTry));
        ImageButton buttonSend = (ImageButton) view.findViewById(R.id.imageButtonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textBeSend = editText.getText().toString();
                if (textBeSend != "") {
                    Message message = new Message(textBeSend, recieverUser, DBHelper.getUser() );
                    stringMessages.add(message.toString());
                    chatFragmentTry.add(new ChatTry(textBeSend, true));
                    listView.setAdapter(new ChatAdapter(a, chatFragmentTry));
                }
            }
        });

        return view;
    }

    public void onAttach(Context con) {
        super.onAttach(con);

        if (con instanceof Activity) {
            a = (HomePageActivity) con;
        }
    }
}
