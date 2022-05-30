package team2.mobileapp.gplx.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import team2.mobileapp.gplx.callback.MySingleton;
import team2.mobileapp.gplx.models.License;
import team2.mobileapp.gplx.models.NoticeBoard;

public class NoticeBoardService {
    public static final String BASE_IP = "http://10.0.2.2:8080/api";

    Context context;

    public NoticeBoardService(Context context) {
        this.context = context;
    }
    public interface GetALLBoardCallBack {
        void onError(String message);

        void onResponse(List<NoticeBoard> boards);
    }
    public void GetAll(GetALLBoardCallBack getALLBoardCallBack){
        String requestMapping = "/trafficsign";
        String url = BASE_IP + requestMapping;
        List<NoticeBoard> boards = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i < response.length(); i++){
                        NoticeBoard board = new NoticeBoard();
                        JSONObject eachBoard = response.getJSONObject(i);

                        board.setId(eachBoard.getString("id"));
                        board.setBoardCode(eachBoard.getString("Code"));
                        board.setBoardName(eachBoard.getString("Name"));
                        board.setBoardDescription(eachBoard.getString("Description"));
                        board.setType(eachBoard.getString("TrafficSignType"));

                        boards.add(board);
                    }
                } catch (JSONException e) {
                    Log.d("JSON Exception:", e.toString());
                }
                getALLBoardCallBack.onResponse(boards);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                getALLBoardCallBack.onError(error.getMessage());
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);

    }
}
