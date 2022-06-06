package team2.mobileapp.gplx.VariableGlobal;

import android.app.Activity;
import android.content.Intent;
import android.view.View;


import java.util.ArrayList;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.view.EditProfileActivity;
import team2.mobileapp.gplx.view.HistoryActivity;
import team2.mobileapp.gplx.view.SelectCategoryActivity;

public class VariableGlobal  {
    public static String typeCode ="A1";
    public static final  String PHOTOURL1="https://firebasestorage.googleapis.com/v0/b/upload-image-9b971.appspot.com/o/";
    public static final  String PHOTOURL2="%2F";
    public static final  String PHOTOURL3="alt=media&token=9c10699c-a49b-4464-b698-07b04e25972b";
    public static ArrayList<String> listMarkGlobal = new ArrayList<>();
    public static void SetNavigationBar(Activity activity){
        View bottom_bar_container = activity.findViewById(R.id.bottom_bar);
        bottom_bar_container.findViewById(R.id.page_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SelectCategoryActivity.class);
                activity.startActivity(intent);
            }
        });
        bottom_bar_container.findViewById(R.id.page_category).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, HistoryActivity.class);
                activity.startActivity(intent);
            }
        });
        bottom_bar_container.findViewById(R.id.page_notice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, HistoryActivity.class);
                activity.startActivity(intent);
            }
        });
        bottom_bar_container.findViewById(R.id.page_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, EditProfileActivity.class);
                activity.startActivity(intent);
            }
        });
    }
}
