package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import team2.mobileapp.gplx.R;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setContentView(R.layout.activity_navigation);
    }
}