package team2.mobileapp.gplx.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import team2.mobileapp.gplx.R;

class HistoryItem {

    private String CategoryName;
    public HistoryItem( String CategoryName) {
        this.CategoryName = CategoryName;

    }
    public HistoryItem() {
        this.CategoryName="No name";
    }

    public String getCategoryName() {
        return this.CategoryName;
    }
    public void setCategoryName(String fullName) {
        this.CategoryName = fullName;
    }
}

public  class HistoryAdapter extends ArrayAdapter<HistoryItem> {

    private Activity context;
    public HistoryAdapter(Activity context, int layoutID, List<HistoryItem>
            objects) {
        super(context, layoutID, objects);
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView =
                LayoutInflater.from(context).inflate(R.layout.listview_item, null,
                        false);

        HistoryItem historyItem = getItem(position);
        TextView tvFullName = (TextView)
                convertView.findViewById(R.id.tv_person_name);
        tvFullName.setText(historyItem.getCategoryName());
// Get item
        return convertView;
    }
}