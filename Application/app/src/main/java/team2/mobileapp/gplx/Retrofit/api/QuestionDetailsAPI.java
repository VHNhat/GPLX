package team2.mobileapp.gplx.Retrofit.api;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import team2.mobileapp.gplx.Retrofit.dto.QuestionDetails;

public interface QuestionDetailsAPI {
    @GET("question/{api}")
    Call<ArrayList<QuestionDetails>> getTestLicense(@Path("api") String api);
}
