package team2.mobileapp.gplx.Retrofit.callbacks;

import java.util.List;

import team2.mobileapp.gplx.Retrofit.models.TrafficSign;

public interface TrafficSignCallBackListener {
    void onFetchListProgress(List<TrafficSign> trafficSigns);
    void onFetchProgress(TrafficSign trafficSign);
    void onFetchComplete(String message);
}
