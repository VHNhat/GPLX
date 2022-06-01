package team2.mobileapp.gplx.Retrofit.callbacks;

import java.util.ArrayList;


import team2.mobileapp.gplx.Retrofit.models.Test;


public interface TestCallBackListener {
    void onFetchProgress(ArrayList<Test> trafficSign);
    void onFetchComplete(String message);
}
