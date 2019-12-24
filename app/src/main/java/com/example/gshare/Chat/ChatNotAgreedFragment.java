package com.example.gshare.Chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gshare.ChatAdapter;
import com.example.gshare.R;

public class ChatNotAgreedFragment extends Fragment {
    ListView listView;
    ChatAdapter chatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_chat_not_agreed, container, false);
        //listView = (ListView) view.findViewById()
return view;
    }
}
