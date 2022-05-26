package team2.mobileapp.gplx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import team2.mobileapp.gplx.models.NoticeBoard;
import team2.mobileapp.gplx.service.NoticeBoardService;

public class NoticeBoardActivity extends AppCompatActivity {
    public NoticeBoardAdapter noticeBoardAdapter ;
    private List<NoticeBoardItem> names = new ArrayList<>();;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);
        listView = findViewById(R.id.lvItems);
        NoticeBoardItem noticeBoard = new NoticeBoardItem();
        noticeBoard.setBoardName("Bien Bao");
        for (int i = 0; i < 10; i++) {
            names.add(noticeBoard);
        }

        noticeBoardAdapter = new NoticeBoardAdapter(NoticeBoardActivity.this, 1,names );

        listView.setAdapter(noticeBoardAdapter);

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
                NoticeBoardItem noticeBoard = new NoticeBoardItem();

                for (int i = 0; i < boards.size(); i++) {
                    noticeBoard.setBoardCode(boards.get(i).getBoardCode());
                    noticeBoard.setBoardName(boards.get(i).getBoardName());
                    noticeBoard.setBoardDescription(boards.get(i).getBoardDescription());
                    noticeBoard.setBoardPhoto(boards.get(i).getPhoto());
                    names.add(noticeBoard);
                }

                noticeBoardAdapter = new NoticeBoardAdapter(NoticeBoardActivity.this, 1,names );

                listView.setAdapter(noticeBoardAdapter);
            }

        });
    }
}