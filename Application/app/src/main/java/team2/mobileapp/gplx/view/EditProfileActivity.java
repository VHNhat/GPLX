package team2.mobileapp.gplx.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import team2.mobileapp.gplx.R;
<<<<<<< HEAD

import android.os.Bundle;

public class EditProfileActivity extends AppCompatActivity {
    //    BottomNavigationItemView btn_home, btn_menu, btn_noti, btn_profile;
=======
import team2.mobileapp.gplx.Retrofit.callbacks.AccountCallbackListener;
import team2.mobileapp.gplx.Retrofit.controllers.AccountController;
import team2.mobileapp.gplx.Retrofit.models.Account;

public class EditProfileActivity extends AppCompatActivity implements AccountCallbackListener {

    private EditText etFullName;
    private EditText etUsername;
    private EditText etEmail;
    private Button btnSave;

    private Account accountView;
    private AccountController accountController;

    private boolean isUpdated = false;

>>>>>>> b35b60da1e9c2267833ece50e13898cfe6b7a3fa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);

        setContentView(R.layout.activity_edit_profile);

        InitialVariables();
        try {
            accountController.startFetching("629c1f72fd4add256bdb9997");
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        accountView.setFirstName(etFullName.getText().toString());
                        accountView.setUsername(etUsername.getText().toString());
                        accountController.updateAccount(accountView.getId(), accountView);
                    } catch (Exception e) {
                        Log.d("Error:", e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    private void InitialVariables() {
        accountController = new AccountController(this);
        etFullName = (EditText) findViewById(R.id.et_fullname);
        etUsername = (EditText) findViewById(R.id.et_username);
        etEmail = (EditText) findViewById(R.id.et_email_profile);
        btnSave = (Button) findViewById(R.id.btn_save);
    }

    @Override
    public void onFetchAccountProgress(Account account) {
        if (account != null) {
            this.accountView = account;
            etFullName.setText(account.getLastName() + " " + account.getFirstName());
            etUsername.setText(account.getUsername());
            etEmail.setText(account.getEmail());
        }
    }

<<<<<<< HEAD
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
=======
    @Override
    public void onFetchComplete(String message) {
        Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_SHORT).show();
>>>>>>> b35b60da1e9c2267833ece50e13898cfe6b7a3fa
    }
}