package com.example.gshare.Notice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Chat.ChatFragment;
import com.example.gshare.R;

public class ContactYellowNoticeFragment extends Fragment {

    private String name = "NoticeTitle";
  private String day = "NoticeDay";
  private String g = "NoticeG";
    @Override
  public void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      Bundle args = getArguments();
      if(args != null){
          name = getArguments().getString("NoticeName");
          day = getArguments().getString("NoticeDay");
          g = getArguments().getString("NoticeG");
      }

  }
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticecontactyellow, container, false);

        TextView nameView = (TextView) view.findViewById(R.id.noticeTitle);
            TextView dayView = (TextView) view.findViewById(R.id.noticedays);
            TextView gView = (TextView) view.findViewById(R.id.noticeg);
            nameView.setText(name);
            dayView.setText(day);
            gView.setText(g);
       /*Button contactButton = (Button) getView().findViewById(R.id.contactButton);
       contactButton.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v){
               try {
                   ChatFragment chatFragment = new ChatFragment();
                   FragmentTransaction chatTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                   chatTransaction.replace(R.id.main_layout, chatFragment);
                   chatTransaction.commit();
               }catch(NullPointerException e){

               }
           }

       });*/
        return view;
    }


    }




