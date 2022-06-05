package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.LicenseByIdCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.LicenseController;
import team2.mobileapp.gplx.Retrofit.models.License;
import team2.mobileapp.gplx.Volley.model.Answer;
import team2.mobileapp.gplx.Volley.model.CheckRadioButton;
import team2.mobileapp.gplx.Volley.model.Question;
import team2.mobileapp.gplx.Volley.model.dto.DtoQuestionSet;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity implements LicenseByIdCallBackListener {
    LicenseController licenseController;
    ArrayList<CheckRadioButton> checkList = new ArrayList<>();
    DtoQuestionSet dto;
    License license;
    int rightAns, wrongAns = 0;
    ImageView iv_result;
    TextView tv_result_show, tv_true_percent, tv_false_percent;
    RelativeLayout result_layout, correct, incorrect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        InitialVariable();
        checkList = (ArrayList<CheckRadioButton>) getIntent().getSerializableExtra("History");
        dto = (DtoQuestionSet) getIntent().getSerializableExtra("Dto");
        Log.i("Dto",dto.toString());
        Log.i("CheckList",checkList.toString());
        UpdateScore(dto, checkList);

        String licenseId = dto.getQuestionSet().getLicenseId();
        licenseController = new LicenseController(this);
        licenseController.startFetchingLicenseById(licenseId);

    }

    private void ShowLayout() {
        Log.i("right ans",String.valueOf(rightAns));
        Log.i("wrong ans",String.valueOf(wrongAns));
        int total = rightAns + wrongAns;
        final float scale = getResources().getDisplayMetrics().density;
        tv_true_percent.setText((rightAns*100/total) + "%");
        tv_false_percent.setText((wrongAns*100/total) + "%");
        if(license != null){
            if(license.getName().equals("A1")){
                int minPx = 24;
                int rightPx = (int) (minPx * rightAns);
                int wrongPx = (int) (minPx * wrongAns);
                correct.getLayoutParams().height = rightPx;
                incorrect.getLayoutParams().height = wrongPx;
                if(rightAns < 21){
                    iv_result.setImageResource(R.drawable.result_failed);
                    result_layout.setBackgroundColor(Color.RED);
                    tv_result_show.setText("Không đạt");
                }
            }
            if(license.getName().equals("A2")){
                int minPx = 24;
                int rightPx = (int) (minPx * rightAns);
                int wrongPx = (int) (minPx * wrongAns);
                correct.getLayoutParams().height = rightPx;
                incorrect.getLayoutParams().height = wrongPx;
                if(rightAns < 23){
                    iv_result.setImageResource(R.drawable.result_failed);
                    result_layout.setBackgroundColor(Color.RED);
                    tv_result_show.setText("Không đạt");
                }
            }
            if(license.getName().equals("B1")){
                int minPx = 20;
                int rightPx = (int) (minPx * rightAns);
                int wrongPx = (int) (minPx * wrongAns);
                correct.getLayoutParams().height = rightPx;
                incorrect.getLayoutParams().height = wrongPx;
                if(rightAns < 27){
                    iv_result.setImageResource(R.drawable.result_failed);
                    result_layout.setBackgroundColor(Color.RED);
                    tv_result_show.setText("Không đạt");
                }
            }
            if(license.getName().equals("B2")){
                int minPx = 17;
                int rightPx = (int) (minPx * rightAns);
                int wrongPx = (int) (minPx * wrongAns);
                correct.getLayoutParams().height = rightPx;
                incorrect.getLayoutParams().height = wrongPx;
                if(rightAns < 32){
                    iv_result.setImageResource(R.drawable.result_failed);
                    result_layout.setBackgroundColor(Color.RED);
                    tv_result_show.setText("Không đạt");
                }
            }
        }
    }

    private void InitialVariable() {
        result_layout = findViewById(R.id.layout_result);
        iv_result = findViewById(R.id.iv_result);
        tv_result_show = findViewById(R.id.tv_result_show);
        tv_true_percent = findViewById(R.id.tv_true_percent);
        tv_false_percent = findViewById(R.id.tv_false_percent);
        correct = findViewById(R.id.value_correct);
        incorrect = findViewById(R.id.value_incorrect);
    }

    private void UpdateScore(DtoQuestionSet dto, ArrayList<CheckRadioButton> checkList) {
        List<Question> questions = dto.getQuestList();
        List<Answer> answers = dto.getAnsList();
        for(int i = 0; i < dto.getQuestList().size(); i++){
            boolean flag = false;
            for(CheckRadioButton item : checkList){
                if(item.getQuestionId().equals(questions.get(i).getId()) && item.getAnswerId().equals(answers.get(i).getId())){
                    Log.i("Result", String.valueOf(answers.get(i).getResult()));
                    if(item.getAnswerIndex() == answers.get(i).getResult()){
                        flag = true;
                        rightAns++;
                    }
                }
            }
            if(!flag) wrongAns++;
        }
    }

    @Override
    public void onFetchProgress(License license) {
        this.license = license;
        ShowLayout();
    }

    @Override
    public void onFetchComplete(String message) {

    }
}