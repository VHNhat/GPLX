package team2.mobileapp.gplx;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class NoticeBoardItem {

    private String boardCode;
    private String boardName;
    private String boardDescription;
    private String boardPhoto;
    public NoticeBoardItem( String boardName) {
        this.boardName = boardName;
    }
    public NoticeBoardItem() {
        this.boardCode="No code";
        this.boardName="No name";
        this.boardDescription="No description";
        this.boardPhoto="";
    }
    public NoticeBoardItem(String boardCode, String boardName, String boardDescription, String boardPhoto) {
        this.boardCode=boardCode;
        this.boardName=boardName;
        this.boardDescription=boardDescription;
        this.boardPhoto=boardPhoto;
    }
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getBoardDescription() {
        return boardDescription;
    }

    public void setBoardDescription(String boardDescription) {
        this.boardDescription = boardDescription;
    }

    public String getBoardCode() {
        return boardCode;
    }

    public void setBoardCode(String boardCode) {
        this.boardCode = boardCode;
    }

    public String getBoardPhoto() {
        return boardPhoto;
    }

    public void setBoardPhoto(String boardPhoto) {
        this.boardPhoto = boardPhoto;
    }
}

public  class NoticeBoardAdapter extends ArrayAdapter<NoticeBoardItem> {

    private Activity context;
    public NoticeBoardAdapter(Activity context, int layoutID, List<NoticeBoardItem>
            objects) {
        super(context, layoutID, objects);
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView =
                LayoutInflater.from(context).inflate(R.layout.listview_notice_board_item, null,
                        false);

        NoticeBoardItem noticeBoardItem = getItem(position);
        TextView tvBoardCode = (TextView)
                convertView.findViewById(R.id.tv_board_code);
        tvBoardCode.setText(noticeBoardItem.getBoardCode());
        TextView tvBoardName = (TextView)
                convertView.findViewById(R.id.tv_board_code);
        tvBoardName.setText(noticeBoardItem.getBoardName());


        String uri = "@drawable/"+ noticeBoardItem.getBoardPhoto();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        ImageView ivBoardPhoto = (ImageView)
                convertView.findViewById(R.id.iv_board);
        ivBoardPhoto.setImageDrawable(res);
// Get item
        return convertView;
    }
}