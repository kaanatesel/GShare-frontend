import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gshare.ModelClasses.NoticeModel.Notice;
import com.example.gshare.Notice.ListViewAdapter;
import com.example.gshare.Notice.ProductHomeListDisplayModel;
import com.example.gshare.R;

import java.util.ArrayList;

public class ProfileTransactionAdapter extends ArrayAdapter<Notice> {
    Context c;
    ArrayList<Notice> transactedNotices;
    public ProfileTransactionAdapter(Context con,ArrayList<Notice> notices){
        super(con, R.layout.,notices);
        c = con;
        transactedNotices = notices;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return row;
    }

}
