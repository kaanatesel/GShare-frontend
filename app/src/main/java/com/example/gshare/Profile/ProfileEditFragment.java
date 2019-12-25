package com.example.gshare.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.gshare.DBHelper;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.R;

public class ProfileEditFragment extends DialogFragment implements View.OnClickListener {

    EditText editName;
    Button applyButton;
    User user;

    String email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        applyButton = view.findViewById(R.id.changeNameButton);
        editName = view.findViewById(R.id.changeNameEditText);
        email = getArguments().getString("email");
        user = DBHelper.getUser(email);
        applyButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.changeNameButton:
                user.setNameAndSurname(editName.getText().toString());
                getDialog().dismiss();
                break;
        }

    }

}
