package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Volley.model.QuestionViewList;

public class QuestionViewListActivity extends AppCompatActivity {
    public QuestionListViewAdapter questionListViewAdapter ;
    private ArrayList<QuestionViewList> questions = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view_list);
        ListView listView = (ListView) findViewById(R.id.lvAllQuestion);

        QuestionViewList h = new QuestionViewList();
        h.setQuery("Hạng A1");
        for (int i = 0; i < 10; i++) {
            questions.add(h);
        }

        questionListViewAdapter = new QuestionListViewAdapter(QuestionViewListActivity.this, 1,questions );

        listView.setAdapter(questionListViewAdapter);
    }
}