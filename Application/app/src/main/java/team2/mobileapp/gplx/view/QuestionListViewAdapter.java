package team2.mobileapp.gplx.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.models.Test;
import team2.mobileapp.gplx.Volley.model.NoticeBoard;
import team2.mobileapp.gplx.Volley.model.QuestionViewList;

import android.os.Bundle;

class QuestionListViewAdapter extends ArrayAdapter<Test> {
    Context context;

    // new
    ArrayList<Test> arrayList;
    int layoutResource;

    //
    public QuestionListViewAdapter(Context context, int resource, ArrayList<Test>
            objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
        this.layoutResource = resource;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView =
                LayoutInflater.from(context).inflate(R.layout.question_view_list_item, null,
                        false);

//        NoticeBoardItem noticeBoardItem = getItem(position);
//        TextView tvBoardCode = (TextView)
//                convertView.findViewById(R.id.tv_board_code);
//        tvBoardCode.setText(arrayList.get(position).getId());
//        TextView tvBoardName = (TextView)
//                convertView.findViewById(R.id.tv_board_name);
//        tvBoardName.setText(arrayList.get(position).getLicenseId());
//        String uri = arrayList.get(position).getPhoto().substring(0, arrayList.get(position).getPhoto().length() - 4);
//        int imageResource = context.getResources().getIdentifier(uri, "drawable", context.getPackageName());
//        Drawable res = context.getResources().getDrawable(imageResource);
//        ImageView ivBoardPhoto = (ImageView)
//                convertView.findViewById(R.id.iv_board);
//        ivBoardPhoto.setImageDrawable(res);
// Get item
        return convertView;
    }
}