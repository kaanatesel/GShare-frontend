package com.example.gshare.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.R;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    User user;
    Button editButton;
    TextView nameAndSurname;
    TextView username;

    String userName;
    String password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container,false);

        Bundle bundle = getArguments();

        userName = bundle.getString("userName");
        password = bundle.getString("password");
        //user = DBHelper.getUser(userName,password);

        editButton = view.findViewById(R.id.editProfileButton);
        nameAndSurname = view.findViewById(R.id.nameTextView);
        username = view.findViewById(R.id.usernameTextView);

        username.setText(user.getUserName());
        nameAndSurname.setText(user.getNameAndSurname());

        editButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        ProfileEditFragment profileEditFragment = new ProfileEditFragment();
        profileEditFragment.show( getFragmentManager(), "EditPopUp");
    }
}
