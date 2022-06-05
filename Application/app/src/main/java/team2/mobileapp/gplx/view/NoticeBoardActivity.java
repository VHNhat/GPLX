package team2.mobileapp.gplx.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Volley.model.NoticeBoard;
import team2.mobileapp.gplx.Volley.service.NoticeBoardService;

public class NoticeBoardActivity extends AppCompatActivity {
    private NoticeBoardAdapter noticeBoardAdapter;
    private ArrayList<NoticeBoard> listNoticeBoard = new ArrayList<>();
    private ListView listView;
    private TextView titleActivity,titleBoard;
    private TextView etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setContentView(R.layout.activity_notice_board);
        String title = getIntent().getStringExtra("TITLE");

        listView = findViewById(R.id.lvItems);
        titleActivity = findViewById(R.id.tv_title_activity_app);
        titleBoard= findViewById(R.id.title_layouts);
        etSearch = findViewById(R.id.et_search);
        titleActivity.setText(title);
        titleBoard.setText("Biển chỉ dẫn");
        SearchListener();

        final NoticeBoardService noticeBoardService = new NoticeBoardService(this);
        ShowBoard(noticeBoardService);
        try {
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    NoticeBoard item = (NoticeBoard) listView.getItemAtPosition(position);
                    Intent intent = new Intent(NoticeBoardActivity.this, DetailsNoticeBoardActivity.class);
                    intent.putExtra("ID", item.getId());
                    startActivity(intent);
                }
            });
        } catch (Exception ex) {
            Log.d("Error: ", ex.getMessage());
        }
    }

    private void SearchListener() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty()) {
                    noticeBoardAdapter = new NoticeBoardAdapter(NoticeBoardActivity.this, 1, listNoticeBoard);
                }
                else {
                    ArrayList<NoticeBoard> listNoticeBoardNew = new ArrayList<>();
                    for (NoticeBoard item : listNoticeBoard) {
                        String compare =item.getBoardCode()+" "+item.getBoardDescription();
                        if (decompose(compare).toLowerCase().contains(decompose(charSequence.toString()).toLowerCase())) {
                            listNoticeBoardNew.add(item);
                        }
                    }
                    noticeBoardAdapter = new NoticeBoardAdapter(NoticeBoardActivity.this, 1, listNoticeBoardNew);
                }
                listView.setAdapter(noticeBoardAdapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    public static String decompose(String s) {
        return java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+","");
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
                    listNoticeBoard.add(noticeBoard);
                }
                noticeBoardAdapter = new NoticeBoardAdapter(NoticeBoardActivity.this, 1, listNoticeBoard);

                listView.setAdapter(noticeBoardAdapter);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }

    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        return super.moveTaskToBack(nonRoot);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}