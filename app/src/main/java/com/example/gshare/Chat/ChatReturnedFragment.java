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

import com.example.gshare.CallUserByEmail;
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
import java.util.Timer;
import java.util.TimerTask;

public class ChatReturnedFragment extends Fragment {
    HomePageActivity a;
    ListView listView;
    ChatAdapter chatAdapter;
    String textBeSend;

    EditText editText;
    EditText editG;
    EditText editDay;
    TextView noticeName;
    Button userNumaAndSurname;
    TextView gText;

    Notice chatNotice;
    Chat chat;
    User recieverUser;
    User itemOwner;

    String email;
    int noticeId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        a.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View view = inflater.inflate(R.layout.fragment_chat_returned, container, false);


        email = getArguments().getString("email");
        noticeId = getArguments().getInt("noticeId");

        editG = view.findViewById(R.id.gEditText);
        editDay = view.findViewById(R.id.daysEditText);
        editText = view.findViewById(R.id.editText);
        noticeName = view.findViewById(R.id.itemName);
        userNumaAndSurname = view.findViewById(R.id.nameButton);
        gText = view.findViewById(R.id.moneyTextView);


        //chat = DBHelper.getChat();
        chatNotice = chat.getNotice();//new Notice("bad",5,"dasdfa",0, new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 ),
                //100,new LocationG());//DBHelper.getNotice(noticeId);

        chat.setStatus(Chat.RETURNED);

        noticeName.setText(chatNotice.getName());
        editG.setText( chatNotice.getG() + "" );
        editDay.setText( chatNotice.getDay() + "");

        try {
            gText.setText(CallUserByEmail.call(email).getG()+"");
        }
        catch (Exception e ){
            e.printStackTrace();
        }

        try {
            if (CallUserByEmail.call(email).equals(chat.getCustomer())) {
                userNumaAndSurname.setText(chat.getNoticeOwner().getNameAndSurname());
                recieverUser = chat.getNoticeOwner();
                if (chat.getNotice().getNoticeType() == Notice.BORROW_NOTICE) {
                    itemOwner = chat.getCustomer();
                }
                if (chat.getNotice().getNoticeType() == Notice.LEND_NOTICE) {
                    itemOwner = chat.getNoticeOwner();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            if (CallUserByEmail.call(email).equals(chat.getNoticeOwner())) {
                userNumaAndSurname.setText(chat.getCustomer().getNameAndSurname());
                recieverUser = chat.getCustomer();
                if (chat.getNotice().getNoticeType() == Notice.LEND_NOTICE) {
                    itemOwner = chat.getNoticeOwner();
                }
                if (chat.getNotice().getNoticeType() == Notice.BORROW_NOTICE) {
                    itemOwner = chat.getCustomer();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        userNumaAndSurname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                try {
                    if (CallUserByEmail.call(email).equals(chat.getCustomer())) {
                        bundle.putString("userEmail", chat.getNoticeOwner().getEmail());
                    }
                    if (CallUserByEmail.call(email).equals(chat.getNoticeOwner())) {
                        bundle.putString("userEmail", chat.getCustomer().getUserName());
                    }
                }
                catch (Exception e ){
                    e.printStackTrace();
                }
                ProfilePublicFragment publicProfile = new ProfilePublicFragment();
                publicProfile.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, publicProfile);

            }
        });


        listView = (ListView) view.findViewById(R.id.chatListView);
        listView.setAdapter(new ChatAdapter(a, chat.getAllMessage(), email));
        ImageButton buttonSend = (ImageButton) view.findViewById(R.id.imageButtonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textBeSend = editText.getText().toString();
                Message message = null;
                try {
                    message = new Message(textBeSend, recieverUser, CallUserByEmail.call(email));//CallByUserEmail.call(email)
                }
                catch (Exception e ){
                    e.printStackTrace();
                }
                textBeSend = message.getMessage() + "\t" + " ( " + message.getCurrentTime() + " ) ";
                message.setMsg(textBeSend);

                if (!textBeSend.matches("")) {
                    chat.getAllMessage().add(message);
                    listView.setAdapter(new ChatAdapter(a, chat.getAllMessage(), email));
                    editText.setText("");
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
    public void updateFragment() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Bundle bundle = getArguments();
                ChatReturnedFragment chatReturnedFragment = new ChatReturnedFragment();
                chatReturnedFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_biglayout,chatReturnedFragment);
            }
        }, 0, 10000);
    }
}
