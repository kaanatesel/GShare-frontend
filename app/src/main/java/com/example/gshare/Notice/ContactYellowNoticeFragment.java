package com.example.gshare.Notice;

import android.content.Intent;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Chat.ChatFragment;
import com.example.gshare.Chat.ChatNotAgreedFragment;

import com.example.gshare.HomePageActivity;
import com.example.gshare.HomePageFragment;
import com.example.gshare.ModelClasses.ChatModel.Chat;
import com.example.gshare.ModelClasses.ChatModel.ChatCollection;
import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.User.User;

import com.example.gshare.Profile.ProfileFragment;
import com.example.gshare.Profile.ProfilePublicFragment;
import com.example.gshare.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Fix these when DBHElper added
 */
public class ContactYellowNoticeFragment extends Fragment implements View.OnClickListener {
    Notice notice;

    int noticeId;

    TextView title;
    TextView days;
    TextView note;
    TextView g;
    Button contactButton;
    ImageButton backButton;

    Button goToProfile;

    String email;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_noticecontactyellow, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            noticeId = bundle.getInt("notice_id");
            email = bundle.getString("email");
        }

        notice = new Notice("bad", 5, "dasdfa", 0, new User("Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100),
                100, new LocationG());//DBHelper.getNotice(noticeId);

        g = view.findViewById(R.id.noticeg);
        title = view.findViewById(R.id.noticeTitle);
        days = view.findViewById(R.id.noticedays);
        note = view.findViewById(R.id.noticenote);
        contactButton = view.findViewById(R.id.contactButton);
        backButton = view.findViewById(R.id.backButton);
        goToProfile = view.findViewById(R.id.userButton);

        title.setText(notice.getName());
        days.setText(notice.getDay() + "");
        note.setText(notice.getNote());
        g.setText(notice.getG() + "");
        goToProfile.setText( "Go to " + notice.getNoticeOwner().getNameAndSurname() + " 's profile");

        contactButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        goToProfile.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contactButton:
                User user = new User("Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100);//DBHelper.getUser( userName , password);
                Chat chat = new Chat(notice, notice.getNoticeOwner(), user);
                //ChatCollection chatCollection = DBHelper.getChatCollection( DBHelper.getUserId( user ) );

        /*
        if( !chatCollection.getAllChat().contains(chat)) {
            //chatCollection.addChat( chat );
            //DBHelper.addChat( chat );
            //DBHelper.updateChatCollection( chatCollection );
        }*/

        /*Intent intent = new Intent(getActivity(), ChatActivity.class );
        intent.putExtra("USERNAME" , user.getUserName() );
        intent.putExtra("PASSWORD", user.getPassword());
        startActivity(intent);*/
                Bundle bundle;
                bundle = getArguments();

                getActivity().setContentView(R.layout.fullyblanklayout);
                ChatNotAgreedFragment fragmentChatNotAgreed = new ChatNotAgreedFragment();
                fragmentChatNotAgreed.setArguments(bundle);
                FragmentTransaction fragmentManagerForNotAgreedChat = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentManagerForNotAgreedChat.replace(R.id.main_biglayout, fragmentChatNotAgreed);
                fragmentManagerForNotAgreedChat.commit();
                break;
            case R.id.backButton:
                Bundle bundle2;
                bundle2 = getArguments();
                Intent intentHome = new Intent(getContext(), HomePageActivity.class);
                intentHome.putExtras(bundle2);
                startActivity(intentHome);
                break;
            case R.id.userButton:
                Bundle bundle3 = new Bundle();
                getActivity().setContentView(R.layout.blank_layout);
                bundle3.putString("userEmail",notice.getNoticeOwner().getEmail());
                ProfilePublicFragment profilePublicFragment = new ProfilePublicFragment();
                profilePublicFragment.setArguments(bundle3);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, profilePublicFragment);
                fragmentTransaction.commit();

                BottomNavigationView bottomNav = getView().findViewById(R.id.nav_view);
                bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

                                                                  @Override
                                                                  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                                      Bundle bundle = new Bundle();
                                                                      bundle.putString("email", email);
                                                                      try {

                                                                          switch (item.getItemId()) {

                                                                              case R.id.navigation_home:


                                                                         HomePageFragment fragment1 = new HomePageFragment();
                                                                          fragment1.setArguments(bundle);
                                                                          FragmentTransaction fragmentTransaction1 = getActivity().getSupportFragmentManager().beginTransaction();
                                                                          fragmentTransaction1.replace(R.id.main_layout, fragment1);
                                                                          fragmentTransaction1.commit();
                                                                                  return true;

                                                                              case R.id.navigation_Map:

                                                                          HomePageFragment fragment2 = new HomePageFragment();
                                                                          fragment2.setArguments(bundle);
                                                                          FragmentTransaction fragmentTransaction2 = getActivity().getSupportFragmentManager().beginTransaction();
                                                                          fragmentTransaction2.replace(R.id.main_layout, fragment2);
                                                                          fragmentTransaction2.commit();
                                                                                  return true;

                                                                              case R.id.navigation_Notices:

                                                                          MyNoticesFragment fragment3 = new MyNoticesFragment();
                                                                          fragment3.setArguments(bundle);
                                                                          FragmentTransaction fragmentTransaction3 = getActivity().getSupportFragmentManager().beginTransaction();
                                                                          fragmentTransaction3.replace(R.id.main_layout, fragment3);
                                                                          fragmentTransaction3.commit();
                                                                                  return true;
                                                                              case R.id.navigation_Chat:

                                                                         ChatFragment fragment4 = new ChatFragment();
                                                                          fragment4.setArguments(bundle);
                                                                          FragmentTransaction fragmentTransaction4 = getActivity().getSupportFragmentManager().beginTransaction();
                                                                          fragmentTransaction4.replace(R.id.main_layout, fragment4);
                                                                          fragmentTransaction4.commit();
                                                                                  return true;


                                                                              case R.id.navigation_Profilet:

                                                                          ProfileFragment fragment5 = new ProfileFragment();
                                                                          fragment5.setArguments(bundle);
                                                                          FragmentTransaction fragmentTransaction5 = getActivity().getSupportFragmentManager().beginTransaction();
                                                                          fragmentTransaction5.replace(R.id.main_layout, fragment5);
                                                                          fragmentTransaction5.commit();
                                                                                  return true;
                                                                          }
                                                                          return false;

                                                                      } catch (NullPointerException e) {
                                                                          return false;
                                                                      }

                                                                  }

                                                              }
                );
                break;

        }
    }

    /*ContactSmallProfileFragment fragment = new ContactSmallProfileFragment();
onAttach(c);
            FragmentTransaction fragmentTransactionForSmallProfile = mContext.getSupportFragmentManager().beginTransaction();
            fragmentTransactionForSmallProfile.replace(R.id.UserButton,fragment);
            fragmentTransactionForSmallProfile.commit();*/
}