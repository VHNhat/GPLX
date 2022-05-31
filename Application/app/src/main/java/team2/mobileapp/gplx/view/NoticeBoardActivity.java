package team2.mobileapp.gplx.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.models.NoticeBoard;
import team2.mobileapp.gplx.service.NoticeBoardService;

public class NoticeBoardActivity extends AppCompatActivity {
    public NoticeBoardAdapter noticeBoardAdapter ;
    private ArrayList<NoticeBoard> names = new ArrayList<>();;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        listView = findViewById(R.id.lvItems);
//        NoticeBoardItem noticeBoard = new NoticeBoardItem();
//        noticeBoard.setBoardCode("Bien Bao");
//        noticeBoard.setBoardName("Bien Bao");
//        noticeBoard.setBoardDescription("Bien Bao");
//        noticeBoard.setBoardPhoto("i408.png");
//        for (int i = 0; i < 10; i++) {
//            names.add(noticeBoard);
//        }
//
//        noticeBoardAdapter = new NoticeBoardAdapter(NoticeBoardActivity.this, 1,names );
//
//        listView.setAdapter(noticeBoardAdapter);

        final NoticeBoardService noticeBoardService = new NoticeBoardService(this);

        ShowBoard(noticeBoardService);

    }
    private void ShowBoard(NoticeBoardService noticeBoardService) {
        noticeBoardService.GetAll(new NoticeBoardService.GetALLBoardCallBack() {
            @Override
            public void onError(String message) {
                Toast.makeText(NoticeBoardActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<NoticeBoard> boards) {

                for (int i = 0; i < boards.size(); i++) {
                    NoticeBoard noticeBoard = new NoticeBoard();
                    noticeBoard.setId(boards.get(i).getId());
                    noticeBoard.setType(boards.get(i).getType());
                    noticeBoard.setBoardCode(boards.get(i).getBoardCode());
                    noticeBoard.setBoardName(boards.get(i).getBoardName());
                    noticeBoard.setBoardDescription(boards.get(i).getBoardDescription());
                    noticeBoard.setPhoto(boards.get(i).getPhoto());
                    Log.i("NoticeBoard", noticeBoard.toString());
                    names.add(noticeBoard);
                }
                noticeBoardAdapter = new NoticeBoardAdapter(NoticeBoardActivity.this,1,names );

                listView.setAdapter(noticeBoardAdapter);
            }

        });
    }
}