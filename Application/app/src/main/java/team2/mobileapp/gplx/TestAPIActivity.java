package team2.mobileapp.gplx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

//import team2.mobileapp.gplx.callback.VolleyResponseListener;
import team2.mobileapp.gplx.models.License;
import team2.mobileapp.gplx.service.LicenseService;

public class TestAPIActivity extends AppCompatActivity {

    private Button btn_getAll, btn_add, btn_update;
    private EditText et_id, et_name, et_status, et_description;
    private ListView lv_licenseShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_api);

        btn_getAll = findViewById(R.id.btn_get_all_license);
        btn_add = findViewById(R.id.btn_post_license);
        btn_update = findViewById(R.id.btn_update_license);

        et_id = findViewById(R.id.et_id_license);
        et_name = findViewById(R.id.et_name_license);
        et_description = findViewById(R.id.et_description_license);
        et_status = findViewById(R.id.et_status_license);

        final LicenseService licenseService = new LicenseService(TestAPIActivity.this);

        btn_getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TestAPIActivity.this, "You Clicked Get All", Toast.LENGTH_SHORT).show();
                licenseService.GetAll(new LicenseService.GetALLLicense() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(TestAPIActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<License> licenses) {
                        Toast.makeText(TestAPIActivity.this, licenses.toString(), Toast.LENGTH_SHORT).show();
                        ArrayAdapter arrayAdapter = new ArrayAdapter(TestAPIActivity.this, android.R.layout.simple_list_item_1, licenses);
                        lv_licenseShow.setAdapter(arrayAdapter);
                    }
                });
            }
        });
    }
}