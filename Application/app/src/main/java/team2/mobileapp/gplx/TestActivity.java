package team2.mobileapp.gplx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

public class TestActivity extends AppCompatActivity {
   SeekBar seekbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        seekbar=  findViewById(R.id.determinateBar);
        seekbar.setEnabled(false);
    }
}