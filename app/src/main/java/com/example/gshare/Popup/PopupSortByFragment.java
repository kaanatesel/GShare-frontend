package com.example.gshare.Popup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Chat.ChatAgreedFragment;
import com.example.gshare.HomePageFragment;
import com.example.gshare.Notice.CreateYellowNoticeFragment;
import com.example.gshare.R;

public class PopupSortByFragment extends DialogFragment implements View.OnClickListener {
    CheckBox gCheckBox;
    CheckBox newestFirstCheckBox;
    CheckBox alphabeticalCheckBox;
    EditText minGText;
    EditText maxGText;
    Button applyButton;

    String userName;
    String password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popup_sort_by, container, false);

        userName = getArguments().getString("userName" );
        password = getArguments().getString("password");

        gCheckBox = view.findViewById(R.id.gIntervalCheckBox);
        newestFirstCheckBox = view.findViewById(R.id.newestFirstCheckBox);
        alphabeticalCheckBox = view.findViewById(R.id.alphabeticallyCheckBox);
        minGText = view.findViewById(R.id.minGEditText);
        maxGText = view.findViewById(R.id.maxGEditText);
        applyButton = view.findViewById(R.id.applySortButton);

        applyButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ){
            case R.id.applySortButton:
                int min = 0;
                int max = 0;
                boolean g = false;
                boolean newest = false;
                boolean alphabetical = false;

                if( gCheckBox.isChecked() ){
                    try {
                         min = Integer.parseInt(minGText.getText().toString());
                         max = Integer.parseInt(maxGText.getText().toString());
                         g = true;
                    }
                    catch( Exception e ){
                        Toast.makeText(getActivity(),"Wrong values please try again", Toast.LENGTH_SHORT).show();
                        minGText.setText("0");
                        maxGText.setText("0");
                    }

                }
                if( newestFirstCheckBox.isChecked() ){
                    newest = true;
                }
                if( alphabeticalCheckBox.isChecked() ){
                    alphabetical = true;
                }

                Bundle bundle = new Bundle();
                bundle.putBoolean("G",g);
                bundle.putBoolean("NEWEST",newest);
                bundle.putBoolean("ACCEPTED",true);
                bundle.putBoolean("ALPHABETICAL",alphabetical);
                bundle.putInt("MIN",min);
                bundle.putInt("MAX",max);
                bundle.putString("userName",userName);
                bundle.putString("password",password);

                HomePageFragment homePageFragment = new HomePageFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                homePageFragment.setArguments(bundle);
                fragmentTransaction.replace( R.id.homepage_placeholder, homePageFragment );
                fragmentTransaction.commit();
                getDialog().dismiss();
        }
    }
}
