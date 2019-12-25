package com.example.gshare.Profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Chat.ChatFragment;
import com.example.gshare.DBHelper;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.R;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    User user;
    ImageButton editButton;
    TextView nameAndSurname;
    TextView username;

    Notice notices;

    String email;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            email = bundle.getString("email");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container,false);

       //user = DBHelper.getUser(email);

        editButton = (ImageButton) view.findViewById(R.id.editProfileButton);
        nameAndSurname =(TextView) view.findViewById(R.id.nameTextView);
        username = (TextView) view.findViewById(R.id.usernameTextView);

        //username.setText(user.getUserName());
        //nameAndSurname.setText(user.getNameAndSurname());

        editButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = getArguments();
        ProfileEditFragment profileEditFragment = new ProfileEditFragment();
        profileEditFragment.setArguments(bundle);
        profileEditFragment.show( getFragmentManager(), "EditPopUp");

    }
}
