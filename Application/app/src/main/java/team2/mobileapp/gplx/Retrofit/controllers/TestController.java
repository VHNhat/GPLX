package team2.mobileapp.gplx.Retrofit.controllers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team2.mobileapp.gplx.Retrofit.api.RestAPIManager;
import team2.mobileapp.gplx.Retrofit.api.TestAPI;
import team2.mobileapp.gplx.Retrofit.callbacks.TestCallBackListener;
import team2.mobileapp.gplx.Retrofit.callbacks.TestCallBackListener;
import team2.mobileapp.gplx.Retrofit.models.Test;
import team2.mobileapp.gplx.Retrofit.models.TrafficSign;

public class TestController {
    private TestCallBackListener testCallBackListener;
    private RestAPIManager restApiManager;
    private String message;

    public TestController(TestCallBackListener listener) {
        testCallBackListener = listener;
        restApiManager = new RestAPIManager();
    }

    public void startFetching(String license) {
        restApiManager.getTestApi().getTestLicense(license).enqueue(new Callback<ArrayList<Test>>() {
            @Override
            public void onResponse(Call<ArrayList<Test>> call, Response<ArrayList<Test>> response) {
                try {
                    message = response.code() == 200 ? " Successfully" : " Error";
                    ArrayList<Test> tests = response.body();
                    Log.d("ALLTEST", tests.get(0).toString());
                    testCallBackListener.onFetchProgress(tests);

                } catch (Exception e) {
                    Log.d("Error:", e.getMessage());
                }
                testCallBackListener.onFetchComplete(message);
            }

            @Override
            public void onFailure(Call<ArrayList<Test>> call, Throwable t) {
                Log.d("FAILALL", "onResponse: True ");
            }
        });
    }
}
