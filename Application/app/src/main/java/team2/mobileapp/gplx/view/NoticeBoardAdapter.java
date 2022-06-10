package team2.mobileapp.gplx.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.models.NoticeBoard;

//class NoticeBoardItem {
//
//    private String boardCode;
//    private String boardName;
//    private String boardDescription;
//    private String boardPhoto;
//    public NoticeBoardItem( String boardName) {
//        this.boardName = boardName;
//    }
//    public NoticeBoardItem() {
//        this.boardCode="No code";
//        this.boardName="No name";
//        this.boardDescription="No description";
//        this.boardPhoto="";
//    }
//    public NoticeBoardItem(String boardCode, String boardName, String boardDescription, String boardPhoto) {
//        this.boardCode=boardCode;
//        this.boardName=boardName;
//        this.boardDescription=boardDescription;
//        this.boardPhoto=boardPhoto;
//    }
//    public void setBoardName(String boardName) {
//        this.boardName = boardName;
//    }
//
//    public String getBoardName() {
//        return boardName;
//    }
//
//    public String getBoardDescription() {
//        return boardDescription;
//    }
//
//    public void setBoardDescription(String boardDescription) {
//        this.boardDescription = boardDescription;
//    }
//
//    public String getBoardCode() {
//        return boardCode;
//    }
//
//    public void setBoardCode(String boardCode) {
//        this.boardCode = boardCode;
//    }
//
//    public String getBoardPhoto() {
//        return boardPhoto;
//    }
//
//    public void setBoardPhoto(String boardPhoto) {
//        this.boardPhoto = boardPhoto;
//    }
//
//    @Override
//    public String toString() {
//        return "NoticeBoardItem{" +
//                "boardCode='" + boardCode + '\'' +
//                ", boardName='" + boardName + '\'' +
//                ", boardDescription='" + boardDescription + '\'' +
//                ", boardPhoto='" + boardPhoto + '\'' +
//                '}';
//    }
//}

public  class NoticeBoardAdapter extends ArrayAdapter<NoticeBoard> {

    Context context;

    // new
    ArrayList<NoticeBoard> arrayList;
    int layoutResource;

    //
    public NoticeBoardAdapter(Context context, int resource, ArrayList<NoticeBoard>
            objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrayList = objects;
        this.layoutResource = resource;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView =
                LayoutInflater.from(context).inflate(R.layout.listview_notice_board_item, null,
                        false);

//        NoticeBoardItem noticeBoardItem = getItem(position);
        TextView tvBoardCode = (TextView)
                convertView.findViewById(R.id.tv_board_code);
        tvBoardCode.setText(arrayList.get(position).getBoardCode());
        TextView tvBoardName = (TextView)
                convertView.findViewById(R.id.tv_board_name);
        tvBoardName.setText(arrayList.get(position).getBoardName());
        Log.i("Position", String.valueOf(position));
        Log.i("Code", arrayList.get(position).getBoardCode());
        Log.i("Name", arrayList.get(position).getBoardName());
        Log.i("Description", arrayList.get(position).getBoardDescription());
        Log.i("Photo",arrayList.get(position).getPhoto().substring(0, arrayList.get(position).getPhoto().length() - 4));
        String uri = arrayList.get(position).getPhoto().substring(0, arrayList.get(position).getPhoto().length() - 4);
        int imageResource = context.getResources().getIdentifier(uri, "drawable", context.getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        ImageView ivBoardPhoto = (ImageView)
                convertView.findViewById(R.id.iv_board);
        ivBoardPhoto.setImageDrawable(res);
// Get item
        return convertView;
    }
}