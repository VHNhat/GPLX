package team2.mobileapp.gplx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class EditProfileActivity extends AppCompatActivity {
//    BottomNavigationItemView btn_home, btn_menu, btn_noti, btn_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

//        btn_home = findViewById(R.id.page_home);
//        btn_menu = findViewById(R.id.page_menu);
//        btn_noti = findViewById(R.id.page_nofication);
//        btn_profile = findViewById(R.id.page_profile);
//
//        btn_profile.setSelected(true);
//
//        Intent home = new Intent(this, SelectCategoryActivity.class);
//        Intent menu = new Intent(this, SelectCategoryActivity.class);
//        Intent notification = new Intent(this, HistoryActivity.class);
//
//        Notification(notification);
//        Menu(menu);
//        Home(home);
    }

//    private void Home(Intent home) {
//        btn_home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(home);
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