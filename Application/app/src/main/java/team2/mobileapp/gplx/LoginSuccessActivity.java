package team2.mobileapp.gplx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginSuccessActivity extends AppCompatActivity {

    Button btn_continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        btn_continue = findViewById(R.id.btn_send_email);
        Intent tutorial = new Intent(this,TutorialActivity.class);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(tutorial);
            }
        });
    }
}