package team2.mobileapp.gplx.Retrofit.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import team2.mobileapp.gplx.Volley.model.Question;

public interface QuestionsAPI {
    @GET("apil")
    Call<List<Question>> getAllByLicense();
}
