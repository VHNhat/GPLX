package team2.mobileapp.gplx.Retrofit.api;

import android.util.Log;

import retrofit2.Call;
import java.util.List;
import retrofit2.http.GET;

import retrofit2.http.Path;
import retrofit2.http.Query;
import team2.mobileapp.gplx.Retrofit.models.TrafficSign;

public interface TrafficSignAPI {
    @GET("trafficsign")
    Call<List<TrafficSign>> getTrafficSigns();
    @GET("trafficsign/{id}")
     Call<TrafficSign> getTrafficSign(@Path("id") String id);
}
