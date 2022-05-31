package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import  team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.TrafficSignCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.TrafficSignController;
import team2.mobileapp.gplx.Retrofit.models.TrafficSign;

public class DetailsNoticeBoard extends AppCompatActivity implements TrafficSignCallBackListener {
    private TrafficSignController trafficSignController;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_notice_board);

        tvTitle = (TextView) findViewById(R.id.tv_title_board_details);

        trafficSignController = new TrafficSignController(this);

        trafficSignController.startFetching(getIntent().getStringExtra("ID"));
//        Toast.makeText(DetailsNoticeBoard.this, getIntent().getStringExtra("ID"), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFetchListProgress(List<TrafficSign> trafficSigns) {

    }

    @Override
    public void onFetchProgress(TrafficSign trafficSign) {
        tvTitle.setText(trafficSign.getName());
        Log.d("Name", trafficSign.getName());
        Toast.makeText(DetailsNoticeBoard.this, getIntent().getStringExtra("ID"), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFetchComplete(String message) {

    }
}