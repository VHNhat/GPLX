package team2.mobileapp.gplx.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.QuestionSetCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.QuestionSetController;
import team2.mobileapp.gplx.Retrofit.models.QuestionSet;
import team2.mobileapp.gplx.Retrofit.models.QuestionCountByType;
import team2.mobileapp.gplx.VariableGlobal.VariableGlobal;

import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReviewTestActivity extends AppCompatActivity implements QuestionSetCallBackListener {
    private  RelativeLayout allQuestion;
    private  RelativeLayout Concept;
    private  RelativeLayout Culture;
    private  RelativeLayout Figure;
    private QuestionSetController questionSetController;
    private RelativeLayout listTypeQuestion;
    private ListView groupTest;
    private GroupTestAdapter groupTestAdapter;
    private List<GroupTestItem> listGroupTests = new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_test);
//        listTypeQuestion = findViewById(R.id.list_type_question);
        groupTest = findViewById(R.id.lv_group_test);
        String license=VariableGlobal.typeCode;
        questionSetController = new QuestionSetController(this);
        questionSetController.GetquestionSize(license);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        setOnclickType();
    }
    public void setOnclickType(){
        groupTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GroupTestItem groupTestItem= (GroupTestItem) adapterView.getItemAtPosition(i);
                Log.d("groupTestItemID", groupTestItem.getType());
                Intent intent = new Intent(ReviewTestActivity.this,QuestionViewListActivity.class);
                intent.putExtra("TITLE_QUESTION_LIST",groupTestItem.getName());
                intent.putExtra("LICENSE_QUESTION_LIST",   VariableGlobal.typeCode);
                intent.putExtra("TYPE_QUESTION_LIST",groupTestItem.getType());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onFetchProgress(ArrayList<QuestionSet> questionSets) {

    }

    @Override
    public void onFetchProgressQuestionSize(ArrayList<QuestionCountByType> questionCountByTypes) {
        for (QuestionCountByType questionCountByType : questionCountByTypes ) {
            if(questionCountByType.getNum()>0)
            {
                GroupTestItem groupTestItem = new GroupTestItem();
                groupTestItem.setName(questionCountByType.getName());
                groupTestItem.setType(questionCountByType.getType());
                groupTestItem.setNum(questionCountByType.getNum());
                groupTestItem.setId(questionCountByType.getType()+questionCountByType.getNum());
                listGroupTests.add(groupTestItem);
            }


        }
        groupTestAdapter = new GroupTestAdapter(this, 1,listGroupTests );
        groupTest.setAdapter(groupTestAdapter);
    }

    @Override
    public void onFetchComplete(String message) {

    }



}