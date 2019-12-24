package com.example.gshare;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;


import com.example.gshare.Notice.ContactYellowNoticeFragment;
import com.example.gshare.Notice.NoticeTry;
import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<NoticeTry> {

    Context context;
    ArrayList<NoticeTry> myNotices;

    private ArrayList<NoticeTry> notices;
    public ListViewAdapter(@NonNull Context c,@NonNull ArrayList<NoticeTry> notices){
        super(c, R.layout.noticelayout,notices);
        this.context = c;
        this.myNotices = notices;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.noticelayout,parent,false);
        TextView noticeNameView = row.findViewById(R.id.textView4);
        TextView noticeDayView = row.findViewById(R.id.textView6);
        TextView noticeGView = row.findViewById(R.id.textView5);
        noticeNameView.setText(myNotices.get(position).getName());
        noticeDayView.setText(myNotices.get(position).getDay());
        noticeGView.setText(myNotices.get(position).getG());
        return row;
    }
    public ListViewAdapter update(ArrayList<NoticeTry> noticeTries){
        ListViewAdapter adapterNew = new ListViewAdapter(this.context,noticeTries);
        return adapterNew;
    }

        }











