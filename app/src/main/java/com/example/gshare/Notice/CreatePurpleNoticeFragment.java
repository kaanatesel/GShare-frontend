package com.example.gshare.Notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.HomePageFragment;
import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.R;

public class CreatePurpleNoticeFragment extends Fragment implements View.OnClickListener {
    EditText itemName;
    EditText day;
    EditText note;
    Button addNotice;
    ImageButton back;

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

        return view;

    }


    @Override
    public void onClick(View v) {//FIX AT FINAL
        Bundle bundle = getArguments();
        /*
        if ( v.getId() == R.id.PublishButton ) {
           try {
               Notice notice = new Notice(itemName.getText().toString(), Integer.parseInt(day.getText().toString()), note.getText().toString(), 0,
                       DBHelper.getUser( getArguments().getString("userName"), getArguments().getString("password")), new LocationG());
            }
           catch(Exception e){
                Toast.makeText(getActivity(),"Wrong values please try again", Toast.LENGTH_LONG).show();
           }
           try{
               DBHelper.addNotice(notice);
           }
           catch (Exception e ){
              e.printStackTrace();
           }
        }
        */
        HomePageFragment homePageFragment = new HomePageFragment();
        homePageFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_layout,homePageFragment);
        fragmentTransaction.commit();

    }

}
