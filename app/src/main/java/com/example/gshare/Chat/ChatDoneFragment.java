package com.example.gshare.Chat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

public class ChatDoneFragment extends Fragment {
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
    User itemOwner;

    String userName;
    int noticeId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        a.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View view = inflater.inflate(R.layout.fragment_chat_done, container, false);

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


        //chat = DBHelper.getChat();
        chatNotice = new Notice("bad",5,"dasdfa",0, new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 ),
                100,new LocationG());//DBHelper.getNotice(noticeId);
        chat.setStatus(Chat.WAITING_FOR_RETURN);

        noticeName.setText(chatNotice.getName());
        editG.setText( chatNotice.getG() + "" );
        editDay.setText( chatNotice.getDay() + "");

        if( DBHelper.getUser().equals(chat.getCustomer()) ) {
            userNumaAndSurname.setText(chat.getNoticeOwner().getNameAndSurname());
            recieverUser = chat.getNoticeOwner();
            if(chat.getNotice().getNoticeType()==Notice.BORROW_NOTICE){
                itemOwner = chat.getCustomer();
            }
            if(chat.getNotice().getNoticeType()==Notice.LEND_NOTICE){
                itemOwner = chat.getNoticeOwner();
            }
        }
        if( DBHelper.getUser().equals(chat.getNoticeOwner() ) ) {
            userNumaAndSurname.setText(chat.getCustomer().getNameAndSurname());
            recieverUser = chat.getCustomer();
            if(chat.getNotice().getNoticeType()== Notice.LEND_NOTICE){
                itemOwner = chat.getNoticeOwner();
            }
            if(chat.getNotice().getNoticeType()==Notice.BORROW_NOTICE){
                itemOwner = chat.getCustomer();
            }
        }

        Button returned = (Button) view.findViewById(R.id.returnedButton);
        returned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DBHelper.getUser().equals(itemOwner)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("userName", userName);
                    bundle.putInt("noticeId", noticeId);
                    chat.setStatus(Chat.RETURNED);
                    chatNotice.finish();
                    ChatReturnedFragment chatRetunedFragment = new ChatReturnedFragment();
                    chatRetunedFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_biglayout, chatRetunedFragment);
                }
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


                if (!textBeSend.matches("")) {
                    // Message message = new Message(textBeSend, recieverUser, DBHelper.getUser() );
                    //stringMessages.add(message.toString());
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
