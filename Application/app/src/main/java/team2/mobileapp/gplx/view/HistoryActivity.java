package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.util.ArrayList;
import java.util.List;

import team2.mobileapp.gplx.R;

public class HistoryActivity extends AppCompatActivity {
    public HistoryAdapter historyAdapter ;
    private List<HistoryItem> names = new ArrayList<>();;
//
//    BottomNavigationItemView btn_home, btn_menu, btn_noti, btn_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

//        btn_home = findViewById(R.id.page_home);
//        btn_menu = findViewById(R.id.page_menu);
//        btn_noti = findViewById(R.id.page_nofication);
//        btn_profile = findViewById(R.id.page_profile);
//
//        btn_noti.setSelected(true);
//
//        Intent menu = new Intent(this, SelectCategoryActivity.class);
//        Intent home = new Intent(this, SelectCategoryActivity.class);
//        Intent profile = new Intent(this, EditProfileActivity.class);
//
//        Home(home);
//        Menu(menu);
//        Profile(profile);

        ListView listView = (ListView) findViewById(R.id.lvItems);
        HistoryItem h = new HistoryItem();
        h.setCategoryName("Hạng A1");
        for (int i = 0; i < 10; i++) {
            names.add(h);
        }

        historyAdapter = new HistoryAdapter(HistoryActivity.this, 1,names );

        listView.setAdapter(historyAdapter);
    }
//    private void Profile(Intent profile) {
//        btn_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(profile);
//            }
//        });
//    }
//    private void Menu(Intent menu) {
//        btn_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(menu);
//            }
//        });
//    }
//
//    private void Home(Intent home) {
//        btn_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(home);
//            }
//        });
//    }
}