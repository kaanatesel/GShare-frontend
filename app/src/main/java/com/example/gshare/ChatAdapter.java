package com.example.gshare;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.gshare.Chat.ChatTry;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gshare.Notice.NoticeTry;

import java.util.ArrayList;

public class ChatAdapter extends ArrayAdapter<ChatTry> {
    String email;
    Context context;
    ArrayList<ChatTry> myChats = new ArrayList<ChatTry>();;


    private ArrayList<String> notices;
    public ChatAdapter(@NonNull Context c, @NonNull ArrayList<ChatTry> stringMessages, String email){
        super(c, R.layout.chatlayout,stringMessages);
        this.context = c;
        this.myChats = stringMessages;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View row = null;
        if(position <= myChats.size()){
        if(myChats.get(position).getUser()){
            row = layoutInflater.inflate(R.layout.chatlayoutsended,parent,false);
            TextView chatMessage = (TextView) row.findViewById(R.id.textViewSended);
            chatMessage.setText(myChats.get(position).getMessage());
        }else{
            row = layoutInflater.inflate(R.layout.chatlayout,parent,false);
            TextView chatMessageOther = (TextView) row.findViewById(R.id.textViewChat);
            chatMessageOther.setText(myChats.get(position).getMessage());
        }}
        return row;
    }

}
