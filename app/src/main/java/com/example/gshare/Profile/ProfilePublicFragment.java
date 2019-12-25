package com.example.gshare.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Chat.ChatFragment;
import com.example.gshare.DBHelper;
import com.example.gshare.HomePageFragment;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.R;

public class ProfilePublicFragment extends Fragment {

    ImageButton backButton;
    TextView name;
    TextView username;
    User user;

    String email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_public, container, false);
        Bundle bundle = getArguments();

        email = bundle.getString("publicEmail");

        user = new User("Cagri Eren", "asdas", "asdfasdf", "sdfasdf", 100);//DBHelper.getUser();

        backButton.findViewById(R.id.backButton);
        name.findViewById(R.id.nameTextView);
        username.findViewById(R.id.usernameTextView);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
                ChatFragment chatFragment = new ChatFragment();
                chatFragment.setArguments(bundle);
                FragmentTransaction fragmentManagerForHomePage = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentManagerForHomePage.replace(R.id.main_layout, chatFragment);
                fragmentManagerForHomePage.commit();
            }
        });

        name.setText(user.getNameAndSurname());
        username.setText(user.getEmail());

        return view;
    }
}
