package com.example.gshare.Notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.Sort.Sort;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.R;

public class NoticeViewBorrowing extends Fragment implements View.OnClickListener {

    Notice notice;
    int noticeId;

    EditText name;
    EditText days;
    EditText note;
    Button editNoticeButton;
    ImageButton backButton;
    ImageView categoryImageView;

    String userName;
    String password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_notice_view_borrowing, container, false);

       Bundle bundle = getArguments();
       userName = bundle.getString("userName");
       password = bundle.getString("password");
       noticeId = bundle.getInt("notice_id");

       notice = new Notice("bad",5,"dasdfa",0, new User( "Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100 ),
               new LocationG());//DBHelper.getNotice(noticeId);

        //Initialize components
        categoryImageView = view.findViewById(R.id.categoryImageView);
        name = view.findViewById(R.id.itemNameinput);
        days = view.findViewById(R.id.daysinput);
       note = view.findViewById(R.id.noteinput);
       editNoticeButton = view.findViewById(R.id.EditNoticeBorrowing);
       backButton = view.findViewById(R.id.backButton);

       editNoticeButton.setOnClickListener(this);
       backButton.setOnClickListener(this);

        //Set the category picture according to the category of the notice
        if(notice.getCategory() == Sort.TRANSPORT){
            categoryImageView.setImageResource(R.drawable.ic_car_black_24dp);
        }
        else if (notice.getCategory() == Sort.EDUCATION){
            categoryImageView.setImageResource(R.drawable.ic_school_black_24dp);
        }
        else if(notice.getCategory() == Sort.ELECTRONIC)
            categoryImageView.setImageResource(R.drawable.ic_laptop_black_24dp);
        else if(notice.getCategory() == Sort.LECTURE_NOTES)
            categoryImageView.setImageResource(R.drawable.ic_photo_camera_black_24dp);
        else if(notice.getCategory() == Sort.STATIONARY)
            categoryImageView.setImageResource(R.drawable.ic_scissors_black_24dp);
        else if(notice.getCategory() == Sort.PET)
            categoryImageView.setImageResource(R.drawable.ic_pets_black_24dp);
        else if(notice.getCategory() == Sort.BOOKS)
            categoryImageView.setImageResource(R.drawable.bookiconforhome);
        else if(notice.getCategory() == Sort.OTHER)
            categoryImageView.setImageResource(R.drawable.img_350691);

       name.setText(notice.getName());
       days.setText(notice.getDay()+"");
       note.setText(notice.getNote());

       return view;

    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.EditNoticeBorrowing:
                NoticeEditBorrowingFragment noticeEditBorrowingFragment = new NoticeEditBorrowingFragment();
                noticeEditBorrowingFragment.setArguments( getArguments() );
                FragmentTransaction fragmentTransaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.mynotices_placeholder, noticeEditBorrowingFragment);
                fragmentTransaction1.commit();
                break;
            case R.id.backButton:
                MyNoticesFragment myNoticesFragment = new MyNoticesFragment();
                myNoticesFragment.setArguments( getArguments() );
                FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.mynotices_placeholder,myNoticesFragment);
                fragmentTransaction2.commit();
                break;

        }
    }

}
