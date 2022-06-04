package team2.mobileapp.gplx.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.QuestionSetCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.QuestionSetController;
import team2.mobileapp.gplx.Retrofit.models.QuestionSet;
import team2.mobileapp.gplx.Retrofit.models.QuestionSize;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewTestActivity extends AppCompatActivity implements QuestionSetCallBackListener {
    private  RelativeLayout allQuestion;
    private  RelativeLayout Concept;
    private  RelativeLayout Culture;
    private  RelativeLayout Figure;
    private QuestionSetController questionSetController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_test);
        String license="a1";
        String ConceptAPI="bb";
        String CultureAPI="vh";
        String FigureAPI="sh";
        questionSetController = new QuestionSetController(this);
        questionSetController.GetquestionSize(license);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        allQuestion = findViewById(R.id.all_question);
        Concept = findViewById(R.id.concept);
        Culture = findViewById(R.id.culture);
        Figure = findViewById(R.id.figure);



        setOnclickType(allQuestion,license,"all");
        setOnclickType(Concept,license,ConceptAPI);
        setOnclickType(Culture,license,CultureAPI);
        setOnclickType(Figure,license,FigureAPI);

    }
    public void setOnclickType(RelativeLayout layouts, String license, @Nullable String type){
        layouts.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewTestActivity.this,QuestionViewListActivity.class);
                TextView textview = (TextView) layouts.getChildAt(0);
                String text = textview.getText().toString();
                intent.putExtra("TITLE_QUESTION_LIST",text);
                intent.putExtra("LICENSE_QUESTION_LIST",license);
                intent.putExtra("TYPE_QUESTION_LIST",type);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFetchProgress(ArrayList<QuestionSet> questionSets) {

    }

    @Override
    public void onFetchProgressQuestionSize(QuestionSize questionSize) {

    }

    @Override
    public void onFetchComplete(String message) {

    }
}