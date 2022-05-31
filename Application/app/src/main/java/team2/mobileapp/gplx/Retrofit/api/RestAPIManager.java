package team2.mobileapp.gplx.Retrofit.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import team2.mobileapp.gplx.Retrofit.models.TrafficSign;

public class RestAPIManager {
    private TrafficSignAPI trafficSignAPI;
    public static final String BASE_URL = "http://10.0.2.2:8080/api/";

    public TrafficSignAPI getTrafficSignApi()
    {
        if(trafficSignAPI == null)
        {
            trafficSignAPI = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(TrafficSignAPI.class);
        }
        return trafficSignAPI;
    }
}

