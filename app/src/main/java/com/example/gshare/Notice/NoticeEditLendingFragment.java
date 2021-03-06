package com.example.gshare.Notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.Sort.Sort;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.R;

import java.util.ArrayList;
import java.util.List;

public class NoticeEditLendingFragment extends Fragment implements View.OnClickListener{

    Notice notice;
    int noticeId;
    int category;

    EditText name;
    EditText day;
    EditText g;
    EditText note;
    Button applyButton;
    ImageButton backButton;
    Spinner spinner;

    String email;

    //Category Buttons
    ImageButton category1;
    ImageButton category2;
    ImageButton category3;
    ImageButton category4;
    ImageButton category5;
    ImageButton category6;
    ImageButton category7;
    ImageButton category8;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice_edit_lending, container, false);

        Bundle bundle = getArguments();

        noticeId = bundle.getInt("notice_id");
        email = bundle.getString("email");

        notice =  new Notice("bad",5,"dasdfa",0, new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 ),
                100,new LocationG());//DBHelper.getNotice(noticeId);

        name = view.findViewById(R.id.itemNameinput);
        day = view.findViewById(R.id.daysinput);
        g = view.findViewById(R.id.ginput);
        note = view.findViewById(R.id.noteinput);
        applyButton = view.findViewById(R.id.ApplyEditNoticeLending);
        backButton = view.findViewById(R.id.backButton);

        name.setText(notice.getName());
        day.setText(notice.getDay() + "");
        g.setText(notice.getG()+"");
        note.setText(notice.getNote());
        applyButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        //Category buttons initialized
        category1 = (ImageButton) view.findViewById(R.id.transportButton);
        category2 = (ImageButton) view.findViewById(R.id.schoolButton);
        category3 = (ImageButton) view.findViewById(R.id.laptopButton);
        category4 = (ImageButton) view.findViewById(R.id.cameraButton);
        category5 = (ImageButton) view.findViewById(R.id.stationaryButton);
        category6 = (ImageButton) view.findViewById(R.id.petsButton);
        category7 = (ImageButton) view.findViewById(R.id.booksButton);
        category8 = (ImageButton) view.findViewById(R.id.othersButton);

        //Initialize the catgory as OTHER in case the user doesnt choose.
        category = Sort.OTHER;

        //Set click listeners for the category buttons
        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = Sort.TRANSPORT;
            }
        });
        category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = Sort.EDUCATION;
            }
        });
        category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = Sort.ELECTRONIC;
            }
        });
        category4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = Sort.LECTURE_NOTES;
            }
        });
        category5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = Sort.STATIONARY;
            }
        });
        category6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = Sort.PET;
            }
        });
        category7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = Sort.BOOKS;
            }
        });
        category8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = Sort.OTHER;
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()== R.id.ApplyEditNoticeLending) {

            notice.setForDatabaseG(Integer.parseInt(g.getText().toString()));//Only here usage is allowed
            notice.setDay(Integer.parseInt(day.getText().toString()));
            notice.setName(name.getText().toString());
            notice.setNote(note.getText().toString());
            notice.setCategory(category);
            // DBHelper.updateNotice(notice);
        }

        Bundle bundle = getArguments();
        getActivity().setContentView(R.layout.fullyblanklayout);

        NoticeViewLending noticeViewLending = new NoticeViewLending();
        noticeViewLending.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_biglayout, noticeViewLending);
        fragmentTransaction.commit();
    }
}
