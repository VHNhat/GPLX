package team2.mobileapp.gplx.Retrofit.api;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import team2.mobileapp.gplx.Retrofit.models.Test;

public interface TestAPI {
    @GET("question/{license}")
    Call<ArrayList<Test>> getTestLicense(@Path("license") String license);
}
