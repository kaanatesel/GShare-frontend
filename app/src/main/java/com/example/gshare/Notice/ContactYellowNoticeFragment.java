package com.example.gshare.Notice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gshare.Chat.ChatNotAgreedFragment;
import com.example.gshare.ChatActivity;
import com.example.gshare.HomePageFragment;
import com.example.gshare.ModelClasses.ChatModel.Chat;
import com.example.gshare.ModelClasses.ChatModel.ChatCollection;
import com.example.gshare.ModelClasses.Location.LocationG;
import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.ModelClasses.Sort.Sort;
import com.example.gshare.ModelClasses.User.User;
import com.example.gshare.Profile.ProfilePublicFragment;
import com.example.gshare.R;

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
    ImageView categoryImageView;

    Button goToProfile;

    String email;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_noticecontactyellow, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            noticeId = bundle.getInt("notice_id");
            email = bundle.getString("email");
        }
        notice = new Notice("bad", 5, "dasdfa", 0, new User("Cagri Eren", "ejderado", "dfasfd", "ejderado99@gmail.com", 100),
                100, new LocationG());//DBHelper.getNotice(noticeId);

        //Initialize components
        categoryImageView = view.findViewById(R.id.categoryImageView);
        g = view.findViewById(R.id.noticeg);
        title = view.findViewById(R.id.noticeTitle);
        days = view.findViewById(R.id.noticedays);
        note = view.findViewById(R.id.noticenote);
        contactButton = view.findViewById(R.id.contactButton);
        backButton = view.findViewById(R.id.backButton);
        goToProfile = view.findViewById(R.id.userButton);

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
                getActivity().setContentView(R.layout.blank_layout);
                HomePageFragment homePageFragment = new HomePageFragment();
                homePageFragment.setArguments(bundle2);
                FragmentTransaction fragmentManagerForHomePage = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentManagerForHomePage.replace(R.id.main_layout, homePageFragment);
                fragmentManagerForHomePage.commit();
                break;
            case R.id.userButton:
                Bundle bundle3;
                bundle3 = getArguments();
                bundle3.putString("userEmail",notice.getNoticeOwner().getEmail());
                getActivity().setContentView(R.layout.blank_layout);
                ProfilePublicFragment profilePublicFragment = new ProfilePublicFragment();
                profilePublicFragment.setArguments(bundle3);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.main_layout, profilePublicFragment);
                fragmentTransaction.commit();
                break;

        }
    }

    /*ContactSmallProfileFragment fragment = new ContactSmallProfileFragment();
onAttach(c);
            FragmentTransaction fragmentTransactionForSmallProfile = mContext.getSupportFragmentManager().beginTransaction();
            fragmentTransactionForSmallProfile.replace(R.id.UserButton,fragment);
            fragmentTransactionForSmallProfile.commit();*/
}