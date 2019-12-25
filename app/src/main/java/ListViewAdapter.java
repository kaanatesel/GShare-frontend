package com.example.gshare;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gshare.Notice.ProductHomeListDisplayModel;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<ProductHomeListDisplayModel> {

    Context context;
    ArrayList<ProductHomeListDisplayModel> myNotices;

    public ListViewAdapter(@NonNull Context c,@NonNull ArrayList<ProductHomeListDisplayModel> notices){
        super(c, R.layout.noticelayout,notices);
        this.context = c;
        this.myNotices = notices;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = layoutInflater.inflate(R.layout.noticelayout,parent,false);
        TextView  productName = row.findViewById(R.id.productName);
        TextView  productDesription = row.findViewById(R.id.productDesription);
        TextView  gamouth = row.findViewById(R.id.gamouth);

        productName.setText(myNotices.get(position).getName());
        productDesription.setText(myNotices.get(position).getDescription()+ "");
        gamouth.setText(myNotices.get(position).getG() + "");
        return row;
    }
    public ListViewAdapter update(ArrayList<ProductHomeListDisplayModel> noticeTries){
        ListViewAdapter adapterNew = new ListViewAdapter(this.context,noticeTries);
        return adapterNew;
    }
}











