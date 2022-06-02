package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import team2.mobileapp.gplx.R;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ReviewTestActivity extends AppCompatActivity {
    private  RelativeLayout allQuestion;
    private  RelativeLayout Concept;
    private  RelativeLayout Culture;
    private  RelativeLayout Figure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_test);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        allQuestion = findViewById(R.id.all_question);
        Concept = findViewById(R.id.concept);
        Culture = findViewById(R.id.culture);
        Figure = findViewById(R.id.figure);

        String allQuestionAPI="a1";
        String ConceptAPI="a2";
        String CultureAPI="b1";
        String FigureAPI="b2";

        setOnclickType(allQuestion,allQuestionAPI);
        setOnclickType(Concept,ConceptAPI);
        setOnclickType(Culture,CultureAPI);
        setOnclickType(Figure,FigureAPI);

    }
    public void setOnclickType(RelativeLayout type,String api){
        type.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewTestActivity.this,QuestionViewListActivity.class);
                TextView textview = (TextView) type.getChildAt(0);
                String text = textview.getText().toString();
                intent.putExtra("TITLE_QUESTION_LIST",text);
                intent.putExtra("API_QUESTION_LIST",api);
                startActivity(intent);
            }
        });
    }
}