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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ChatAdapter;
import com.example.gshare.HomePageActivity;
import com.example.gshare.MainActivity;
import com.example.gshare.ModelClasses.ChatModel.Chat;
import com.example.gshare.ModelClasses.ChatModel.Message;
import com.example.gshare.R;

import java.util.ArrayList;

public class ChatNotAgreedFragment extends Fragment {
   HomePageActivity a;
    ListView listView;
    ChatAdapter chatAdapter;
    String textBeSend;
    ArrayList<String> stringMessages;
    Chat chat;
    String user;
    //String user;
    String notice;
    ArrayList<ChatTry> chatFragmentTry;
    EditText editText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_chat_not_agreed, container, false);
        chatFragmentTry = new ArrayList<ChatTry>();
        chatFragmentTry.add(new ChatTry("message 1",false));
        chatFragmentTry.add(new ChatTry("message 2",true));
        chatFragmentTry.add(new ChatTry("message 1",false));
        chatFragmentTry.add(new ChatTry("message 2",true));
        chatFragmentTry.add(new ChatTry("message 1",false));
        chatFragmentTry.add(new ChatTry("message 2",true));
        chatFragmentTry.add(new ChatTry("message 1",false));
        chatFragmentTry.add(new ChatTry("message 2",true));
        chatFragmentTry.add(new ChatTry("message 1",false));
        chatFragmentTry.add(new ChatTry("message 2",true));
        chatFragmentTry.add(new ChatTry("message 1",false));
        chatFragmentTry.add(new ChatTry("message 2",true));
        chatFragmentTry.add(new ChatTry("message 1",false));
        chatFragmentTry.add(new ChatTry("message 2",true));
        Button agreeButton =(Button) view.findViewById(R.id.terminateButton);
        agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ChatAgreedFragment fragment = new ChatAgreedFragment();
                FragmentTransaction fragmentTransaction = a.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout,fragment);
                fragmentTransaction.commit();
            }
        });


                listView = (ListView) view.findViewById(R.id.chatListView);
        listView.setAdapter(new ChatAdapter(a,chatFragmentTry));
        editText = view.findViewById(R.id.gEditText);
        ImageButton buttonSend = (ImageButton)view.findViewById(R.id.imageButtonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textBeSend = editText.getText().toString();
                if(textBeSend != ""){
                    //Message message = new Message();
                    //stringMessages.add(message.toString());
                    chatFragmentTry.add(new ChatTry(textBeSend, true));
                    listView.setAdapter(new ChatAdapter(getContext(),chatFragmentTry));
                }
            }
        });

return view;
    }
    public void onAttach(Context con){
        super.onAttach(con);

        if(con instanceof Activity){
            a = (HomePageActivity) con;
        }
    }
}
