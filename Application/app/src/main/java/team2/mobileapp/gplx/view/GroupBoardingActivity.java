package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.TrafficSignCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.TrafficSignController;
import team2.mobileapp.gplx.Retrofit.dto.TrafficSignTypes;
import team2.mobileapp.gplx.VariableGlobal.VariableGlobal;
import team2.mobileapp.gplx.Volley.model.TrafficSign;

public class GroupBoardingActivity extends AppCompatActivity implements TrafficSignCallBackListener {
    TextView titleBoard;
    TrafficSignController trafficSignController;
    TextView quantityOfAll;
    ListView lvItemsTrafficSign;
    GroupBoardingAdapter groupBoardingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_boarding_sign);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);

        titleBoard = findViewById(R.id.tv_title_activity_app);
        quantityOfAll = findViewById(R.id.tv_num_group_test);
        lvItemsTrafficSign = findViewById(R.id.lv_group_boarding_sign);

        trafficSignController = new TrafficSignController(this);
        trafficSignController.getTrafficSignTypes();
        titleBoard.setText("Các loại biến báo");
        VariableGlobal.SetNavigationBar(this);
    }
private void setOnlickTrafficType(){
    lvItemsTrafficSign.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            GroupBoardingItem groupBoardingItem = (GroupBoardingItem) adapterView.getItemAtPosition(i);
            Toast.makeText(GroupBoardingActivity.this, groupBoardingItem.getId(), Toast.LENGTH_SHORT).show();
        }
    });
}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
    }

    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        return super.moveTaskToBack(nonRoot);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onFetchProgress(TrafficSign trafficSign) {

    }

    @Override
    public void onFetchTrafficSignTypeProgress(List<TrafficSignTypes> trafficSignTypes) {

        List<GroupBoardingItem> listGroupBoardings = new ArrayList<>();
        int quantityAll = 0;
        for (TrafficSignTypes trafficType : trafficSignTypes) {
            GroupBoardingItem groupBoardingItem = new GroupBoardingItem();
            groupBoardingItem.setId(trafficType.getId());
            groupBoardingItem.setType(trafficType.getCode());
            groupBoardingItem.setName(trafficType.getName());
            groupBoardingItem.setQuantity(trafficType.getQuantity());
            quantityAll += trafficType.getQuantity();
            listGroupBoardings.add(groupBoardingItem);
        }

        groupBoardingAdapter = new GroupBoardingAdapter(GroupBoardingActivity.this, 1, listGroupBoardings);
        lvItemsTrafficSign.setAdapter(groupBoardingAdapter);
        quantityOfAll.setText("Gồm " + quantityAll + " câu");
    }

    @Override
    public void onFetchComplete(String message) {

    }
}