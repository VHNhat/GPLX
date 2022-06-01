package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.TestCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.QuestionDetailsController;
import team2.mobileapp.gplx.Retrofit.dto.QuestionDetails;

public class QuestionViewListActivity extends AppCompatActivity implements TestCallBackListener {
    private QuestionDetailsController questionDetailsController;
    public QuestionListViewAdapter questionListViewAdapter ;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view_list);
        listView = (ListView) findViewById(R.id.lvAllQuestion);
        questionDetailsController = new QuestionDetailsController(this);
        questionDetailsController.startFetching("a1");
    }

    @Override
    public void onFetchProgress(ArrayList<QuestionDetails> trafficSigns) {
        if(!trafficSigns.isEmpty())
        {

            questionListViewAdapter= new QuestionListViewAdapter(QuestionViewListActivity.this,R.layout.question_view_list_item,trafficSigns);
            listView.setAdapter(questionListViewAdapter);
        }

    }

    @Override
    public void onFetchComplete(String message) {
        Log.d("onFetchComplete", message);
    }
}