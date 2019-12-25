package com.example.gshare.Notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.R;

public class NoticeEditBorrowingFragment extends Fragment implements View.OnClickListener {

    Notice notice;
    int noticeId;

    EditText name;
    EditText day;
    EditText note;
    Button applyButton;
    ImageButton backButton;

    String userName;
    String password;

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
        View view = inflater.inflate(R.layout.fragment_notice_edit_borrowing, container, false);

        Bundle bundle = getArguments();

        noticeId = bundle.getInt("notice_id");
        userName = bundle.getString("userName");
        password = bundle.getString("password");

        notice =  new Notice("bad",5,"dasdfa",0, new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 )
                ,new LocationG());//DBHelper.getNotice(noticeId);

        name = view.findViewById(R.id.itemNameinput);
        day = view.findViewById(R.id.daysinput);
        note = view.findViewById(R.id.noteinput);
        applyButton = view.findViewById(R.id.ApplyEditNoticeBorrowing);
        backButton = view.findViewById(R.id.backButton);

        name.setText(notice.getName());
        day.setText(notice.getDay());
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

        return view;
    }

    @Override
    public void onClick(View v) {

        notice.setDay( Integer.parseInt( day.getText().toString() ) );
        notice.setName( name.getText().toString() );
        notice.setNote( note.getText().toString() );
        //DBHelper.updateNotice(notice);

        Bundle bundle = getArguments();
        NoticeViewBorrowing noticeViewBorrowing = new NoticeViewBorrowing();
        noticeViewBorrowing.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mynotices_placeholder, noticeViewBorrowing);
        fragmentTransaction.commit();
    }
}
