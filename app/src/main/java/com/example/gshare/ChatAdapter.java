package com.example.gshare;

import android.content.Context;
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

    Context context;
    ArrayList<ChatTry> myChats;

    private ArrayList<NoticeTry> notices;
    public ChatAdapter(@NonNull Context c, @NonNull ArrayList<ChatTry> chats){
        super(c, R.layout.chatlayout,chats);
        this.context = c;
        this.myChats = chats;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.chatlayout,parent,false);
        TextView chatMessage = row.findViewById(R.id.textView);
        chatMessage.setText(myChats.get(position).getMessage());
        return row;
    }
}
