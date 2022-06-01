package team2.mobileapp.gplx.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Volley.model.dto.DtoQuestionSet;
import team2.mobileapp.gplx.Volley.service.TestService;

public class TestActivity extends AppCompatActivity {

    TextView tv_positionQuestion, tv_totalQuestion, tv_question;
    SeekBar determinateBar;
    RadioButton rd_answer1, rd_answer2, rd_answer3, rd_answer4, rd_answer5;
    Button btn_next, btn_prev;
    ImageView iv_question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        InitialVariables();

        final TestService testService = new TestService(TestActivity.this);

        // Đang hardcode để test
        String questionSetId = "62965678b6e03f1f146bfd72";

        ShowTest(testService, questionSetId);

    }

    public void ShowTest(TestService testService, String questionSetId) {
        testService.GetByQuestionSet(questionSetId, new TestService.GetByQuestionSetCallBack() {
            @Override
            public void onError(String message) {
                Toast.makeText(TestActivity.this, "Error Babe!!!", Toast.LENGTH_LONG);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(DtoQuestionSet dto) {
                Toast.makeText(TestActivity.this, "OK Babe", Toast.LENGTH_LONG);
                Log.i("DTO", dto.getQuestionSet().get().getName());
                int totalQuestion = dto.getQuestList().size();
                Log.i("Total question", dto.getQuestionSet().get().getName());
                tv_totalQuestion.setText("" + dto.getQuestionSet().get().getQuantity());
                final int[] i = {0};

                UpdateQuestion(dto, totalQuestion, i[0]);
                btn_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i[0]++;
                        Log.i("Next", "Da bam next");
                        UpdateQuestion(dto, totalQuestion, i[0]);
                    }
                });
                btn_prev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i[0]--;
                        Log.i("Prev", "Da bam prev");
                        UpdateQuestion(dto, totalQuestion, i[0]);
                    }
                });

            }
        });
    }

    private void UpdateQuestion(DtoQuestionSet dto, int totalQuestion, int i) {
        Log.i("I", String.valueOf(i));
        if(i == 0){
            btn_prev.setVisibility(View.INVISIBLE);
        }
        else if(i == totalQuestion-1){
            btn_next.setVisibility(View.INVISIBLE);
        }
        else{
            btn_next.setVisibility(View.VISIBLE);
            btn_prev.setVisibility(View.VISIBLE);
        }
        String photo = dto.getQuestList().get(i).getPhoto();
        // Khi nào có hình thì mở ra
//        if(!photo.isEmpty()){
//            String uri = photo.substring(0, photo.length() - 4);
//            int imageResource = getResources().getIdentifier(uri, "drawable", getPackageName());
//            Drawable res = getResources().getDrawable(imageResource);
//            iv_question.setImageDrawable(res);
//        }
        int index = dto.getQuestList().get(i).getIndex();
        String[] ansList = dto.getAnsList().get(i).getAnswerList();
        int numberOfAns = ansList.length;
        determinateBar.setProgress(index * 100 / totalQuestion);
        tv_positionQuestion.setText("" + dto.getQuestList().get(i).getIndex());
        tv_question.setText(dto.getQuestList().get(i).getQuery());
//        ResetReadioButton();
        switch (numberOfAns) {
            case 2:
                rd_answer1.setText(ansList[0]);
                rd_answer2.setText(ansList[1]);
                rd_answer3.setVisibility(View.INVISIBLE);
                rd_answer4.setVisibility(View.INVISIBLE);
                rd_answer5.setVisibility(View.INVISIBLE);
                break;
            case 3:
                rd_answer1.setText(ansList[0]);
                rd_answer2.setText(ansList[1]);
                rd_answer3.setText(ansList[2]);
                rd_answer4.setVisibility(View.INVISIBLE);
                rd_answer5.setVisibility(View.INVISIBLE);
                break;
            case 4:
                rd_answer1.setText(ansList[0]);
                rd_answer2.setText(ansList[1]);
                rd_answer3.setText(ansList[2]);
                rd_answer4.setText(ansList[3]);
                rd_answer5.setVisibility(View.INVISIBLE);
                break;
            case 5:
                rd_answer1.setText(ansList[0]);
                rd_answer2.setText(ansList[1]);
                rd_answer3.setText(ansList[2]);
                rd_answer4.setText(ansList[3]);
                rd_answer5.setText(ansList[4]);
            default:
                break;
        }
    }

    private void ResetReadioButton() {
        rd_answer1.setChecked(false);
        rd_answer2.setChecked(false);
        rd_answer3.setChecked(false);
        rd_answer4.setChecked(false);
        rd_answer5.setChecked(false);
    }


    public void InitialVariables() {
        tv_positionQuestion = findViewById(R.id.tv_positionQuestion);
        tv_totalQuestion = findViewById(R.id.tv_totalQuestion);
        tv_question = findViewById(R.id.tv_question);
        determinateBar = findViewById(R.id.determinateBar);
        rd_answer1 = findViewById(R.id.rd_answer1);
        rd_answer2 = findViewById(R.id.rd_answer2);
        rd_answer3 = findViewById(R.id.rd_answer3);
        rd_answer4 = findViewById(R.id.rd_answer4);
        rd_answer5 = findViewById(R.id.rd_answer5);
        btn_next = findViewById(R.id.btn_next);
        btn_prev = findViewById(R.id.btn_prev);
        iv_question = findViewById(R.id.iv_question);
    }
}