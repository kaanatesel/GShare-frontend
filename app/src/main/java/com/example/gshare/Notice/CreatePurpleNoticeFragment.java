package com.example.gshare.Notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.CallUserByEmail;
import com.example.gshare.DBHelper;
import com.example.gshare.HomePageActivity;
import com.example.gshare.HomePageFragment;
import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.Sort.Sort;
import com.example.gshare.NoticePusher;
import com.example.gshare.R;

public class CreatePurpleNoticeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    EditText itemName;
    EditText day;
    EditText note;
    Button addNotice;
    ImageButton back;

    int category;

    //Category Buttons
    ImageButton category1;
    ImageButton category2;
    ImageButton category3;
    ImageButton category4;
    ImageButton category5;
    ImageButton category6;
    ImageButton category7;
    ImageButton category8;

    String email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_createnoticepurple, container,false);
        itemName = view.findViewById(R.id.itemNameinput);
        day = view.findViewById(R.id.daysinput);
        note = view.findViewById(R.id.noteinput);

        addNotice = view.findViewById(R.id.PublishButtonPurple);
        back = view.findViewById(R.id.backButton);
        addNotice.setOnClickListener(this);
        back.setOnClickListener(this);

        email = getArguments().getString("email");

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
    public void onClick(View v) {//FIX AT FINAL
        Bundle bundle = getArguments();
        Notice notice = null;
        if ( v.getId() == R.id.PublishButtonPurple ) {
           try {
               notice = new Notice(itemName.getText().toString(), Integer.parseInt(day.getText().toString()), note.getText().toString(), category,
                       CallUserByEmail.call( email), new LocationG());
            }
           catch(Exception e){
                Toast.makeText(getActivity(),"Wrong values please try again", Toast.LENGTH_LONG).show();
           }
           try{
               NoticePusher.push(notice);
           }
           catch (Exception e ){
              e.printStackTrace();
           }
        }

        HomePageFragment homePageFragment = new HomePageFragment();
        getActivity().setContentView(R.layout.blank_layout);
        homePageFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_layout,homePageFragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT);
    }

}
