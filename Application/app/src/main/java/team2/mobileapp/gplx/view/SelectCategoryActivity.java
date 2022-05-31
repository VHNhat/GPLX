package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import team2.mobileapp.gplx.R;

public class SelectCategoryActivity extends AppCompatActivity {

//    BottomNavigationItemView btn_home, btn_menu, btn_noti, btn_profile;
    RelativeLayout btn_a1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        btn_a1 = findViewById(R.id.btn_a1);
//        btn_home = findViewById(R.id.page_home);
//        btn_menu = findViewById(R.id.page_menu);
//        btn_noti = findViewById(R.id.page_nofication);
//        btn_profile = findViewById(R.id.page_profile);
//
//        btn_home.setSelected(true);
//
//        Intent menu = new Intent(this, SelectCategoryActivity.class);
//        Intent notification = new Intent(this, HistoryActivity.class);
//        Intent profile = new Intent(this, EditProfileActivity.class);
//
//        Notification(notification);
//        Menu(menu);
//        Profile(profile);

    }

//    private void Profile(Intent profile) {
//        btn_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(profile);
//            }
//        });
//    }
//
//    private void Menu(Intent menu) {
//        btn_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(menu);
//            }
//        });
//    }
//
//    private void Notification(Intent notification) {
//        btn_noti.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(notification);
//            }
//        });
//    }
}