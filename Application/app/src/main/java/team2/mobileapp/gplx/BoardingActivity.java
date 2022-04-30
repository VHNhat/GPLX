package team2.mobileapp.gplx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BoardingActivity extends AppCompatActivity {

    Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding);

        btn_start = findViewById(R.id.btn_boarding);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BoardingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}