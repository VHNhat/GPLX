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

import java.util.LinkedList;
import java.util.List;

import team2.mobileapp.gplx.callback.MySingleton;
//import team2.mobileapp.gplx.callback.VolleyResponseListener;
import team2.mobileapp.gplx.models.License;

public class LicenseService {
    public static final String QUERY_FOR_GET_ALL = "http://127.0.0.1:8080/api/license";
    public static final String QUERY_FOR_POST = "http://127.0.0.1:8080/api/license/add";
    public static final String QUERY_FOR_PUT = "http://127.0.0.1:8080/api/license/edit";
    public static final String QUERY_FOR_DELETE = "http://127.0.0.1:8080/api/license/delete";

    Context context;

    public LicenseService(Context context) {
        this.context = context;
    }
    public interface GetALLLicense {
        void onError(String message);

        void onResponse(List<License> licenses);
    }
    public void GetAll(GetALLLicense getALLLicense) {
        String url = QUERY_FOR_GET_ALL;

        List<License> licenses = new LinkedList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
//                    for(int i = 0; i < response.length(); i++){
//                        JSONObject jresponse = response.getJSONObject(i);
//                        String nickname = jresponse.getString("nickname");
//
//                    }
                    //JSONArray licenseList = response.getJSONArray(1);
                    Log.d("Response Length: ", "" + response.length());
                    for(int i = 0; i < response.length(); i++){
                        License license = new License();
                        Log.d( "LOGGGGGG:","Object" + i);
                        JSONObject eachLicense = response.getJSONObject(i);

                        license.setId(eachLicense.getString("id"));
                        license.setName(eachLicense.getString("Name"));
                        license.setStatus(eachLicense.getString("Status"));
                        license.setDesciption(eachLicense.getString("Description"));

                        licenses.add(license);
                    }
                getALLLicense.onResponse(licenses);
                } catch (JSONException e) {
                    Log.d("JSON Exception:", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
