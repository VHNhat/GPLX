package team2.mobileapp.gplx.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.model.dto.DtoQuestionSet;
import team2.mobileapp.gplx.service.TestService;

public class TestActivity extends AppCompatActivity {

    TextView tv_positionQuestion, tv_totalQuestion, tv_question;
    SeekBar determinateBar;
    RadioButton rd_answer1, rd_answer2, rd_answer3, rd_answer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        InitialVariables();

        final TestService testService = new TestService(TestActivity.this);
        String questionSetId = "62958b6355f289692d18e2c0";
        ShowTest(testService, questionSetId);

    }

    public void ShowTest(TestService testService, String questionSetId){
        testService.GetByQuestionSet(questionSetId, new TestService.GetByQuestionSetCallBack() {
            @Override
            public void onError(String message) {
                Toast.makeText(TestActivity.this, "Error Babe!!!", Toast.LENGTH_LONG);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(DtoQuestionSet dto) {
                Toast.makeText(TestActivity.this, "OK Babe", Toast.LENGTH_LONG);
                Log.i("DTO",dto.getQuestionSet().get().getName());
                tv_totalQuestion.setText(""+dto.getQuestionSet().get().getQuantity());
                tv_positionQuestion.setText(""+dto.getQuestList().get(0).getIndex());
                determinateBar.setProgress(dto.getQuestionSet().get().getQuantity()/dto.getQuestList().get(0).getIndex());
                tv_question.setText(dto.getQuestList().get(0).getQuery());
                rd_answer1.setText(dto.getAnsList().get(0).getAnswerList()[0]);
                rd_answer2.setText(dto.getAnsList().get(0).getAnswerList()[1]);
                rd_answer3.setText(dto.getAnsList().get(0).getAnswerList()[2]);

            }
        });
    }

    public void InitialVariables(){
        tv_positionQuestion = findViewById(R.id.tv_positionQuestion);
        tv_totalQuestion = findViewById(R.id.tv_totalQuestion);
        tv_question = findViewById(R.id.tv_question);
        determinateBar = findViewById(R.id.determinateBar);
        rd_answer1 = findViewById(R.id.rd_answer1);
        rd_answer2 = findViewById(R.id.rd_answer2);
        rd_answer3 = findViewById(R.id.rd_answer3);
        rd_answer4 = findViewById(R.id.rd_answer4);
    }
}