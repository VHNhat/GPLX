package team2.mobileapp.gplx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class A1Activity extends AppCompatActivity {

    ImageButton btn_back, ib_review;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1);

        btn_back = findViewById(R.id.ib_back);
        ib_review = findViewById(R.id.ib_review);

        Intent review = new Intent(this, ReviewWrongTestActivity.class);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ib_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(review);
            }
        });
    }
}