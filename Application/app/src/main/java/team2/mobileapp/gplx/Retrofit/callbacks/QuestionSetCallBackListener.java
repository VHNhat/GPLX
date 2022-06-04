package team2.mobileapp.gplx.Retrofit.callbacks;

import java.util.ArrayList;

import team2.mobileapp.gplx.Retrofit.models.QuestionSet;
import team2.mobileapp.gplx.Retrofit.models.QuestionSize;

public interface QuestionSetCallBackListener {
    void onFetchProgress(ArrayList<QuestionSet> questionSets);
    void onFetchProgressQuestionSize(QuestionSize questionSize);
    void onFetchComplete(String message);
}
