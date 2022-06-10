package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import team2.mobileapp.gplx.R;

public class Profile extends AppCompatActivity {
    LinearLayout btn_Test,btn_Info,btn_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btn_Test=findViewById(R.id.btn_Test);
        btn_Info=findViewById(R.id.btn_Info);
        btn_pass=findViewById(R.id.btn_Pass);
        btn_Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Profile.this,HistoryActivity.class);
                startActivity(intent1);
            }
        });
        btn_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Profile.this, EditProfileActivity.class);
                startActivity(intent2);
            }
        });
        btn_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(Profile.this, SetNewPasswordActivity.class);
                startActivity(intent3);
            }
        });
    }
}