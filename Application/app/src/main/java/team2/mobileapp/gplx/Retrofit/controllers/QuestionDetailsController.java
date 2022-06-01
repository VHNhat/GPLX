package team2.mobileapp.gplx.Retrofit.controllers;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team2.mobileapp.gplx.Retrofit.api.RestAPIManager;
import team2.mobileapp.gplx.Retrofit.callbacks.TestCallBackListener;
import team2.mobileapp.gplx.Retrofit.dto.QuestionDetails;

public class QuestionDetailsController {
    private TestCallBackListener testCallBackListener;
    private RestAPIManager restApiManager;
    private String message;

    public QuestionDetailsController(TestCallBackListener listener) {
        testCallBackListener = listener;
        restApiManager = new RestAPIManager();
    }

    public void startFetching(String license) {
        restApiManager.getTestApi().getTestLicense(license).enqueue(new Callback<ArrayList<QuestionDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<QuestionDetails>> call, Response<ArrayList<QuestionDetails>> response) {
                try {
                    message = response.code() == 200 ? " Successfully" : " Error";
                    ArrayList<QuestionDetails> questionDetails = response.body();
                    Log.d("ALLTEST", questionDetails.get(0).toString());
                    testCallBackListener.onFetchProgress(questionDetails);

                } catch (Exception e) {
                    Log.d("Error:", e.getMessage());
                }
                testCallBackListener.onFetchComplete(message);
            }

            @Override
            public void onFailure(Call<ArrayList<QuestionDetails>> call, Throwable t) {
                Log.d("FAILALL", "onResponse: True ");
            }
        });
    }
}