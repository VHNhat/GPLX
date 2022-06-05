package team2.mobileapp.gplx.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import team2.mobileapp.gplx.R;

class GroupBoardingAdapter extends ArrayAdapter<GroupBoardingItem> {

    private Activity context;
    public GroupBoardingAdapter(Activity context, int layoutID, List<GroupBoardingItem>
            objects) {
        super(context, layoutID, objects);
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView =
                LayoutInflater.from(context).inflate(R.layout.listview_history_item, null,
                        false);

        GroupBoardingItem groupBoardingItem = getItem(position);
        TextView tvFullName = (TextView)
                convertView.findViewById(R.id.tv_person_name);
        tvFullName.setText(groupBoardingItem.getName());
// Get item
        return convertView;
    }
}